package simpleQueue;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class SimpleQueue implements Queue{
	private final Map<String, Topic> topics;		
	
	public SimpleQueue() {
		this.topics = new HashMap();
	}
	
	public Topic createTopic(String name) {
		UUID uuid = UUID.randomUUID();
		int id = uuid.hashCode();
		Topic topic = new SimpleTopic(id, name);
		topics.put(name, topic);	
		return topic;
	}	
	
	@Override
	public void publishMessage(String topicName, String msg) throws Exception {
		if(topics.containsKey(topicName)) {
			topics.get(topicName).addMessage(msg);
			System.out.println("time:"+System.currentTimeMillis()+":"+Thread.currentThread()+"-published:"+topicName+"-"+msg);
			Topic topic = topics.get(topicName);
			topic.getTopicHandler().notifySubscribers();
			return;
		}		
		throw new Exception("topic not found");
	}

	@Override
	public String consumeMessage(String topicName, int offset) throws Exception {
		if(topics.containsKey(topicName)) {
			return topics.get(topicName).readMessage(offset);
		}
		throw new Exception("topic not found");
	}

	@Override
	public void subscribe(String topicName, Consumer consumer) {
		Topic topic = topics.get(topicName);
		topic.addSubscriber(consumer);		
		consumer.addTopic(topic);
		try {
			consumer.startListening(topic);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
}
