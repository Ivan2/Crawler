package vk.vk_date_post_count;

import org.junit.Assert;
import org.junit.Test;
import vk.VkDatePostCount;

public class CalcPostCountTest {

	//Название для теста придумать не получилось.
	//Метод получает на вход семь различных дней недели.
	//Должен вернуть массив из семи единиц.
	@Test
	public void setDateArrayToMethodAndCompareResultPostCountWithGoalPostCount() throws Exception {
		// arrange
		long[] dates = {0, 3600000*24, 3600000*48, 3600000*72, 3600000*96,
				3600000*120, 3600000*144};

		// act
		byte[] postCount = VkDatePostCount.calcPostCount(dates);
		byte[] goalPostCount = {1, 1, 1, 1, 1, 1, 1};

		// assert
		Assert.assertArrayEquals(postCount, goalPostCount);
	}
}