package SHA;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

public class Fichero {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

		File file = new File("password.txt");
		FileReader fileLectura = new FileReader(file);
		BufferedReader leer = new BufferedReader(fileLectura);
		
		String linea;
		
		while((linea = leer.readLine()) != null) {
			System.out.println(linea);
			
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			 md.update(linea.getBytes());
			 
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
		leer.close();
	}

}
