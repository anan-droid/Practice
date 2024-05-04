package simpleQueue;

public class SimpleProducer implements Producer {
	
	Queue queue;	
	
	public SimpleProducer(Queue queue) {
		this.queue = queue;				
	}

	@Override
	public void publishToQueue(String topicName, String msg) throws Exception {		
		queue.publishMessage(topicName, msg);		
	}

}
