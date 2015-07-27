package settings;

import java.io.*;

public class SettingsLoadManagerFromFile implements ISettingsLoadManager {

	private final String FILE_NAME = "settings.config";

	public Settings load() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader
					(new FileInputStream(FILE_NAME)));

			String userName = br.readLine();
			String password = br.readLine();
			String appName = br.readLine();

			return new Settings(userName, password, appName);

		} catch (FileNotFoundException e) {
			System.out.println(e);
			return null;
		} catch (IOException e) {
			System.out.println(e);
			return null;
		}
	}
}
