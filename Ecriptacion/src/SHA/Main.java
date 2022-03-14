package SHA;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

public class Main {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		 MessageDigest md = MessageDigest.getInstance("SHA-256");
	      md.update("texto a cifrar".getBytes());
	      byte[] digest = md.digest();

	      // Se escribe byte a byte en hexadecimal
	      for (byte b : digest) {
	         System.out.print(Integer.toHexString(0xFF & b));
	      }
	      System.out.println();

	      // Se escribe codificado base 64. Se necesita la librería
	      // commons-codec-x.x.x.jar de Apache
	      byte[] encoded = Base64.encodeBase64(digest);
	      System.out.println(new String(encoded));
	}

}
