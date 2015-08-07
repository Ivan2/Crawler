package classes.services;

import abstractions.queue.IMessage;
import abstractions.queue.IQueue;
import abstractions.services.Service;
import abstractions.vk.IVkWallLoader;
import classes.crawler.Control;
import classes.service_locator.ServiceLocatorAdapter;

import java.io.IOException;

public class LoadWallFromVkService extends Service {

	private IVkWallLoader vkWallLoader;

	public LoadWallFromVkService(IQueue consumerQueue, IQueue producerQueue) {
		super(consumerQueue, producerQueue);
		vkWallLoader = ServiceLocatorAdapter.getInstance().getObject(IVkWallLoader.class);
		start();
	}

	@Override
	protected void threadBody() {
		try {
			IMessage message = consumerQueue.receiveMessage();
			String id = new String(message.getBytes());

			String wall = vkWallLoader.loadWall(id);

			if (wall == "") {
				consumerQueue.rejectMessage(message.getTag());
				Control.error(getClass().getName(), "Error load wall by id: " + id);
				return;
			}

			Control.info(getClass().getName(), "Load wall by id: " + id);

			producerQueue.sendMessage(wall.getBytes());

			consumerQueue.ackMessage(message.getTag());

		} catch (InterruptedException e) {
			Control.error(getClass().getName(), e.toString());
		} catch (IOException e) {
			Control.error(getClass().getName(), e.toString());
		}
	}

	@Override
	public Service copyService() {
		return new LoadWallFromVkService(consumerQueue, producerQueue);
	}

}
