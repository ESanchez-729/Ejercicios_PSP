package SHA;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

public class PasswordMD5 {

	public static void main(String[] args) throws NoSuchAlgorithmException {

		String[] password = {"hola123", "ikerbobo","docker"};
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		
		for(int index = 0; index < password.length; index++) {
			
			System.out.println("Palabra => "+password[index]);
			md.update(password[index].getBytes());
			
			byte[] digest = md.digest();
			
			for(byte b : digest) {
				System.out.print(Integer.toHexString(0xFF & b));
			}
			System.out.println();
			
			byte[] encoded = Base64.encodeBase64(digest);
		      System.out.println(new String(encoded));
		}
	}

}
