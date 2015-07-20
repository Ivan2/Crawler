package main.store;

import main.logic.GitSettings;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveInFileManager implements ISaveManager {

	private GitSettings gitSettings;

	public SaveInFileManager(GitSettings gitSettings){
		this.gitSettings = gitSettings;
	}

	@Override
	public void save(byte[] postCount){
		try {
			File file = new File(gitSettings.getGitDirectory()+"/data");
			if (file.exists())
				file.delete();
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(postCount);
			fos.close();
		} catch (IOException e){
			e.printStackTrace();
		}

	}
}
