package crypto;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

public class Crypto {
	private Key key;
    private Cipher cipher; 

    public Crypto(String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException {               
        key = KeyGenerator.getInstance(algorithm).generateKey();     
        cipher = Cipher.getInstance(algorithm);
    }

    public byte[] encryptFileText(File file) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
        cipher.init(Cipher.ENCRYPT_MODE, key);
        
        FileInputStream inputStream = new FileInputStream(file);
        byte[] fileBytes = new byte[(int) file.length()];
        inputStream.read(fileBytes);
        inputStream.close();
        
        byte[] inputBytes = fileBytes;
        return cipher.doFinal(inputBytes);
    }
    
    public byte[] decryptFileText(File file) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
        cipher.init(Cipher.DECRYPT_MODE, key);
        
        FileInputStream inputStream = new FileInputStream(file);
        byte[] fileBytes = new byte[(int) file.length()];
        inputStream.read(fileBytes);
        inputStream.close();
        
        byte[] decrypt = cipher.doFinal(fileBytes);
        return decrypt;
    }
    
    public byte[] encryptString(String toEncrypt) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] inputBytes = toEncrypt.getBytes();
        return cipher.doFinal(inputBytes);
    }

    public String decryptString(byte[] toDecrypt) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypt = cipher.doFinal(toDecrypt);
        String decrypted = new String(decrypt);
        return decrypted;
    }
	
}
