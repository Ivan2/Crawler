package classes.vk;

import classes.service_locator.ServiceLocatorAdapter;
import abstractions.data.IPostInfo;
import abstractions.json.IJSONParser;
import org.json.JSONArray;
import org.json.JSONObject;

public class JSONWallParser implements IJSONParser{

	public IPostInfo[] parse(String wall) {
		IPostInfo[] result = null;

		JSONObject mainJsonObject = new JSONObject(wall);
		JSONObject responseJsonObject1 = mainJsonObject.getJSONObject("response");
		JSONArray postJsonArray = responseJsonObject1.getJSONArray("items");

		result = new IPostInfo[postJsonArray.length()];

		for (int i=0; i<postJsonArray.length(); i++){

			JSONObject post = postJsonArray.getJSONObject(i);
			long id = post.getLong("id");
			long date = post.getLong("date")*1000;
			long commentsCount = post.getJSONObject("comments").getLong("count");
			long likesCount = post.getJSONObject("likes").getLong("count");
			long repostsCount = post.getJSONObject("reposts").getLong("count");

			result[i] = ServiceLocatorAdapter.getInstance().getObject(IPostInfo.class);
			result[i].setParams(id, date, commentsCount, likesCount, repostsCount);
		}

		return result;
	}
}
