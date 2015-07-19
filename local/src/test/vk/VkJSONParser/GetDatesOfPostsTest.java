package test.vk.VkJSONParser;

import main.vk.VkJSONParser;
import org.junit.Assert;
import org.junit.Test;

public class GetDatesOfPostsTest {

	//Название для теста придумать не получилось.
	//Метод получает на вход строку в формате JSON.
	//Должен вернуть массив {0, 1000, 2000, 3000, 4000, 5000, 6000}
	@Test
	public void setVkWallToMethodAndCompareResultDatesWithGoalDates() throws Exception {
		// arrange
		String vkWall = "{\"response\":{\"items\":[{\"date\":0}, {\"date\":1}," +
				"{\"date\":2}, {\"date\":3}, {\"date\":4}, {\"date\":5}," +
				"{\"date\":6}]}}";

		// act
		long[] dates = VkJSONParser.getDatesOfPosts(vkWall);
		long[] goalDates = {0, 1000, 2000, 3000, 4000, 5000, 6000};

		// assert
		Assert.assertArrayEquals(dates, goalDates);
	}
}