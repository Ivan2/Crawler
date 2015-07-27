package load_wall.vk;

import load_wall.json.IJSONParser;
import load_wall.wall_data.Data;
import org.json.JSONArray;
import org.json.JSONObject;

public class JSONWallParser implements IJSONParser{

	public Data parse(String wall) {
		long[] id;
		long[] comments;
		long[] likes;
		long[] reposts;
		long[] dates;

		JSONObject mainJsonObject = new JSONObject(wall);
		JSONObject responseJsonObject1 = mainJsonObject.getJSONObject("response");
		JSONArray postJsonArray = responseJsonObject1.getJSONArray("items");

		comments = new long[postJsonArray.length()];
		likes = new long[postJsonArray.length()];
		reposts = new long[postJsonArray.length()];
		id = new long[postJsonArray.length()];
		dates = new long[postJsonArray.length()];

		for (int i=0; i<postJsonArray.length(); i++){
			JSONObject post = postJsonArray.getJSONObject(i);

			comments[i] = post.getJSONObject("comments").getLong("count");
			likes[i] = post.getJSONObject("likes").getLong("count");
			reposts[i] = post.getJSONObject("reposts").getLong("count");
			id[i] = post.getLong("id");
			dates[i] = post.getLong("date")*1000;
		}

		return new Data(id, comments, likes, reposts, dates);
	}
}
