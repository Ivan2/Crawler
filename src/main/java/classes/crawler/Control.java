package classes.crawler;

import abstractions.services.IServices;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Control extends Thread {

	private static String info = "";
	private static String error = "";

	public static void info(String className, String msg) {
		info += msg + "  <  " + className + "\n";
	}

	public static void error(String className, String msg) {
		error += msg + "  <  " + className + "\n";
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

					String msg = "error\n";

					if (ar[0].equals("exit")) {
						pw.println("exit\n");
						br.close();
						pw.close();
						socket.close();
						listener.close();
						System.exit(0);
					}

					if (ar[0].equals("info")) {
						msg = info + "\nendInfo\n";
						info = "";
					}

					if (ar[0].equals("error")) {
						msg = error + "\nendError\n";
						error = "";
					}

					if (ar[0].equals("status")) {
						msg = services.toString();
					}

					if (ar[0].equals("add"))
						if (ar.length == 2)
							if (isInt(ar[1]))
								msg = Boolean.toString(services.addService
										(Integer.parseInt(ar[1]))) + "\n";

					if (ar[0].equals("del"))
						if (ar.length == 2)
							if (isInt(ar[1]))
								msg = Boolean.toString(services.delService
										(Integer.parseInt(ar[1]))) + "\n";

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
