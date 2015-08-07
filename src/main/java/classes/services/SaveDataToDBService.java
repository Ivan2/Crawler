package classes.services;

import abstractions.data.IPostInfo;
import abstractions.db.IntermediateDB;
import abstractions.queue.IMessage;
import abstractions.queue.IQueue;
import abstractions.services.Service;
import classes.crawler.Control;
import classes.service_locator.ServiceLocatorAdapter;

import java.io.IOException;

public class SaveDataToDBService extends Service {

	private IntermediateDB db;

	public SaveDataToDBService(IntermediateDB db, IQueue consumerQueue,
	                           IQueue producerQueue) {

		super(consumerQueue, producerQueue);
		this.db = db;
		start();
	}

	@Override
	protected void threadBody() {
		try {
			IPostInfo postInfo = ServiceLocatorAdapter.getInstance().getObject(IPostInfo.class);
			IMessage message = consumerQueue.receiveMessage();
			postInfo.parseByteArray(message.getBytes());

			int updateCount = db.setCommentsLikesRepostsCount(postInfo);
			if (updateCount == 0) {
				db.addCommentsLikesRepostsCount(postInfo);
				Control.info(getClass().getName(), "Save post to DB");
			} else
				Control.info(getClass().getName(), "Update post in DB");

			consumerQueue.ackMessage(message.getTag());

		} catch (InterruptedException e) {
			Control.error(getClass().getName(), e.toString());
		} catch (IOException e) {
			Control.error(getClass().getName(), e.toString());
		}
	}

	@Override
	public Service copyService() {
		return new SaveDataToDBService(db, consumerQueue, producerQueue);
	}

}
