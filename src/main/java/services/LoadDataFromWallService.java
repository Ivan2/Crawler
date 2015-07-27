package services;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import db.IDB;
import load_wall.json.IJSONParser;
import load_wall.vk.JSONWallParser;
import load_wall.wall_data.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;

public class LoadDataFromWallService extends Thread {

	//private final String PRODUCER_QUEUE_NAME = "3Q";
	private final String CONSUMER_QUEUE_NAME = "2Q";

	//private ConnectionFactory producerFactory;
	//private Connection producerConnection;
	//private Channel producerChannel;

	private ConnectionFactory consumerFactory;
	private Connection consumerConnection;
	private Channel consumerChannel;
	private BufferedReader consumerBufferedReader;
	private QueueingConsumer consumer;

	private IDB db;
	private IJSONParser parser;

	public LoadDataFromWallService(IDB db) {
		this.db = db;
		setDaemon(true);
		parser = new JSONWallParser();

		try {
			//producerFactory = new ConnectionFactory();
			//producerFactory.setHost("localhost");
			//producerConnection = producerFactory.newConnection();
			//producerChannel = producerConnection.createChannel();
			//producerChannel.queueDeclare(PRODUCER_QUEUE_NAME, false, false, false, null);

			consumerFactory = new ConnectionFactory();
			consumerFactory.setHost("localhost");
			consumerConnection = consumerFactory.newConnection();
			consumerChannel = consumerConnection.createChannel();
			consumerChannel.basicQos(1);
			consumerChannel.queueDeclare(CONSUMER_QUEUE_NAME, false, false, false, null);
			consumerBufferedReader = new BufferedReader(new InputStreamReader(System.in));
			consumer = new QueueingConsumer(consumerChannel);
			consumerChannel.basicConsume(CONSUMER_QUEUE_NAME, false, consumer);

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
				QueueingConsumer.Delivery delivery = consumer.nextDelivery();
				String wall = new String(delivery.getBody());

				Data data = parser.parse(wall);

				for (int i=0; i<data.getId().length; i++) {
					int setDataCount = db.setData(data.getId()[i], data.getComments()[i],
							data.getLikes()[i], data.getReposts()[i],
							data.getDates()[i]);

					if (setDataCount == 0)
						db.addData(data.getId()[i], data.getComments()[i],
								data.getLikes()[i], data.getReposts()[i],
								data.getDates()[i]);
				}

				int setDateCount = db.setDateOfUpdate();
				if (setDateCount == 0)
					db.addDateOfUpdate();

				consumerChannel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


}
