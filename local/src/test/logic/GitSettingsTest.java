package test.logic;

import main.logic.GitSettings;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class GitSettingsTest {

	private static GitSettings gitSettings;

	@BeforeClass
	public static void createGitSettings(){
		gitSettings = new GitSettings("directory", "login", "password");
	}

	@Test
	public void compareGitDirectoryWithGoalGetDirectory() throws Exception {
		Assert.assertEquals(gitSettings.getGitDirectory(), "directory");
	}

	@Test
	public void compareLoginWithGoalLogin() throws Exception {
		Assert.assertEquals(gitSettings.getLogin(), "login");
	}

	@Test
	public void comparePasswordWithGoalPassword() throws Exception {
		Assert.assertEquals(gitSettings.getPassword(), "password");
	}
}