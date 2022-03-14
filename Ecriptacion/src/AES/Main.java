package AES;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Main {

	public static void main(String[] args) throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
		KeyGenerator kg = KeyGenerator.getInstance("AES");
		kg.init(128);
		//Podemos generar una clave aleatoria
		//Key clave = kg.generateKey();
		
		//O generar una clave basada en un texto. Tiene que tener más de 16 bytes.
		String textoGenerador = "texto de clave....";
		Key clave = new SecretKeySpec(textoGenerador.getBytes(), 0 , 16 , "AES");
		
		String texto = "Este es mi texto SECRETO bobazo adfgfdgdfsg sdfgdfsgdsfgdsf.";
		
		Cipher codificadorAES = 
				Cipher.getInstance("AES/CBC/PKCS5Padding");
		// ECB no es seguro para paquetes grandes (mayor que la longitud de clave)
		//para CBC el init es diferente
		String initVectorString = "encryptionIntVec";
		IvParameterSpec iv = new IvParameterSpec(initVectorString.getBytes());
		
		codificadorAES.init(Cipher.ENCRYPT_MODE, clave, iv);
		//convertimos String a array de Bytes
		byte[] textoBytes = texto.getBytes();
		
		for(byte b: textoBytes) {
			System.out.print(Integer.toHexString(0xFF & b));
		}
		System.out.println();
		
		byte[] textoEncriptado = codificadorAES.doFinal( textoBytes );
		for(byte b: textoEncriptado) {
			System.out.print(Integer.toHexString(0xFF & b));
		}
		System.out.println();
		
		Cipher decodificadorAES = 
				Cipher.getInstance("AES/CBC/PKCS5Padding");
		textoGenerador = "texto de clave....";
		clave = new SecretKeySpec(textoGenerador.getBytes(), 0 , 16 , "AES");
		decodificadorAES.init(Cipher.DECRYPT_MODE, clave, iv);
		byte[] textoDesencriptado = decodificadorAES.doFinal(textoEncriptado);
		for(byte b: textoDesencriptado) {
			System.out.print(Integer.toHexString(0xFF & b));
		}
		System.out.println();
		
		String textoClaro = new String(textoDesencriptado);
		System.out.println(textoClaro);
	}

}
