package net;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataLoader implements IDataLoader {
	
	private final String urlString = "https://raw.githubusercontent.com/Ivan2/Crawler/master/data";
	
	@Override
	public byte[] load() {
		try{
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			byte[] result = new byte[is.available()];
			for (int i=0; i<result.length; i++)
				result[i] = (byte)is.read();
			is.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
