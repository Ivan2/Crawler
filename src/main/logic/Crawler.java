package main.logic;

import main.store.*;
import main.vk.IVkApi;
import main.vk.VkApi;
import main.vk.VkDatePostCount;
import main.vk.VkJSONParser;

public class Crawler {

	public static void main(String[] args) throws Exception{
		new Crawler();
	}

	public Crawler(){
		IVkApi vkApi = new VkApi(null, null);
		Log.log("Запрос данных у vk.com");
		String res = vkApi.getWall();

		Log.log("Подсчет количества постов по датам");
		long[] dates = VkJSONParser.getDatesOfPosts(res);
		byte[] postCount = VkDatePostCount.calcPostCount(dates);

		Log.log("Понедельник: " + postCount[1]);
		Log.log("Вторник: " + postCount[2]);
		Log.log("Среда: " + postCount[3]);
		Log.log("Четверг: " + postCount[4]);
		Log.log("Пятница: " + postCount[5]);
		Log.log("Суббота: " + postCount[6]);
		Log.log("Воскресенье: " + postCount[0]);

		Log.log("Формирование файла");
		LoadGitSettings loadGitSettings = new LoadGitSettings();
		GitSettings gitSettings = loadGitSettings.loadGitSettings();
		ISaveManager saveManager = new SaveInFileManager(gitSettings);
		saveManager.save(postCount);

		Log.log("Pushing");
		IGitManager gitManager = new GitManager(gitSettings);
		gitManager.sendFile();
		Log.log("Готово");
	}

}
