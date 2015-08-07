package classes.data.post_info;

import classes.service_locator.ServiceLocatorAdapter;
import abstractions.data.IPostInfo;
import org.junit.Assert;
import org.junit.Test;

public class ConvertPostInfoToByteArrayTest {

	@Test
	public void convertOriginPostInfoToByteArray_ConvertByteArrayToPostInfo_CompareWithOriginPostInfo() throws Exception {
		//Arrange
		long id = 1;
		long date = 2;
		long commentsCount = 3;
		long likesCount = 4;
		long repostsCount = 5;
		IPostInfo originPostInfo = ServiceLocatorAdapter.getInstance().getObject(IPostInfo.class);
		originPostInfo.setParams(id, date, commentsCount, likesCount, repostsCount);

		//Act
		byte[] bytes = originPostInfo.toByteArray();
		IPostInfo postInfo = ServiceLocatorAdapter.getInstance().getObject(IPostInfo.class);
		postInfo.parseByteArray(bytes);

		//Assert
		Assert.assertEquals(postInfo, originPostInfo);
	}

}