import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class TCPClient {
	public static void main(String cla[]) throws Exception {
		String recv,send,nick;
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("NickName> ");
		nick = input.readLine();
		String regex = "[a-zA-Z]+";
		while(true) {
			Socket sock = new Socket("localhost",9999);
			BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			DataOutputStream writer = new DataOutputStream(sock.getOutputStream());
			Pattern pattern = Pattern.compile(regex);
			System.out.print("Message> "); 
			send = input.readLine();
			Matcher matcher = pattern.matcher(send);
			if(!matcher.matches()) {
				System.out.println(reader.readLine());
				writer.writeBytes(nick+" : Connection terminated by client...");
				System.out.println("[-] Error: Invalid characters found and connection closed...");
				sock.close();
				break;
			}
			writer.writeBytes(nick+" : "+send+'\n');
			if(send.equals("bye")) {
				System.out.println(reader.readLine());
				sock.close();
				break;
			}
			recv = reader.readLine();
			System.out.println(recv);
		}
	}
}
