package simpleQueue;

import java.util.List;

public interface Topic {
	public String readMessage(int offset) throws Exception;
	public void addMessage(String msg);
	public void addSubscriber(Consumer consumer);
	public String getTopicName();
	public List<Consumer> getConsumers();
	public TopicHandler getTopicHandler();
}
