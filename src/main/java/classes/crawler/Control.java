package classes.crawler;

import interfaces_abstracts.services.IServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Control extends Thread {

	private static String log = "";

	synchronized public static void log(String msg) {
		log += msg + "\n";
	}

	private boolean isInt(String s) {
		for (char ch : s.toCharArray())
			if (!Character.isDigit(ch))
				return false;
		return true;
	}

	private IServices services;

	public Control(IServices services) {
		this.services = services;
		setDaemon(true);
		start();
	}

	@Override
	public void run() {
		try {
			ServerSocket listener = new ServerSocket(3002);
			while (true) {
				try {
					Socket socket = listener.accept();
					BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

					String s = br.readLine();
					String[] ar = s.split(" ");

					String msg = "error";

					if (ar[0].equals("exit")) {
						pw.println("exit");
						br.close();
						pw.close();
						socket.close();
						listener.close();
						System.exit(0);
					}

					if (ar[0].equals("log")) {
						msg = log + "\nendLog\n";
						log = "";
					}

					if (ar[0].equals("add"))
						if (ar.length == 2)
							if (isInt(ar[1]))
								msg = Boolean.toString(services.addService(Integer.parseInt(ar[1])));

					pw.println(msg);

					br.close();
					pw.close();
					socket.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
