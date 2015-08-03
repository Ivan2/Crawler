package classes.data.post_info;

import classes.ioc.IoCAdapter;
import interfaces_abstracts.data.IPostInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PostInfoGettersTest {

	private IPostInfo postInfo;
	private long id = 1;
	private long date = 2;
	private long commentsCount = 3;
	private long likesCount = 4;
	private long repostsCount = 5;

	@Before
	public void setUp() throws Exception {
		postInfo = IoCAdapter.getInstance().getIPostInfoObject();
		postInfo.setParams(id, date, commentsCount, likesCount, repostsCount);
	}

	@Test
	public void compareReturnedIDWithOriginalID() throws Exception {
		//Arrange

		//Act
		long id = postInfo.getId();

		//Assert
		Assert.assertEquals(id, this.id);
	}

	@Test
	public void compareReturnedDateWithOriginalDate() throws Exception {
		//Arrange

		//Act
		long date = postInfo.getDate();

		//Assert
		Assert.assertEquals(date, this.date);
	}

	@Test
	public void compareReturnedCommentsCountWithOriginalCommentsCount() throws Exception {
		//Arrange

		//Act
		long commentsCount = postInfo.getCommentsCount();

		//Assert
		Assert.assertEquals(commentsCount, this.commentsCount);
	}

	@Test
	public void compareReturnedLikesCountWithOriginalLesCount() throws Exception {
		//Arrange

		//Act
		long likesCount = postInfo.getLikesCount();

		//Assert
		Assert.assertEquals(likesCount, this.likesCount);
	}

	@Test
	public void compareReturnedRepostsCountWithOriginalRepostsCount() throws Exception {
		//Arrange

		//Act
		long repostsCount = postInfo.getRepostsCount();

		//Assert
		Assert.assertEquals(repostsCount, this.repostsCount);
	}
}