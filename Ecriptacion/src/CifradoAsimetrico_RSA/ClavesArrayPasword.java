package CifradoAsimetrico_RSA;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class ClavesArrayPasword {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

		String[] password = {"ikerbobo"};
		
		//Generamos las claves =>
		KeyPairGenerator generador = KeyPairGenerator.getInstance("RSA");
		KeyPair parClaves = generador.generateKeyPair();
		
		// separo las claves =>
		PublicKey clavePublica = parClaves.getPublic();
		PrivateKey clavePrivada = parClaves.getPrivate();
		
		//Guardar las claves =>
		byte[] clavePublicaBytes = clavePublica.getEncoded();
		FileOutputStream fichero = new FileOutputStream("./PublicaKeys.dat");
		fichero.write(clavePublicaBytes);
		fichero.close();
		
		// inicializamos el cifrador o codificador
		Cipher rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		rsa.init(Cipher.ENCRYPT_MODE, clavePrivada);
		
		//Fichero para cifrar las password =>
		FileOutputStream fichero2 = new FileOutputStream("./passwordCifrado.dat");
		
		for(int x = 0; x < password.length; x++) {
			byte[] textoCifrado = rsa.doFinal(password[x].getBytes());
			
			fichero2.write(textoCifrado);
		}
		fichero2.close();
	}

}
