import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ssl.SSLServerSocketFactory;

public class Servidor {

	
	public static void main(String[] args) {
		
		//Almacen del certificado de servidor
		System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\Willi\\SSL\\serverKey.p12");
		
		//Contraseña del almacen del certificado del servidor
		System.setProperty("javax.net.ssl.keyStorePassword", "clave123");
		
		//Almacen de certificados en que confío (en este caso tengo el mismo almacen para
		//el certificado propio y el certificado de los sitios en que confio)
		System.setProperty("javax.net.ssl.trustStore", "C:\\Users\\Willi\\SSL\\serverKey.p12");
		
		//Contraseña del almacen de los certificados en que confío
		System.setProperty("javax.net.ssl.trustStorePassword", "clave123");
		
		//creamos el socket seguro
		int puerto = 10000;
		SSLServerSocketFactory serverFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		
		try {
			
			ServerSocket serverSocket = serverFactory.createServerSocket(puerto);
		
			while(true) {
				
				Socket socket = serverSocket.accept();
				
				System.out.println("Se ha conectado un cliente");
				HiloConexion hilo = new HiloConexion(socket);
				hilo.start();
				
				System.out.print(hilo.getName());
			}
		} 
		
		catch (IOException e) {
			
			System.out.println(e.getMessage());
		}
	}
}
