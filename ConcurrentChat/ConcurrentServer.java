import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;

public class ConcurrentServer {
	public static int port = 9090;
	public static void main(String cla[]) throws Exception {
		System.out.println("[+] Waiting for incoming connections on port "+port+"...");
		int clientNumber = 1;
		ServerSocket servSock = new ServerSocket(port);
		try {
			while(true) {
				new Server(servSock.accept(),clientNumber++).start();
			}
		}
		finally {
			servSock.close();
		}
		
	}
	private static class Server extends Thread {
		private Socket sock;
		private int clientNumber;
		public Server(Socket sock, int cliNum)  {
			this.sock = sock;
			clientNumber = cliNum;
			System.out.println("[+] New connection established with client#"+clientNumber+"...");
		}
		public void run() {
			try {
				BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in));
				BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				PrintWriter writer = new PrintWriter(sock.getOutputStream(),true);
			
				writer.println("\t\t\t\t\tWELCOME TO CHAT SERVER");
				writer.println("\t\t\t\t*************************************");
				writer.println("You're client#"+clientNumber);
				writer.println("Enter \"bye\" to exit chat...");
				String recv;
				while(true) {
					recv = reader.readLine();
					System.out.println("Client#"+clientNumber+": "+recv);
					if(recv.equals("bye")||recv == null) {
						writer.println("You are disconnected from server...");
						break;
					}
					System.out.print("Message to #"+clientNumber+"> ");
					String send = fromUser.readLine();
					writer.println(send);
				}
			}
			catch(IOException e) {
				System.out.println("[-] Error handling client#"+clientNumber+"...");
			}
			finally {
				try{
					sock.close();
				}
				catch(IOException e) {
					System.out.println("[-] Error: Couldn't close socket..");
				}
				System.out.println("[+] Connection with client#"+clientNumber+" is closed...");
			}
		}
	}
}
