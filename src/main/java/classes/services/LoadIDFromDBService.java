package classes.services;

import abstractions.db.IntermediateDB;
import abstractions.queue.IQueue;
import abstractions.services.Service;
import classes.crawler.Control;

import java.io.IOException;
import java.util.List;

public class LoadIDFromDBService extends Service {

	private IntermediateDB db;

	public LoadIDFromDBService(IntermediateDB db, IQueue consumerQueue,
	                           IQueue producerQueue) {

		super(consumerQueue, producerQueue);
		this.db = db;
		start();
	}

	@Override
	protected void threadBody() {
		try {
			List<String> id = db.getIDList();

			Control.info(getClass().getName(), "Load idList: " + id);

			for (String str : id)
				producerQueue.sendMessage(str.getBytes());
			Thread.sleep(600000);

		} catch (InterruptedException e) {
			Control.error(getClass().getName(), e.toString());
		} catch (IOException e) {
			Control.error(getClass().getName(), e.toString());
		}
	}

	@Override
	public Service copyService() {
		return new LoadIDFromDBService(db, consumerQueue, producerQueue);
	}

}
