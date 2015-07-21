package vk;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class VkApi implements IVkApi {

	private String message = "https://api.vk.com/method/%s?%sv=5.34";
	private String appID = "30666517";

	public VkApi(String message, String appID){
		if (message != null)
			this.message = message;
		if (appID != null)
			this.appID = appID;
	}

	@Override
	public String getWall() {
		String params = "owner_id=-" + appID + "&count=100&";
		return sendMessage("wall.get", params);
	}

	private String sendMessage(String method, String params) {
		String result = "";
		try {
			URL url = new URL(String.format(message, method, params));
			InputStreamReader isr = new InputStreamReader(url.openStream(), "UTF-8");
			int value;
			while ((value = isr.read()) != -1) {
				result += (char) value;
			}
		} catch (IOException e){
			e.printStackTrace();
		}
		return result;
	}

}
