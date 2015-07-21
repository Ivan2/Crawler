package store;

import logic.GitSettings;

import java.io.*;

public class LoadGitSettings implements ILoadGitSettings {

	private String settingsFileName = "settings.txt";
	private String gitDirectory;
	private String login;
	private String password;

	public void setSettingsFileName(String settingsFileName){
		this.settingsFileName = settingsFileName;
	}

	public GitSettings loadGitSettings() {
		File file = new File(settingsFileName);
		if (!file.exists()) {
			new FileNotFoundException().printStackTrace();
		} else {
			try {
				FileInputStream fis = new FileInputStream(file);
				DataInputStream dis = new DataInputStream(fis);

				gitDirectory = dis.readLine();
				login = dis.readLine();
				password = dis.readLine();

				dis.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new GitSettings(gitDirectory, login, password);
	}

}
