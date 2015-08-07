package classes.crawler;

import abstractions.services.IServices;
import abstractions.settings.ISettingsLoadManager;
import classes.service_locator.ServiceLocatorAdapter;

public class Crawler {

	public static void main(String[] args) {
		IServices services = ServiceLocatorAdapter.getInstance().getObject(IServices.class);

		new Control(services);

		ISettingsLoadManager settingsLoadManager =
				ServiceLocatorAdapter.getInstance().getObject(ISettingsLoadManager.class);
		settingsLoadManager.loadSettings();

		services.createServices();
	}


}
