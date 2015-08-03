import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Control {

	public static void main(String[] args) throws IOException {
		BufferedReader systemBr = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String s = systemBr.readLine();

			Socket socket = new Socket("127.0.0.1", 3002);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

			pw.println(s);
			String s2;
			while ((s2 = br.readLine()) != null)
				System.out.println(s2);

			pw.close();
			br.close();
			socket.close();
		}
	}

}
