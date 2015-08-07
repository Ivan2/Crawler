package abstractions.queue;

import java.io.IOException;

public interface IQueue {

	void sendMessage(byte[] bytes) throws IOException;

	IMessage receiveMessage() throws InterruptedException;

	void ackMessage(long tag) throws IOException;

	void rejectMessage(long tag) throws IOException;

}
