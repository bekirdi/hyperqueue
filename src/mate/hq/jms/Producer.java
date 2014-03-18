package mate.hq.jms;

import org.apache.log4j.Logger;

public class Producer
{
	private static final Logger LOGGER = Logger.getLogger(Consumer.class);
	
	private Broker broker;
	private Integer message;
	private String topicName;
	
	public Producer() {
		this(null, null, null);
	}
	
	private Producer(
			final Broker pBroker, final String pTopicName, final Integer pMessage) {
        super();
		this.broker = pBroker;
		this.message = pMessage;
		this.topicName = pTopicName;
    }

	public static Producer create(Broker broker, String topicName, Integer message) {
		return new Producer(broker, topicName, message);
    }

	public void putMessage() {
		try {
			broker.putMessage(topicName, message);
			System.out.println("producer " + message);
			System.out.println("Producer finished its job; terminating.");
			Producer.LOGGER.info("New Message on the queue " + message);
		}
		catch (InterruptedException exception)
		{
			Producer.LOGGER.error("An error occured while invoking consume message",
					exception);
		}
	}
}
