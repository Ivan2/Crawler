package classes.services;

import classes.service_locator.ServiceLocatorAdapter;
import abstractions.data.IPostInfo;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CalcAverageCountTest {

	@Test
	public void setPostInfoList_CalcAverageCount_CompareResultAverageCountWithExpected1() throws Exception {
		//Arrange
		List<IPostInfo> postInfoList = new ArrayList<IPostInfo>();

		postInfoList.add(ServiceLocatorAdapter.getInstance().getObject(IPostInfo.class));
		postInfoList.add(ServiceLocatorAdapter.getInstance().getObject(IPostInfo.class));

		postInfoList.get(0).setParams(1, 1, 50, 260, 800);
		postInfoList.get(1).setParams(2, 2, 250, 140, 100);

		long[] expectedCommentsCount = {0, 0, 0, 0, 150, 0, 0};
		long[] expectedLikesCount = {0, 0, 0, 0, 200, 0, 0};
		long[] expectedRepostsCount = {0, 0, 0, 0, 450, 0, 0};

		long[] commentsCount = new long[7];
		long[] likesCount = new long[7];
		long[] repostsCount = new long[7];

		//Act
		CalcAverageCountManager.calcAverageCount(postInfoList, commentsCount,
				likesCount, repostsCount);

		//Assert
		Assert.assertArrayEquals(commentsCount, expectedCommentsCount);
		Assert.assertArrayEquals(likesCount, expectedLikesCount);
		Assert.assertArrayEquals(repostsCount, expectedRepostsCount);
	}

	@Test
	public void setPostInfoList_CalcAverageCount_CompareResultAverageCountWithExpected2() throws Exception {
		//Arrange
		List<IPostInfo> postInfoList = new ArrayList<IPostInfo>();

		postInfoList.add(ServiceLocatorAdapter.getInstance().getObject(IPostInfo.class));
		postInfoList.add(ServiceLocatorAdapter.getInstance().getObject(IPostInfo.class));

		postInfoList.get(0).setParams(1, 1, 50, 200, 500);
		postInfoList.get(1).setParams(2, 75600000, 250, 200, 400);

		long[] expectedCommentsCount = {0, 0, 0, 0, 50, 250, 0};
		long[] expectedLikesCount = {0, 0, 0, 0, 200, 200, 0};
		long[] expectedRepostsCount = {0, 0, 0, 0, 500, 400, 0};

		long[] commentsCount = new long[7];
		long[] likesCount = new long[7];
		long[] repostsCount = new long[7];

		//Act
		CalcAverageCountManager.calcAverageCount(postInfoList, commentsCount,
				likesCount, repostsCount);

		//Assert
		Assert.assertArrayEquals(commentsCount, expectedCommentsCount);
		Assert.assertArrayEquals(likesCount, expectedLikesCount);
		Assert.assertArrayEquals(repostsCount, expectedRepostsCount);
	}
}