# Started 

### 1) Clone the java project

```bash
https://github.com/VietTruong-50/AES-Crypto.git
```

### 2) Set up

```bash
cd AES-Crypto
```

```
For the 'AES/CBC/PKCS7Padding' support:
   => Import library [bcprov-ext-jdk16-1.46.jar] to the project
```

### Inside the file CryptAES128.java, there are 4 methods:

```Method main(): will take 3 arguments needed to run the program```

```
There are 2 methods that in charge of encrypt and decrypt:
- encode(): That will take 3 arguments to pass to the process() method
and the cipher mode (CTR.Cipher.ENCRYPT_MODE) to encrypt

- decode(): That will take 3 arguments to pass to the process() method
and the cipher mode (CTR.Cipher.DECRYPT_MODE) to decrypt
```

```
process(): This is the main method that is responsible for
encrypting or decrypting data using the AES algorithm in CTR.Cipher Block 
Chaining (CBC) mode with PKCS5 padding
```

### 3) Run:

#### CBC Mode:

```bash
java src/main/java/CBC/CryptAES128.java "mysecretkey12345" "1234567890123457" "Hello world!"
```

#### CTR Mode: