package main.java.CBC;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;

class CryptAES {
    static final String cipher_type = "AES/CBC/PKCS7Padding";

    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());

        String key = args[0];
        String iv = args[1] ;  //The same size as key
        String data = args[2];

        byte[] enc = encode(key, iv, data.getBytes());
        byte[] dec = decode(key, iv, enc);

        for (int i = 0; i < enc.length; i++) {
            System.out.printf("%02x", enc[i]);
        }
        System.out.println();

        System.out.println(new String(dec));
    }

    public static byte[] encode(String skey, String iv, byte[] data) {
        return process(Cipher.ENCRYPT_MODE, skey, iv, data);
    }

    public static byte[] decode(String skey, String iv, byte[] data) {
        return process(Cipher.DECRYPT_MODE, skey, iv, data);
    }

    private static byte[] process(int mode, String skey, String iv, byte[] data) {
        SecretKeySpec key = new SecretKeySpec(skey.getBytes(), "AES");
        AlgorithmParameterSpec param = new IvParameterSpec(iv.getBytes());
        try {
            Cipher cipher = Cipher.getInstance(cipher_type);
            cipher.init(mode, key, param);
            return cipher.doFinal(data);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
