package classes.data.post_info;

import classes.ioc.IoCAdapter;
import interfaces_abstracts.data.IPostInfo;
import org.junit.Assert;
import org.junit.Test;

public class ConvertPostInfoToByteArrayTest {

	@Test
	public void createPostInfo_ConvertToByteArray_ConvertToPostInfoBack_CompareWithOriginPostInfo() throws Exception {
		//Arrange
		long id = 1;
		long date = 2;
		long commentsCount = 3;
		long likesCount = 4;
		long repostsCount = 5;
		IPostInfo originPostInfo = IoCAdapter.getInstance().getIPostInfoObject();
		originPostInfo.setParams(id, date, commentsCount, likesCount, repostsCount);

		//Act
		byte[] bytes = originPostInfo.toByteArray();
		IPostInfo postInfo = IoCAdapter.getInstance().getIPostInfoObject();
		postInfo.parseByteArray(bytes);

		//Assert
		Assert.assertEquals(postInfo, originPostInfo);
	}

}