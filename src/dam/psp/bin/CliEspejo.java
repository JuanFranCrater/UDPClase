package dam.psp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class CliEspejo {
	public static final int PuertoEmisor = 8501;
	public static final int PuertoReceptor = 8000;
	public static final String IPEmisor = "0.0.0.0";
	byte[] contenidoEnvio = new byte[144];
	byte[] contenidoRecibido = new byte[144];
	public CliEspejo() {
		DatagramSocket emisor;
		DatagramPacket dgp;
		InetAddress IPLocal;
		InetAddress IPRemota;
		try {
			
			IPLocal = InetAddress.getByName("0.0.0.0");
			IPRemota = InetAddress.getByName(IPEmisor);
			emisor = new DatagramSocket(PuertoEmisor,IPLocal); 
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			contenidoEnvio=bufferedReader.readLine().getBytes();
			dgp = new DatagramPacket(contenidoEnvio, contenidoEnvio.length, IPRemota, PuertoReceptor);
			emisor.send(dgp);
			DatagramPacket dato = new DatagramPacket(new byte[144], 144); 
			emisor.receive(dato);
			contenidoRecibido=dato.getData();
			System.out.println(new String(contenidoRecibido));
		} catch (UnknownHostException | SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args) {
		new CliEspejo();
	}
}
