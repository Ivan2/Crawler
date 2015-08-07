package abstractions.services;

import abstractions.queue.IQueue;

public abstract class Service extends Thread {

	protected IQueue consumerQueue;
	protected IQueue producerQueue;
	private volatile boolean isInterrupted = false;

	public Service(IQueue consumerQueue, IQueue producerQueue) {
		setDaemon(true);
		this.consumerQueue = consumerQueue;
		this.producerQueue = producerQueue;
	}

	public abstract Service copyService();

	@Override
	public void run() {
		while (!isInterrupted) {
			threadBody();
		}
	}

	protected abstract void threadBody();

	@Override
	public void interrupt() {
		isInterrupted = true;
		super.interrupt();
	}

}
