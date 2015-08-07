package abstractions.queue;

public interface IMessage {

	byte[] getBytes();

	long getTag();

}
