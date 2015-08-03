package classes.data.post_info;

import classes.ioc.IoCAdapter;
import interfaces_abstracts.data.IPostInfo;
import org.junit.Assert;
import org.junit.Test;

public class getDayOfWeekTest {

	@Test
	public void setDate1000_ReturnDayOfWeek4() throws Exception {
		//Arrange
		long id = 1;
		long date = 1000;
		long commentsCount = 3;
		long likesCount = 4;
		long repostsCount = 5;
		IPostInfo postInfo = IoCAdapter.getInstance().getIPostInfoObject();
		postInfo.setParams(id, date, commentsCount, likesCount, repostsCount);

		//Act
		byte dayOfWeek = postInfo.getDayOfWeek();

		//Assert
		Assert.assertEquals(dayOfWeek, 4);
	}

	@Test
	public void setDate75599999_ReturnDayOfWeek4() throws Exception {
		//Arrange
		long id = 1;
		long date = 75599999;//1000*(21*3600)-1
		long commentsCount = 3;
		long likesCount = 4;
		long repostsCount = 5;
		IPostInfo postInfo = IoCAdapter.getInstance().getIPostInfoObject();
		postInfo.setParams(id, date, commentsCount, likesCount, repostsCount);

		//Act
		byte dayOfWeek = postInfo.getDayOfWeek();

		//Assert
		Assert.assertEquals(dayOfWeek, 4);
	}


	@Test
	public void setDate75600000_ReturnDayOfWeek5() throws Exception {
		//Arrange
		long id = 1;
		long date = 75600000;//1000*(21*3600)
		long commentsCount = 3;
		long likesCount = 4;
		long repostsCount = 5;
		IPostInfo postInfo = IoCAdapter.getInstance().getIPostInfoObject();
		postInfo.setParams(id, date, commentsCount, likesCount, repostsCount);

		//Act
		byte dayOfWeek = postInfo.getDayOfWeek();

		//Assert
		Assert.assertEquals(dayOfWeek, 5);
	}

}