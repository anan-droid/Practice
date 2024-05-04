package simpleQueue;

import java.util.List;

public class SimpleTopicHandler implements TopicHandler{
	
	private final Topic topic;
	private final List<Consumer> consumers;
	
	
	public SimpleTopicHandler(Topic topic, List<Consumer> consumers) {
		this.topic = topic;
		this.consumers = consumers;
	}
	
	public void notifySubscribers() {
		new Thread(()->{ 
			for(Consumer consumer: consumers) {
				TopicConsumerEntry topicConsumerEntry = consumer.getTopicConsumerEntry(topic);
				synchronized(topicConsumerEntry) { 
					topicConsumerEntry.notify();
				}
			}
		}).start();
	}
}
