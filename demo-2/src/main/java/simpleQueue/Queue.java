package simpleQueue;

public interface Queue {
	public Topic createTopic(String name);
	public void subscribe(String topicName, Consumer consumer);
	public void publishMessage(String topicName, String msg) throws Exception;
	public String consumeMessage(String topicName, int offset) throws Exception;
}
