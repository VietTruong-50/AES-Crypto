# Started 

### 1) Clone the java project

```bash
https://github.com/VietTruong-50/AES-Crypto.git
```

### 2) Set up

```bash
cd AES-Crypto
```

```bash
mvn clean install
```

### 3) Run:

#### CBC Mode:


```bash
java -cp target/AES-Crypto-1.0-SNAPSHOT.jar main/java/CBC/CryptAES "mysecretkey12345" "1234567890123457" "Hello world!"
```

##### Inside the class CryptAES128.java, there are 4 methods:

```Method main(): will take 3 arguments needed to run the program```

```
There are 2 methods that in charge of encrypt and decrypt:
- encode(): That will take 3 arguments to pass to the process() method
and the cipher mode (main.java.CTR.Cipher.ENCRYPT_MODE) to encrypt

- decode(): That will take 3 arguments to pass to the process() method
and the cipher mode (main.java.CTR.Cipher.DECRYPT_MODE) to decrypt
```

```
process(): This is the main method that is responsible for
encrypting or decrypting data using the AES algorithm in main.java.CTR.Cipher Block 
Chaining (CBC) mode with PKCS5 padding
```


#### CTR Mode:

```bash
java -cp target/AES-Crypto-1.0-SNAPSHOT.jar main/java/CTR/AesWithCTR "This is the result of CTR"
```

##### There are 2 methods to encrypt and decrypt inside class AesCTR.java :

```
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
```


```
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
```
