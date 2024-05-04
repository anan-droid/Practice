package simpleQueue;

public class Main {

	public static void main(String[] args) {
		// queue side code
		Queue queue = new SimpleQueue();
		Topic topic1 = queue.createTopic("simple");
		Topic topic2 = queue.createTopic("complex");
		//producer side code
		Producer producer = new SimpleProducer(queue);		
		//consumer side code
		Consumer consumer1 = new SimpleConsumer(queue);
		Consumer consumer2 = new SimpleConsumer(queue);
		
		queue.subscribe("simple", consumer1);
		queue.subscribe("simple", consumer2);
		queue.subscribe("complex", consumer1);
		
		Thread t1 = new Thread(()->{
			int i=0;
			while(true) {
				i++;
				try {
					producer.publishToQueue("simple","msg"+i);
					producer.publishToQueue("complex","msg"+i);
					if(i==10) break;
					//Thread.sleep(2000);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println(e.getMessage());
				}
			}
		});
		t1.start();
		
//		Thread t2 = new Thread(()->{
//			int i=0;
//			while(true) {
//				i++;
//				try {
//					producer.publishToQueue("complex","msg"+i);
//					if(i==10) break;
//					//Thread.sleep(2000);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					//e.printStackTrace();
//					System.out.println(e.getMessage());
//				}
//			}
//		});
//		t2.start();
		
	}
}
