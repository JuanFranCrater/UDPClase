package dam.psp.bin;

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

import sun.java2d.SunGraphics2D;

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
				System.out.println("Longitud: "+dato.getLength());
				String mensaje=new String(dato.getData(), 0, dato.getLength());
				mensaje=new StringBuilder(mensaje).reverse().toString().toUpperCase();
				System.out.println(mensaje);
				System.out.println(mensaje.length());
			
				DatagramPacket datoReves = new DatagramPacket(mensaje.getBytes(), dato.getLength(), dato.getSocketAddress());
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
