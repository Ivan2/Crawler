package classes.services;

import classes.crawler.Control;
import classes.ioc.IoCAdapter;
import com.rabbitmq.client.QueueingConsumer;
import interfaces_abstracts.services.Service;
import interfaces_abstracts.vk.IVkWallLoader;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class LoadWallFromVkService extends Service {

	private IVkWallLoader vkWallLoader;

	public LoadWallFromVkService(String rabbitMQHost, String consumerQName,
			String producerQName) throws TimeoutException, IOException {

		super(rabbitMQHost, consumerQName, producerQName);
		vkWallLoader = IoCAdapter.getInstance().getIVkWallLoaderObject();
		start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				QueueingConsumer.Delivery delivery = consumer.nextDelivery();
				String id = new String(delivery.getBody());

				String wall = vkWallLoader.loadWall(id);

				Control.log("Load wall by id: " + id);

				producerChannel.basicPublish("", producerQName, null,
						wall.getBytes());

				consumerChannel.basicAck(delivery.getEnvelope().getDeliveryTag(),
						false);

			} catch (InterruptedException e) {
				Control.log(e.toString());
			} catch (IOException e) {
				Control.log(e.toString());
			}
		}
	}

	public Service copyService() throws TimeoutException, IOException {
		return new LoadWallFromVkService(rabbitMQHost, consumerQName, producerQName);
	}
}
