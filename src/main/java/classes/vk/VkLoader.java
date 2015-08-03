package classes.vk;

import classes.crawler.Control;
import interfaces_abstracts.vk.IVkLoader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class VkLoader implements IVkLoader {

	private final String MESSAGE = "https://api.vk.com/method/%s?%sv=5.34";

	public String loadData(String method, String params) {
		String result = "";

		try {
			URL url = new URL(String.format(MESSAGE, method, params));
			InputStreamReader isr = new InputStreamReader(url.openStream(), "UTF-8");

			int value;
			while ((value = isr.read()) != -1) {
				result += (char) value;
			}

		} catch (IOException e){
			Control.log(e.toString());
		}

		return result;
	}

}
