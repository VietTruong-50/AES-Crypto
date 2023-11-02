package CTR;

import org.apache.commons.lang3.ArrayUtils;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;


public class AesWithCTR {
    public static void main(String[] args) throws Exception {

        String key = UUID.randomUUID().toString().substring(0, 16); //Length at least 16
        String data = "This is the data to encrypt with AES-CTR."; //Length much greater than 16

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

/**
 * This class extends abstract CTR.Cipher class. It implements AES in counter mode.
 */
class AesCtr extends JavaxCipher
{
    /**
     * Secure random generator.
     */
    protected SecureRandom m_secureRandom;

    /**
     * Number of bytes in a block, which is constant for AES.
     */
    protected static int BLOCK_SIZE_BYTES = 16;

    /**
     * Number of bits in a block, which is constant for AES.
     */
    protected static int BLOCK_SIZE_BITS = 128;

    /**
     * Number of bytes in key.
     */
    protected int KEY_SIZE_BYTES;


    /**
     * Class constructor. Creates a Javax.Crypto.CTR.Cipher instance with AES in CTR<br>
     * mode, without any padding.
     * @param key Input key for the cipher. Should be 16, 24, or 32 bytes long
     * @throws Exception Throws exception if key length is not 16, 24, or 32 bytes.<br>
     * 					 May throw exception based on Javax.Crypto classes.
     */
    public AesCtr(byte[] key) throws Exception
    {
        //use default constructor for cipher.CTR.Cipher
        super();

        //check if input key is ok
        if(key.length != 16 && key.length != 24 && key.length != 32)
        {
            throw new Exception("Key length should be 16, 24, or 32 bytes long");
        }

        //set key length
        KEY_SIZE_BYTES = key.length;

        //create secret key spec instance
        m_keySpec = new SecretKeySpec(key, "AES");

        //create cipher instance
        m_cipher = javax.crypto.Cipher.getInstance("AES/CTR/NoPadding");

        //create secure random number generator instance
        m_secureRandom = new SecureRandom();
    }


    /**
     * Encrypts input data with AES CTR mode.
     * @param data Input byte array.
     * @return Encryption result.
     * @throws Exception Throws exception if there is no data to encrypt.<br>
     * 					 May throw exception based on Javax.Crypto.CTR.Cipher class
     */
    public byte[] encrypt(byte[] data) throws Exception
    {
        //check if there is data to encrypt
        if(data.length == 0)
        {
            throw new Exception("No data to encrypt");
        }

        //create iv
        byte[] iv = new byte[BLOCK_SIZE_BYTES];
        byte[] randomNumber = (new BigInteger(BLOCK_SIZE_BITS, m_secureRandom)).toByteArray();
        int a;
        for(a = 0; a<randomNumber.length && a<BLOCK_SIZE_BYTES; a++)
            iv[a] = randomNumber[a];
        for(; a<BLOCK_SIZE_BYTES; a++)
            iv[a] = 0;

        //init cipher instance
        m_cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, m_keySpec, new IvParameterSpec(iv));

        //return concatenation of iv + encrypted data
        return ArrayUtils.addAll(iv, m_cipher.doFinal(data));
    }


    /**
     * Decrypts input data with AES CTR mode
     * @param data Input byte array.
     * @return Decryption result.
     * @throws Exception Throws exception if there is no data to decrypt.<br>
     * 					 May throw exception based on Javax.Crypto.CTR.Cipher class.
     */
    public byte[] decrypt(byte[] data) throws Exception
    {
        //call overriden function with offset = 0
        return decrypt(data, 0);
    }


    /**
     * Decrypts input data starting and including the offset index position<br>
     * with AES CTR mode.
     * @param data Input byte array.
     * @param offset Offset to start decryption.
     * @return Decryption result.
     * @throws Exception Throws exception if there is no data to decrypt.<br>
     * 					 Throws exception if offset is invalid.<br>
     * 					 May throw exception based on Javax.Crypto.CTR.Cipher class.
     */
    public byte[] decrypt(byte[] data, int offset) throws Exception
    {
        //check if there is data to decrypt after the offset and iv
        if(data.length <= BLOCK_SIZE_BYTES + offset)
        {
            throw new Exception("No data to decrypt");
        }

        //get iv value from the beggining of data
        byte[] iv = new byte[BLOCK_SIZE_BYTES];
        System.arraycopy(data, offset, iv, 0, BLOCK_SIZE_BYTES);

        //init cipher instance
        m_cipher.init(javax.crypto.Cipher.DECRYPT_MODE, m_keySpec, new IvParameterSpec(iv));

        //return decrypted value
        return m_cipher.doFinal(data, (BLOCK_SIZE_BYTES + offset), data.length - (BLOCK_SIZE_BYTES + offset));
    }
}


/**
 * Abstract class to perform encryption, decryption, and decryption by an offset.
 */
abstract class Cipher
{
    /**
     * Default constructor.
     * @throws Exception
     */
    public Cipher() throws Exception
    {
    }


    /**
     * Encrypt input data.
     * @param data Input data.
     * @return Encryption result.
     * @throws Exception
     */
    public abstract byte[] encrypt(byte[] data) throws Exception;


    /**
     * Decrypt input data.
     * @param data Input data.
     * @return Decryption result.
     * @throws Exception
     */
    public abstract byte[] decrypt(byte[] data) throws Exception;


    /**
     * Decrypt input data starting from index offset.
     * @param data Input data.
     * @param offset Starting index for decryption.
     * @return Decryption result.
     * @throws Exception
     */
    public abstract byte[] decrypt(byte[] data, int offset) throws Exception;
}


/**
 * CTR.Cipher class that uses built-in Javax library CTR.Cipher instances.
 */
abstract class JavaxCipher extends Cipher
{
    /**
     * Javax crypto instance.
     */
    protected javax.crypto.Cipher m_cipher;

    /**
     * Javax secret key spec instance.
     */
    protected javax.crypto.spec.SecretKeySpec m_keySpec;


    /**
     * Default constructor.
     * @throws Exception
     */
    public JavaxCipher() throws Exception
    {

    }
}