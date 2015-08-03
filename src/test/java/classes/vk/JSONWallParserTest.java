package classes.vk;

import classes.ioc.IoCAdapter;
import interfaces_abstracts.data.IPostInfo;
import interfaces_abstracts.json.IJSONParser;
import org.junit.Assert;
import org.junit.Test;

public class JSONWallParserTest {

	@Test
	public void callParseMethodWithJSON_AndCompareResultWithExpected() throws Exception {
		//Arrange
		String jsonWall = "{\"response\":{\"items\":[{\"id\":1, \"date\":1,"
				+ "\"comments\":{\"count\":100}, \"likes\":{\"count\":200},"
				+ "\"reposts\":{\"count\":300}}, "

				+ "{\"id\":2, \"date\":2, \"comments\":{\"count\":400},"
				+ "\"likes\":{\"count\":500}, \"reposts\":{\"count\":600}}]}}";

		IPostInfo[] expectedPostInfo = new IPostInfo[2];

		expectedPostInfo[0] = IoCAdapter.getInstance().getIPostInfoObject();
		expectedPostInfo[1] = IoCAdapter.getInstance().getIPostInfoObject();

		expectedPostInfo[0].setParams(1, 1000, 100, 200, 300);
		expectedPostInfo[1].setParams(2, 2000, 400, 500, 600);

		IJSONParser parser = IoCAdapter.getInstance().getIJSONParserObject();

		//Act
		IPostInfo[] postInfo = parser.parse(jsonWall);

		//Assert
		Assert.assertEquals(postInfo[0], expectedPostInfo[0]);
		Assert.assertEquals(postInfo[1], expectedPostInfo[1]);
	}
}