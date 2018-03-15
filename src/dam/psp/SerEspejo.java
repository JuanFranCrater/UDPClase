package dam.psp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SerEspejo {
	
	private static final String IPEspejo = "0.0.0.0";

	DatagramSocket socket;
	
	public static int PUERTO = 8000;
	
	public SerEspejo() {
		
		try {
			socket = new DatagramSocket(PUERTO, InetAddress.getByName(IPEspejo));	
			System.out.println("Emisor conectado al socket: "+socket.getLocalAddress());
			while (true) {
				DatagramPacket dato = new DatagramPacket(new byte[144], 144); 
				socket.receive(dato); 
				byte[] contenido = dato.getData();
				String s1 = new String(contenido);
				System.out.println("Mensaje:"+s1);
				System.out.println(new StringBuilder(s1).reverse().toString());
				s1="mensajeAlterado";
				byte[] contenidoAlterado=s1.getBytes();
				DatagramPacket datoReves = new DatagramPacket(contenidoAlterado, contenidoAlterado.length, dato.getSocketAddress());
				socket.send(datoReves);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) { 
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		} finally {
			if (socket != null) {
				socket.close();
			}
		}
	}
	public static String reverse(String s) {
		   return new StringBuilder(s).reverse().toString();
		}
	
	public static void main(String[] args) {
		new SerEspejo();
	}
	
}