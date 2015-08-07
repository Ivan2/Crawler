package classes.service_locator;

import abstractions.data.IPostInfo;
import abstractions.date.IDateTime;
import abstractions.db.FinalDB;
import abstractions.db.IntermediateDB;
import abstractions.json.IJSONParser;
import abstractions.openshift.IDBConnectionControl;
import abstractions.services.IServices;
import abstractions.settings.ISettings;
import abstractions.settings.ISettingsLoadManager;
import abstractions.vk.IVkLoader;
import abstractions.vk.IVkWallLoader;
import classes.data.PostInfo;
import classes.date.DateTime;
import classes.db.FinalPostgresQLDB_OS;
import classes.db.IntermediatePostgresQLDB;
import classes.openshift.DBConnectionControl;
import classes.services.Services;
import classes.settings.Settings;
import classes.settings.SettingsLoadManagerFromFile;
import classes.vk.JSONWallParser;
import classes.vk.VkLoader;
import classes.vk.VkWallLoader;

public class ServiceLocatorAdapter extends ServiceLocator {

	private static ServiceLocatorAdapter instance = null;

	public static ServiceLocatorAdapter getInstance() {
		if (instance == null)
			instance = new ServiceLocatorAdapter();
		return instance;
	}

	private ServiceLocatorAdapter() {
		super();
		add(IPostInfo.class, PostInfo.class, false);
		add(IDateTime.class, DateTime.class, false);
		add(IntermediateDB.class, IntermediatePostgresQLDB.class, true);
		add(FinalDB.class, FinalPostgresQLDB_OS.class, true);
		add(IDBConnectionControl.class, DBConnectionControl.class, false);
		add(IServices.class, Services.class, true);
		add(ISettings.class, Settings.class, true);
		add(ISettingsLoadManager.class, SettingsLoadManagerFromFile.class, false);
		add(IJSONParser.class, JSONWallParser.class, false);
		add(IVkLoader.class, VkLoader.class, false);
		add(IVkWallLoader.class, VkWallLoader.class, false);
	}

}
