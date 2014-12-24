import java.net.*;
import java.io.*;
public class UDPServer {
	public static void main(String cla[]) throws Exception {
		DatagramSocket servSock = new DatagramSocket(9090);
		while(true) {
			byte recv[] = new byte[1024];
			byte send[] = new byte[1024];
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			DatagramPacket recvPkt = new DatagramPacket(recv,recv.length);
			servSock.receive(recvPkt);
			String msg = new String(recvPkt.getData());
			InetAddress ip = recvPkt.getAddress();
			int port = recvPkt.getPort();
			System.out.println("Client: "+msg);
			System.out.print("Message> ");
			send = input.readLine().getBytes();
			DatagramPacket sendPkt = new DatagramPacket(send,send.length,ip,port);
			servSock.send(sendPkt);
		}
	}
}
