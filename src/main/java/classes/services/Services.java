package classes.services;

import abstractions.db.FinalDB;
import abstractions.db.IntermediateDB;
import abstractions.queue.IQueue;
import abstractions.services.IServices;
import abstractions.services.Service;
import abstractions.settings.ISettings;
import classes.crawler.Control;
import classes.queue.RabbitMQ;
import classes.service_locator.ServiceLocatorAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class Services implements IServices {

	volatile private List<Service>[] serviceList;

	public void createServices() {
		if (serviceList != null)
			return;

		ISettings settings = ServiceLocatorAdapter.getInstance().getObject(ISettings.class);

		IntermediateDB intermediateDB = ServiceLocatorAdapter.getInstance().getObject(IntermediateDB.class);
		intermediateDB.setParams(
				settings.getIntermediateDBHost(),
				settings.getIntermediateDBPort(),
				settings.getIntermediateDBName(),
				settings.getIntermediateDBUserName(),
				settings.getIntermediateDBPassword()
		);
		intermediateDB.connect();

		FinalDB finalDB = ServiceLocatorAdapter.getInstance().getObject(FinalDB.class);
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
			IQueue idQueue = new RabbitMQ(settings.getRabbitMQHost(), "ID_Q");
			IQueue wallQueue = new RabbitMQ(settings.getRabbitMQHost(), "Wall_Q");
			IQueue dataQueue = new RabbitMQ(settings.getRabbitMQHost(), "Data_Q");

			serviceList[0].add(new LoadIDFromDBService(intermediateDB, null, idQueue));

			serviceList[1].add(new LoadWallFromVkService(idQueue, wallQueue));

			serviceList[2].add(new ParseWallService(wallQueue, dataQueue));

			serviceList[3].add(new SaveDataToDBService(intermediateDB, dataQueue, null));

			serviceList[4].add(new SaveToFinalDBService(intermediateDB,
					finalDB, null, null));

		} catch (TimeoutException e) {
			Control.error(getClass().getName(), e.toString());
		} catch (IOException e) {
			Control.error(getClass().getName(), e.toString());
		}
	}

	public boolean addService(int ind) {
		if (serviceList == null)
			return false;
		if (serviceList.length < 1)
			return false;
		if (ind <= 0 || ind >= serviceList.length-1)
			return false;

		serviceList[ind].add(serviceList[ind].get(0).copyService());
		return true;
	}

	public boolean delService(int ind) {
		if (serviceList == null)
			return false;
		if (serviceList.length < 1)
			return false;
		if (ind <= 0 || ind >= serviceList.length-1)
			return false;
		if (serviceList[ind].size() < 2)
			return false;

		serviceList[ind].get(serviceList[ind].size()-1).interrupt();
		serviceList[ind].remove(serviceList[ind].size()-1);
		return true;
	}

	@Override
	public String toString() {
		String result = "";
		if (serviceList == null)
			return result;
		if (serviceList.length < 1)
			return result;
		for (int i=0; i<serviceList.length; i++) {
			result += "ind=" + i;
			if (serviceList[i] != null)
				if (serviceList[i].size() > 0)
					if (serviceList[i].get(0) != null)
						result += "  name=\"" + serviceList[i].get(0).getClass().getName()
								+ "\"  count=" + serviceList[i].size();
			result += "\n";
		}
		return result;
	}
}
