package simpleQueue;

public interface Producer {	

	void publishToQueue(String topicName, String msg) throws Exception;
}
