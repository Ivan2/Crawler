package crawler;

import db.IDB;
import db.PortForwardManager;
import db.PostgresQLDB;
import services.LoadDataFromWallService;
import services.LoadIDFromDBService;
import services.LoadWallFromVkService;
import settings.ISettingsLoadManager;
import settings.Settings;
import settings.SettingsLoadManagerFromFile;

public class Crawler {

	public static void main(String[] args) throws Exception{
		new Crawler();
	}

	public Crawler() throws Exception{
		ISettingsLoadManager settingsLoadManager = new SettingsLoadManagerFromFile();
		Settings settings = settingsLoadManager.load();

		String port = PortForwardManager.createConnection();
		System.out.println("port = "+port);
		if (port.equals(0))
			return;

		IDB db = new PostgresQLDB("127.0.0.1", port, settings);
		db.connect();

		new LoadIDFromDBService(db);

		new LoadWallFromVkService();

		new LoadDataFromWallService(db);
	}

}
