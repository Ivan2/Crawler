package test.store.SaveInFileManager;

import main.logic.GitSettings;
import main.store.SaveInFileManager;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;

public class SaveTest {

	@Test
	public void setPostCountToMethodAndComparePostCountInFileWithGoalPostCount() throws Exception {
		// arrange
		byte[] goalPostCount = {1, 2, 3, 4, 5, 6, 7};
		String gitDirectory = "D:";
		GitSettings gitSettings = new GitSettings(gitDirectory, null, null);
		SaveInFileManager saveInFileManager = new SaveInFileManager(gitSettings);

		// act
		saveInFileManager.save(goalPostCount);

		// assert
		FileInputStream fis = new FileInputStream(gitDirectory+"/data");
		byte[] postCount = new byte[7];
		fis.read(postCount, 0, 7);
		Assert.assertArrayEquals(postCount, goalPostCount);
	}

}