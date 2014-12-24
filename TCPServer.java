import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
public class TCPServer {
	public static void main(String cla[]) throws Exception {
		String recv,send,nick;
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("NickName> ");
		nick = input.readLine();
		ServerSocket servSock = new ServerSocket(9999);
		System.out.println("[+] Waiting for incoming connections on port 9999...");
		while(true) {
			Socket sock = servSock.accept();
			BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			DataOutputStream writer = new DataOutputStream(sock.getOutputStream());
			recv = reader.readLine();
			if(recv == null) {
				writer.writeBytes("You are disconnected from server...");
				continue;
			}
			System.out.println(recv);
			System.out.print("Message> ");
			send = input.readLine();
			writer.writeBytes(nick+" : "+send+'\n');
		}
	}
}
