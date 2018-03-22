package dam.psp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SerEspejo {
	
	private static final String IPEspejo = "0.0.0.0";
	
	public static int PUERTO = 8000;
	public static String reverse(String s) {
		   return new StringBuilder(s).reverse().toString(); //No funciona
		}
	
	public static void main(String[] args) {
		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket(PUERTO, InetAddress.getByName(IPEspejo));	
			System.out.println("Emisor conectado al socket: "+socket.getLocalAddress());
			while (true) {
				DatagramPacket dato = new DatagramPacket(new byte[144], 144); 
				socket.receive(dato); 
				byte[] contenido = dato.getData();
				String s1 = new String(contenido);
				System.out.println("Mensaje:"+s1);
				
				
				List<String> myList = new ArrayList<String>(Arrays.asList(s1.split("")));
				Collections.sort(myList, Collections.reverseOrder());
				String s2 = "";
				for(int i=0; i<myList.size();i++)
					s2+=myList.get(i);
				
				
				byte[] contenidoAlterado=s2.getBytes();
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
	
}
