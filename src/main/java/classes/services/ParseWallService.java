package classes.services;

import abstractions.data.IPostInfo;
import abstractions.json.IJSONParser;
import abstractions.queue.IMessage;
import abstractions.queue.IQueue;
import abstractions.services.Service;
import classes.crawler.Control;
import classes.service_locator.ServiceLocatorAdapter;

import java.io.IOException;

public class ParseWallService extends Service {

	private IJSONParser parser;

	public ParseWallService(IQueue consumerQueue, IQueue producerQueue) {
		super(consumerQueue, producerQueue);
		parser = ServiceLocatorAdapter.getInstance().getObject(IJSONParser.class);
		start();
	}

	@Override
	protected void threadBody() {
		try {
			IMessage message = consumerQueue.receiveMessage();
			String wall = new String(message.getBytes());

			IPostInfo[] postInfoArray = parser.parse(wall);

			Control.info(getClass().getName(), "Parse wall. Post count: "
					+ postInfoArray.length);

			for (int i=0; i<postInfoArray.length; i++)
				producerQueue.sendMessage(postInfoArray[i].toByteArray());

			consumerQueue.ackMessage(message.getTag());

		} catch (InterruptedException e) {
			Control.error(getClass().getName(), e.toString());
		} catch (IOException e) {
			Control.error(getClass().getName(), e.toString());
		}
	}

	@Override
	public Service copyService() {
		return new ParseWallService(consumerQueue, producerQueue);
	}
}
