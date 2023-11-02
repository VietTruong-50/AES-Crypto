# Started 

### 1) Clone the java project

```bash
    git clone https://gist.github.com/1313541.git
```

### 2) Access to the folder

```bash
    cd 1313541
```

### Inside the file CryptAES128.java, there are 4 methods:

```Method main(): will take 3 args needed to run the program```

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