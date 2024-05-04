package simpleQueue;

import java.util.concurrent.locks.Lock;

public interface Consumer {
	public void readFromQueue(Topic topic) throws Exception;
	public void startListening(Topic topic) throws InterruptedException;
	public void addTopic(Topic topic);
	public TopicConsumerEntry getTopicConsumerEntry(Topic topic);
}
