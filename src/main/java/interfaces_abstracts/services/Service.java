package interfaces_abstracts.services;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public abstract class Service extends Thread {

	protected String rabbitMQHost;
	protected String consumerQName;
	protected String producerQName;

	protected ConnectionFactory producerFactory;
	protected Connection producerConnection;
	protected Channel producerChannel;

	protected ConnectionFactory consumerFactory;
	protected Connection consumerConnection;
	protected Channel consumerChannel;
	protected QueueingConsumer consumer;

	public Service(String rabbitMQHost, String consumerQName, String producerQName)
			throws TimeoutException, IOException {

		setDaemon(true);
		this.rabbitMQHost = rabbitMQHost;
		this.consumerQName = consumerQName;
		this.producerQName = producerQName;

		if (producerQName != null) {
			producerFactory = new ConnectionFactory();
			producerFactory.setHost(rabbitMQHost);

			producerConnection = producerFactory.newConnection();

			producerChannel = producerConnection.createChannel();
			producerChannel.queueDeclare(producerQName, false, false,
					false, null);
		}

		if (consumerQName != null) {
			consumerFactory = new ConnectionFactory();
			consumerFactory.setHost(rabbitMQHost);

			consumerConnection = consumerFactory.newConnection();

			consumerChannel = consumerConnection.createChannel();
			consumerChannel.basicQos(1);
			consumerChannel.queueDeclare(consumerQName, false, false,
					false, null);

			consumer = new QueueingConsumer(consumerChannel);
			consumerChannel.basicConsume(consumerQName, false, consumer);
		}
	}

	public abstract Service copyService() throws TimeoutException, IOException;

}
