package services;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import load_wall.vk.IVkWallLoader;
import load_wall.vk.VkWallLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;

public class LoadWallFromVkService extends Thread {

	private final String PRODUCER_QUEUE_NAME = "2Q";
	private final String CONSUMER_QUEUE_NAME = "1Q";

	private ConnectionFactory producerFactory;
	private Connection producerConnection;
	private Channel producerChannel;

	private ConnectionFactory consumerFactory;
	private Connection consumerConnection;
	private Channel consumerChannel;
	private BufferedReader consumerBufferedReader;
	private QueueingConsumer consumer;

	private IVkWallLoader vkWallLoader;

	public LoadWallFromVkService() {
		setDaemon(true);
		vkWallLoader = new VkWallLoader();

		try {
			producerFactory = new ConnectionFactory();
			producerFactory.setHost("localhost");

			producerConnection = producerFactory.newConnection();

			producerChannel = producerConnection.createChannel();
			producerChannel.queueDeclare(PRODUCER_QUEUE_NAME, false, false,
					false, null);


			consumerFactory = new ConnectionFactory();
			consumerFactory.setHost("localhost");

			consumerConnection = consumerFactory.newConnection();

			consumerChannel = consumerConnection.createChannel();
			consumerChannel.basicQos(1);
			consumerChannel.queueDeclare(CONSUMER_QUEUE_NAME, false, false,
					false, null);

			consumerBufferedReader = new BufferedReader
					(new InputStreamReader(System.in));

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
				String id = new String(delivery.getBody());

				String wall = vkWallLoader.loadWall(id);

				producerChannel.basicPublish("", PRODUCER_QUEUE_NAME, null,
						wall.getBytes());

				consumerChannel.basicAck(delivery.getEnvelope().getDeliveryTag(),
						false);

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


}
