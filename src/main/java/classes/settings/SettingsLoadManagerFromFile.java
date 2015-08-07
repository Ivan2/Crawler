package classes.settings;

import classes.crawler.Control;
import classes.service_locator.ServiceLocatorAdapter;
import abstractions.settings.ISettings;
import abstractions.settings.ISettingsLoadManager;

import java.io.*;

public class SettingsLoadManagerFromFile implements ISettingsLoadManager {

	private final String FILE_NAME = "settings.config";

	public ISettings loadSettings() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader
					(new FileInputStream(System.getProperty("user.dir")+"/"+FILE_NAME)));

			String intermediateDBName = br.readLine();
			String intermediateDBUserName = br.readLine();
			String intermediateDBPassword = br.readLine();
			String intermediateDBHost = br.readLine();
			String intermediateDBPort = br.readLine();
			String finalDBName = br.readLine();
			String finalDBUserName = br.readLine();
			String finalDBPassword = br.readLine();
			String finalDBHost = br.readLine();
			String finalDBPort = br.readLine();
			String rabbitMQHost = br.readLine();

			ISettings settings = ServiceLocatorAdapter.getInstance().getObject(ISettings.class);
			settings.setParams(
					intermediateDBName, intermediateDBUserName,
					intermediateDBPassword, intermediateDBHost,
					intermediateDBPort, finalDBName, finalDBUserName,
					finalDBPassword, finalDBHost, finalDBPort, rabbitMQHost
			);

			return settings;

		} catch (FileNotFoundException e) {
			Control.error(getClass().getName(), e.toString());
			return null;
		} catch (IOException e) {
			Control.error(getClass().getName(), e.toString());
			return null;
		}
	}
}
