package classes.openshift;

import classes.crawler.Control;
import abstractions.openshift.IDBConnectionControl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DBConnectionControl implements IDBConnectionControl {

	private String port;

	public String connect() {
		port = "0";
		Control.info(getClass().getName(), "DB connect...");
		createConnection();
		return port;
	}

	private void createConnection() {
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
							Control.info(getClass().getName(), str);
							if (str.length() > 21)
								if (str.substring(0, 21).equals("postgresql 127.0.0.1:")) {
									port = str.substring(21, 25);
									return;
								}
						}
					} catch (Exception e) {
						Control.error(getClass().getName(), e.toString());
					} finally {
						try {
							Thread.sleep(30000);
						} catch (InterruptedException e) {
							Control.error(getClass().getName(), e.toString());
						}
					}
			}
		});
		thread.setDaemon(true);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			Control.error(getClass().getName(), e.toString());
		}
	}

}
