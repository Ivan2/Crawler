package classes.crawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PortForwardManager {

	private static String port;

	public static String createConnection() {
		port = "0";
		Thread thread = new Thread(new Runnable() {
			public void run() {
				while (true)
					try {
						Runtime runtime = Runtime.getRuntime();
						Process process = runtime.exec("cmd /c rhc port-forward -a crawler");
						BufferedReader br = new BufferedReader
								(new InputStreamReader(process.getInputStream()));
						String str;
						while ((str = br.readLine()) != null) {
							Control.log(str);
							if (str.length() > 21)
								if (str.substring(0, 21).equals("postgresql 127.0.0.1:")) {
									port = str.substring(21, 25);
									return;
								}
						}
						Thread.sleep(10000);
					} catch (Exception e) {
						Control.log(e.toString());
					}
			}
		});
		thread.setDaemon(true);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			Control.log(e.toString());
		}
		return port;
	}

}
