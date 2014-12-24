import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
public class RandomEchoClient {
	String host;
	int port;
	public RandomEchoClient(String host, int port) {
		this.host = host;
		this.port = port;
	}
	public void letsChat() {
		try {
			Socket s = new Socket(host,port);
			InputStreamReader reader = new InputStreamReader(s.getInputStream());
			BufferedReader br = new BufferedReader(reader);
			System.out.println(br.readLine());
			String msg = br.readLine();
			System.out.println("[+] New Message > "+msg);
			br.close();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	public static void main(String cla[]) {
		RandomEchoClient rec;
		if(cla.length >= 2) {
			String host = cla[0];
			int port = Integer.parseInt(cla[1]);
			rec = new RandomEchoClient(host,port);
		}
		else {
			rec = new RandomEchoClient("localhost",5000);
		}
		rec.letsChat();
	}
}
