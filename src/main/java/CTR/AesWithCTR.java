package CTR;

public class AesWithCTR {
    public static void main(String[] args) throws Exception {

        String key = args[0];
        String data = args[1];

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
