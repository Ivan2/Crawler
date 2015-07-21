package vk;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class VkJSONParser {

	public static long[] getDatesOfPosts(String vkWall) {
		long[] result = null;
		try {
			JSONObject mainJsonObject = (JSONObject) (new JSONParser().parse(vkWall));
			JSONObject responseJsonObject1 = (JSONObject)mainJsonObject.get("response");
			JSONArray postJsonArray = (JSONArray)responseJsonObject1.get("items");
			result = new long[postJsonArray.size()];
			for (int i=0; i<postJsonArray.size(); i++){
				JSONObject post = (JSONObject)postJsonArray.get(i);
				result[i] = (Long)post.get("date")*1000;
			}
		} catch (ParseException e){
			e.printStackTrace();
		}
		return result;
	}

}
