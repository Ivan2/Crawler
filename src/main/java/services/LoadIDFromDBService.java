package services;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import db.IDB;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class LoadIDFromDBService extends Thread {

	private final String PRODUCER_QUEUE_NAME = "1Q";

	private IDB db;
	private ConnectionFactory producerFactory;
	private Connection producerConnection;
	private Channel producerChannel;

	public LoadIDFromDBService(IDB db) {
		this.db = db;
		setDaemon(true);

		try {
			producerFactory = new ConnectionFactory();
			producerFactory.setHost("localhost");
			producerConnection = producerFactory.newConnection();
			producerChannel = producerConnection.createChannel();
			producerChannel.queueDeclare(PRODUCER_QUEUE_NAME, false, false,
					false, null);

		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				List<String> id = db.getIDList();

				for (String str : id)
					producerChannel.basicPublish("", PRODUCER_QUEUE_NAME, null,
							str.getBytes());

				Thread.sleep(600000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
