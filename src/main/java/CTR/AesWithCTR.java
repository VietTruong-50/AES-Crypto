package main.java.CTR;

import java.util.UUID;


public class AesWithCTR {
    public static void main(String[] args) throws Exception {

        String key = UUID.randomUUID().toString().substring(0, 16); //Length at least 16
        String data = args[0]; //Length much greater than 16

        AesCtr aesCtr = new AesCtr(key.getBytes());

        byte[] enc = aesCtr.encrypt(data.getBytes());

        for (int i = 0; i < enc.length; i++) {
            System.out.printf("%02x", enc[i]);
        }

        byte[] dec = aesCtr.decrypt(enc);

        System.out.println();
        System.out.println(new String(dec));
    }
}


