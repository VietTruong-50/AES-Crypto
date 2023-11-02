# Started 

### 1) Clone the java project

```bash
git clone https://gist.github.com/1313541.git
```

### 2) Set up

```bash
cd 1313541
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
and the cipher mode (Cipher.ENCRYPT_MODE) to encrypt

- decode(): That will take 3 arguments to pass to the process() method
and the cipher mode (Cipher.DECRYPT_MODE) to decrypt
```

```
process(): This is the main method that is responsible for
encrypting or decrypting data using the AES algorithm in Cipher Block 
Chaining (CBC) mode with PKCS5 padding
```

### 3) Run:

```bash
java -cp /path/to/file/bcprov-ext-jdk16-1.46.jar CryptAES128.java "mysecretkey12345" "1234567890123457" "Hello world!"
```