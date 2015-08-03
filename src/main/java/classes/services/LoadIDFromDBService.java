package classes.services;

import classes.crawler.Control;
import interfaces_abstracts.db.IntermediateDB;
import interfaces_abstracts.services.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class LoadIDFromDBService extends Service {

	private IntermediateDB db;

	public LoadIDFromDBService(IntermediateDB db, String rabbitMQHost, String consumerQName,
			String producerQName) throws TimeoutException, IOException{

		super(rabbitMQHost, consumerQName, producerQName);
		this.db = db;
		start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				List<String> id = db.getIDList();

				Control.log("Load idList: " + id);

				for (String str : id)
					producerChannel.basicPublish("", producerQName, null,
							str.getBytes());
				Thread.sleep(600000);

			} catch (InterruptedException e) {
				Control.log(e.toString());
			} catch (IOException e) {
				Control.log(e.toString());
			}
		}
	}

	public Service copyService() throws TimeoutException, IOException {
		return new LoadIDFromDBService(db, rabbitMQHost, consumerQName, producerQName);
	}
}
