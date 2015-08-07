package classes.queue;

import abstractions.queue.IMessage;

public class Message implements IMessage {

	private byte[] bytes;
	private long tag;

	public Message(byte[] bytes, long tag) {
		this.bytes = bytes;
		this.tag = tag;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public long getTag() {
		return tag;
	}
}
