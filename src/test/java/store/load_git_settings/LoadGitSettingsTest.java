package store.load_git_settings;

import logic.GitSettings;
import org.junit.Assert;
import org.junit.Test;
import store.LoadGitSettings;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class LoadGitSettingsTest {

	@Test
	public void writeGitSettingsToFile_LoadGitSettings_AndCompareWithOriginal() throws Exception {
		// arrange
		String fileName = "settingsTest.txt";
		LoadGitSettings loadGitSettings = new LoadGitSettings();
		loadGitSettings.setSettingsFileName(fileName);
		GitSettings originalGitSettings = new GitSettings("gitDirectory", "login", "password");

		File file = new File(fileName);
		if (file.exists())
			file.delete();
		file.createNewFile();

		FileOutputStream fos = new FileOutputStream(file);
		DataOutputStream dos = new DataOutputStream(fos);
		dos.writeBytes(originalGitSettings.getGitDirectory()+"\n");
		dos.writeBytes(originalGitSettings.getLogin()+"\n");
		dos.writeBytes(originalGitSettings.getPassword()+"\n");
		dos.close();
		fos.close();

		// act
		GitSettings gitSettings = loadGitSettings.loadGitSettings();
		file.delete();

		// assert
		Assert.assertEquals(gitSettings, originalGitSettings);
	}
}