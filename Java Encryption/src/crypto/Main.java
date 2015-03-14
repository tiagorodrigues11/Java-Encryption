package crypto;

import java.io.File;
import java.io.FileOutputStream;


public class Main {

	//AES, Blowfish, DES, DESede, RC2
	private static final String ALGORITHM = "AES";
	
	public Main() {
		try {
			Crypto crypto = new Crypto(ALGORITHM);
			
			byte[] fileEncryptedBytes = crypto.encryptFileText(new File("document.txt"));
	        System.out.println("Encryption Result: " + new String(fileEncryptedBytes));
	        
	        FileOutputStream outputStream = new FileOutputStream("document.encrypted");
            outputStream.write(fileEncryptedBytes);             
            outputStream.close();
	        
	        byte[] fileDecryptedBytes = crypto.decryptFileText(new File("document.encrypted"));
	        System.out.println("Decryption Result : " + new String(fileDecryptedBytes));
			
	        System.out.println(" ---------------- ");
	        
			String input = "Hello World";
			
	        byte[] encryptedBytes = crypto.encryptString(input);
	        System.out.println("Encryption Result: " + new String(encryptedBytes));
	        
	        String decryptedStr = crypto.decryptString(encryptedBytes);
	        System.out.println("Decryption Result : " + decryptedStr);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
}
