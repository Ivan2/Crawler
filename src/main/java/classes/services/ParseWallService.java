package classes.services;

import classes.crawler.Control;
import classes.ioc.IoCAdapter;
import com.rabbitmq.client.QueueingConsumer;
import interfaces_abstracts.data.IPostInfo;
import interfaces_abstracts.json.IJSONParser;
import interfaces_abstracts.services.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ParseWallService extends Service {

	private IJSONParser parser;

	public ParseWallService(String rabbitMQHost, String consumerQName,
			String producerQName) throws TimeoutException, IOException {

		super(rabbitMQHost, consumerQName, producerQName);
		parser = IoCAdapter.getInstance().getIJSONParserObject();
		start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				QueueingConsumer.Delivery delivery = consumer.nextDelivery();
				String wall = new String(delivery.getBody());

				IPostInfo[] postInfoArray = parser.parse(wall);

				Control.log("Parse wall. Post count: " + postInfoArray.length);

				for (int i=0; i<postInfoArray.length; i++)
					producerChannel.basicPublish("", producerQName, null,
							postInfoArray[i].toByteArray());

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
		return new ParseWallService(rabbitMQHost, consumerQName, producerQName);
	}
}
