package classes.crawler;

import classes.ioc.IoCAdapter;
import interfaces_abstracts.services.IServices;
import interfaces_abstracts.settings.ISettings;
import interfaces_abstracts.settings.ISettingsLoadManager;

public class Crawler {

	public static void main(String[] args) {
		IServices services = IoCAdapter.getInstance().getIServicesObject();

		new Control(services);

		ISettingsLoadManager settingsLoadManager =
				IoCAdapter.getInstance().getISettingsLoadManagerObject();
		ISettings settings = settingsLoadManager.loadSettings();

		String port = PortForwardManager.createConnection();
		Control.log("port = " + port);

		settings.setFinalDBPort(port);

		services.createServices();
	}

}
