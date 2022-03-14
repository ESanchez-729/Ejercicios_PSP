import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.net.ssl.SSLSocketFactory;

public class Cliente extends Thread{

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		String ip = "192.168.1.35";
		int puerto = 10000;
		
		//almacen del certificado de cliente
		System.setProperty("javax.net.ssl.keyStore", "/home/alumno/SSL/clientKey.p12");
		 
		//contraseña del almacen del certificado del cliente
		System.setProperty("javax.net.ssl.keyStorePassword", "clave321");
		
		//almacen de certificados en que confío (en este caso tengo el mismo almacen para
		//el certificado propio y el certificado de los sitios en que confio)
		System.setProperty("javax.net.ssl.trustStore", "/home/alumno/SSL/clientKey.p12");
		 
		//contraseña del almacen de los certificados en que confío 
		System.setProperty("javax.net.ssl.trustStorePassword", "clave321");	 
		System.setProperty("javax.net.ssl.trustStoreType","pkcs12");
		
		SSLSocketFactory sslFactory = (SSLSocketFactory) SSLSocketFactory.getDefault(); 
		Socket socket = sslFactory.createSocket(ip, puerto);
		
		DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
		
		Scanner teclado = new Scanner(System.in);
		
		while (true) {
			
			salida.writeUTF(teclado.nextLine());
		}
	}
}
