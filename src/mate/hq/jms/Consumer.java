package mate.hq.jms;

import org.apache.log4j.Logger;

public class Consumer
{
    private static final Logger LOGGER = Logger.getLogger(Consumer.class);

	private Broker broker;
	private String topicName;
	private String sessionId;
	
	public Consumer() {
		this(null, null, null);
	}
	
	private Consumer(
			final String pTopicName, final String pSessionId, final Broker pBroker) {
        super();
        this.broker = pBroker;
		this.sessionId = pSessionId;
		this.topicName = pTopicName;
    }

	public static Consumer create(String topicName, String sessionId, Broker broker) {
		return new Consumer(topicName, sessionId, broker);
    }

	public Integer getMessage() {
		Integer message = null;
		try {
			message = broker.getMessage(topicName);
			System.out.println("Message " + message + " cousumed by message " + sessionId);
			Consumer.LOGGER.info("Message " + message + " cousumed by message " + sessionId);
		}
		catch (InterruptedException exception) {
			Consumer.LOGGER.error("An error occured while invoking consume message",
					exception);
		} 
			
		return message;
	}
}
