package classes.settings;

import classes.crawler.Control;
import classes.ioc.IoCAdapter;
import interfaces_abstracts.settings.ISettings;
import interfaces_abstracts.settings.ISettingsLoadManager;

import java.io.*;

public class SettingsLoadManagerFromFile implements ISettingsLoadManager {

	private final String FILE_NAME = "settings.config";

	public ISettings loadSettings() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader
					(new FileInputStream(FILE_NAME)));

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

			ISettings settings = IoCAdapter.getInstance().getISettingsObject();
			settings.setParams(
					intermediateDBName, intermediateDBUserName,
					intermediateDBPassword, intermediateDBHost,
					intermediateDBPort, finalDBName, finalDBUserName,
					finalDBPassword, finalDBHost, finalDBPort, rabbitMQHost
			);

			return settings;

		} catch (FileNotFoundException e) {
			Control.log(e.toString());
			return null;
		} catch (IOException e) {
			Control.log(e.toString());
			return null;
		}
	}
}
