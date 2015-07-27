package load_wall.vk;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class VkWallLoader implements IVkWallLoader{

	//private final String ID = "30666517";
	private final byte POST_COUNT = 100;
	private final String METHOD = "wall.get";
	private final String PARAMS = "owner_id=-%s&count=%d&";
	private final String MESSAGE = "https://api.vk.com/method/%s?%sv=5.34";

	public String loadWall(String id) {
		/*return "{\"response\":{\"items\":[{\"id\":1, \"comments\":{\"count\":2},"+
		"\"likes\":{\"count\":3}, \"reposts\":{\"count\":4}}, {\"id\":5, \"comments\":{\"count\":6},"+
		"\"likes\":{\"count\":7}, \"reposts\":{\"count\":8}}]}}";*/
		String params = String.format(PARAMS, id, POST_COUNT);
		String result = "";

		try {
			URL url = new URL(String.format(MESSAGE, METHOD, params));
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
