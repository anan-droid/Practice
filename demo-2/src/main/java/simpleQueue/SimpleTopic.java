package simpleQueue;

import java.util.ArrayList;
import java.util.List;

public class SimpleTopic implements Topic{
	private final int topicId;
	private final String topicName;	
	private final List<String> messages = new ArrayList();
	private final List<Consumer> subscribers = new ArrayList();
	private final TopicHandler topicHandler;
	
	public SimpleTopic(int id, String name) {
		this.topicId = id;
		this.topicName = name;
		this.topicHandler = new SimpleTopicHandler(this, subscribers);
	}
	
	public TopicHandler getTopicHandler() {
		return topicHandler;
	}
	
	public void addMessage(String msg) {
		messages.add(msg);
		
	}
	
	public String readMessage(int offset) throws Exception {
		if(messages.size()>offset) 
			return messages.get(offset);		
		throw new Exception("offset not found");
	}

	@Override
	public void addSubscriber(Consumer consumer) {
		subscribers.add(consumer);		
	}	
	
	public String getTopicName() {
		return topicName;
	}
	
	public List<Consumer> getConsumers() {
		return List.copyOf(subscribers);
	}
	
}
