package db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PortForwardManager {

	public static String createConnection() {
		try {
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec("cmd /c rhc port-forward -a crawler");
			BufferedReader br = new BufferedReader
					(new InputStreamReader(process.getInputStream()));
			String str;
			while ((str = br.readLine()) != null) {
				//System.out.println(str);
				if (str.length()>21)
					if (str.substring(0, 21).equals("postgresql 127.0.0.1:"))
						return str.substring(21, 25);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		return "0";
	}

}
