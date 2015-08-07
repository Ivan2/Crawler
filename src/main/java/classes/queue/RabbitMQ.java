package classes.queue;

import abstractions.queue.IMessage;
import abstractions.queue.IQueue;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQ implements IQueue {

	private String host;
	private String queueName;

	private ConnectionFactory producerFactory;
	private Connection producerConnection;
	private Channel producerChannel;

	private ConnectionFactory consumerFactory;
	private Connection consumerConnection;
	private Channel consumerChannel;
	private QueueingConsumer consumer;

	private QueueingConsumer.Delivery delivery;

	public RabbitMQ(String host, String queueName)
			throws TimeoutException, IOException {
		this.host = host;
		this.queueName = queueName;

		producerFactory = new ConnectionFactory();
		producerFactory.setHost(host);

		producerConnection = producerFactory.newConnection();

		producerChannel = producerConnection.createChannel();
		producerChannel.queueDeclare(queueName, false, false,
				false, null);


		consumerFactory = new ConnectionFactory();
		consumerFactory.setHost(host);

		consumerConnection = consumerFactory.newConnection();

		consumerChannel = consumerConnection.createChannel();
		consumerChannel.basicQos(1);
		consumerChannel.queueDeclare(queueName, false, false,
				false, null);

		consumer = new QueueingConsumer(consumerChannel);
		consumerChannel.basicConsume(queueName, false, consumer);
	}

	public void sendMessage(byte[] bytes) throws IOException {
		producerChannel.basicPublish("", queueName, null, bytes);
	}

	public IMessage receiveMessage() throws InterruptedException {
		delivery = consumer.nextDelivery();
		return new Message(delivery.getBody(), delivery.getEnvelope().getDeliveryTag());
	}

	public void ackMessage(long tag) throws IOException {
		consumerChannel.basicAck(tag, false);
	}

	public void rejectMessage(long tag) throws IOException {
		consumerChannel.basicReject(tag, false);
	}

}
