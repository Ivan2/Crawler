package classes.services;

import classes.crawler.Control;
import classes.ioc.IoCAdapter;
import interfaces_abstracts.db.FinalDB;
import interfaces_abstracts.db.IntermediateDB;
import interfaces_abstracts.services.IServices;
import interfaces_abstracts.services.Service;
import interfaces_abstracts.settings.ISettings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Services implements IServices {

	private List<Service>[] serviceList;

	public void createServices() {
		if (serviceList != null)
			return;

		ISettings settings = IoCAdapter.getInstance().getISettingsObject();

		IntermediateDB intermediateDB = IoCAdapter.getInstance().getIntermediateDbObject();
		intermediateDB.setParams(
				settings.getIntermediateDBHost(),
				settings.getIntermediateDBPort(),
				settings.getIntermediateDBName(),
				settings.getIntermediateDBUserName(),
				settings.getIntermediateDBPassword()
		);
		intermediateDB.connect();

		FinalDB finalDB = IoCAdapter.getInstance().getFinalDbObject();
		finalDB.setParams(
				settings.getFinalDBHost(),
				settings.getFinalDBPort(),
				settings.getFinalDBName(),
				settings.getFinalDBUserName(),
				settings.getFinalDBPassword()
		);
		finalDB.connect();

		serviceList = new List[5];

		serviceList[0] = new ArrayList<Service>();
		serviceList[1] = new ArrayList<Service>();
		serviceList[2] = new ArrayList<Service>();
		serviceList[3] = new ArrayList<Service>();
		serviceList[4] = new ArrayList<Service>();

		try {

			serviceList[0].add(new LoadIDFromDBService(intermediateDB,
					settings.getRabbitMQHost(), null, "ID_Q"));

			serviceList[1].add(new LoadWallFromVkService(settings.getRabbitMQHost(),
					"ID_Q", "Wall_Q"));

			serviceList[2].add(new ParseWallService(settings.getRabbitMQHost(),
					"Wall_Q", "Data_Q"));

			serviceList[3].add(new SaveDataToDBService(intermediateDB,
					settings.getRabbitMQHost(), "Data_Q", null));

			serviceList[4].add(new SaveToFinalDBService(intermediateDB,
					finalDB, settings.getRabbitMQHost(), null, null));


		} catch (TimeoutException e) {
			Control.log(e.toString());
		} catch (IOException e) {
			Control.log(e.toString());
		}
	}

	public boolean addService(int ind) {
		if (serviceList == null)
			return false;
		if (serviceList.length < 1)
			return false;
		if (ind <= 0 || ind >= serviceList.length-1)
			return false;

		try {

			serviceList[ind].add(serviceList[0].get(0).copyService());
			return true;

		} catch (TimeoutException e) {
			Control.log(e.toString());
		} catch (IOException e) {
			Control.log(e.toString());
		}
		return false;
	}

}
