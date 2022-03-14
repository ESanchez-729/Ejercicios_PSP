import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HiloConexion extends Thread {
	
	Socket socket;
	private DataInputStream entrada;
	
	public HiloConexion(Socket socket) throws IOException {
		
		this.socket = socket;
		this.entrada = new DataInputStream(socket.getInputStream());
	}
	
	@Override
	public void run() {
		
		super.run();
		ObjectOutputStream salida;
		
		try {
			
			String mensaje = "";
			
			while(!mensaje.equals("quite")) {
				
				System.out.println(mensaje);
				mensaje = entrada.readUTF();
			}
		}
		
		catch (IOException e) {
			
			e.getMessage();
		}
	}
}
