import java.io.*;
import java.net.*;
class TCPServer {
	public static void main (String argv[]) throws Exception {
		String clientMsg;
		String capitalizedSentence;
		ServerSocket welcomeSocket = new ServerSocket (6789);
		while (true) {	
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			clientMsg = inFromClient.readLine();
			System.out.println("Client : "+clientMsg);
			capitalizedSentence = clientMsg.toUpperCase()+'\n';
			outToClient.writeBytes(capitalizedSentence);
			}
		}	
	}
