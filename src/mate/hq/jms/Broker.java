package mate.hq.jms;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.BlockingQueue;

public final class Broker
{
	public ConcurrentMap<String, BlockingQueue<Integer>> concurrentQueueMap = new ConcurrentHashMap<String, BlockingQueue<Integer>>();
    
	public void putMessage(final String topicName, final Integer message) throws InterruptedException
	{
		if(concurrentQueueMap.containsKey(topicName))
		{
			concurrentQueueMap.get(topicName).put(message);
		}
		else
		{
			BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(1024);
			queue.put(message);
			concurrentQueueMap.put(topicName, queue);
		}
	}

	public Integer getMessage(final String topicName) throws InterruptedException
	{
		if(concurrentQueueMap.containsKey(topicName) && concurrentQueueMap.get(topicName).size() != 0)
		{
			return (Integer) concurrentQueueMap.get(topicName).take();
		}
		
		return null;
	}
}
