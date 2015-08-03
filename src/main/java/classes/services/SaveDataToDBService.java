package classes.services;

import classes.crawler.Control;
import classes.ioc.IoCAdapter;
import com.rabbitmq.client.QueueingConsumer;
import interfaces_abstracts.data.IPostInfo;
import interfaces_abstracts.db.IntermediateDB;
import interfaces_abstracts.services.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class SaveDataToDBService extends Service {

	private IntermediateDB db;

	public SaveDataToDBService(IntermediateDB db, String rabbitMQHost,
	                           String consumerQName, String producerQName)
			throws TimeoutException, IOException {

		super(rabbitMQHost, consumerQName, producerQName);
		this.db = db;
		start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				QueueingConsumer.Delivery delivery = consumer.nextDelivery();
				IPostInfo postInfo = IoCAdapter.getInstance().getIPostInfoObject();
				postInfo.parseByteArray(delivery.getBody());

				int updateCount = db.setCommentsLikesRepostsCount(postInfo);
				if (updateCount == 0) {
					db.addCommentsLikesRepostsCount(postInfo);
					Control.log("Save post to DB");
				} else
					Control.log("Update post in DB");

				consumerChannel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);

			} catch (InterruptedException e) {
				Control.log(e.toString());
			} catch (IOException e) {
				Control.log(e.toString());
			}
		}
	}

	@Override
	public Service copyService() throws TimeoutException, IOException {
		return new SaveDataToDBService(db, rabbitMQHost, consumerQName,	producerQName);
	}

}
