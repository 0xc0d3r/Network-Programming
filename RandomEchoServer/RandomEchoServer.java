import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.IOException;
public class RandomEchoServer {
	int port;
	String msgs[] = {"Hello Client..!","Howdy..!","Hi, how are you? :)","Nice to see you.. :)","May I know your good name please? :)"};
	public RandomEchoServer(int port) {
		this.port = port;
	}
	public void letsChat() {
		try {
			ServerSocket serverSoc = new ServerSocket(port);
			while(true) {
				System.out.printf("\n[+] Waiting for incoming connections on port %d...\n",port);
				Socket sock = serverSoc.accept();
				System.out.println("[+] New connnection established with "+sock.getInetAddress().getHostAddress());
				PrintWriter writer = new PrintWriter(sock.getOutputStream());
				writer.println("You are connected to Server...");
				String msg = getMessage();//"Helloworld"
				writer.println(msg);
				writer.close();
				System.out.println("[+] Messege sent > " + msg);
			}
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	private String getMessage() {
		int random = (int) (Math.random() * msgs.length);
		return msgs[random];
	}
	public static void main(String cla[]) {
		RandomEchoServer res;
		if(cla.length > 0) {
			res = new RandomEchoServer(Integer.parseInt(cla[0]));
		}
		else {
			res = new RandomEchoServer(5000);
		}
		res.letsChat();
		
	}
}
