package simpleQueue;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

class TopicConsumerEntry {
	private final Topic topic;
	private final Consumer consumer;
	private final AtomicInteger offset;
	
	public TopicConsumerEntry(Topic topic, Consumer consumer) {
		this.topic = topic;
		this.consumer = consumer;
		this.offset= new AtomicInteger(0);
	}

	public AtomicInteger getOffset() {
		return offset;
	}	
}


public class SimpleConsumer implements Consumer{

	private final Queue queue;	
	private final Map<Topic, TopicConsumerEntry> topicConsumerMap;
	
	
	public SimpleConsumer(Queue queue) {
		this.queue = queue;		
		this.topicConsumerMap = new HashMap<>();
	}

	public TopicConsumerEntry getTopicConsumerEntry(Topic topic) {
		return topicConsumerMap.get(topic);
	}

	public void startListening(Topic topic) throws InterruptedException {
		new Thread(()->{try {
			synchronized(topicConsumerMap.get(topic)) {
				while(true) {				
					try {					
						readFromQueue(topic);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						topicConsumerMap.get(topic).wait();
					}						
				}		
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}}).start();;
		
	}
		
	public void addTopic(Topic topic) {
		topicConsumerMap.putIfAbsent(topic, new TopicConsumerEntry(topic, this));		
	}
	
	@Override
	public void readFromQueue(Topic topic) throws Exception {
		String msg=null;
		AtomicInteger offset = topicConsumerMap.get(topic).getOffset();
		int curroffset = offset.get();
		msg = queue.consumeMessage(topic.getTopicName(),curroffset);
		System.out.println("time:"+System.currentTimeMillis()+":"+Thread.currentThread()+"-consumed from "+topic.getTopicName()+":"+msg);
		if(topic.getTopicName()=="simple") Thread.sleep(3000);
		offset.incrementAndGet();									
	}	

	
}
