import java.net.*;
import java.io.*;
public class UDPClient {
	public static void main(String cla[]) throws Exception {
		DatagramSocket servSock = new DatagramSocket();
		while(true) {
			byte recv[] = new byte[1024];
			byte send[] = new byte[1024];
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Message> ");
			String sent = input.readLine();
			send = sent.getBytes();
			InetAddress ip = InetAddress.getByName("localhost");
			DatagramPacket sendPkt = new DatagramPacket(send,send.length,ip,9090);
			servSock.send(sendPkt);
			DatagramPacket recvPkt = new DatagramPacket(recv,recv.length);
			servSock.receive(recvPkt);
			String msg = new String(recvPkt.getData());
			System.out.println("Server: "+msg);
		}
		
	}
}
