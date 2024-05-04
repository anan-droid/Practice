package com.example.demo;

public class SimpleThreadComm implements Runnable{
	private static int i=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleThreadComm tc = new SimpleThreadComm();
		Thread t1 = new Thread(tc);
		Thread t2 = new Thread(tc);
		t1.start();
		t2.start();
	}
	
	public void run() {
		synchronized (this) {	
			while(true) {
				System.out.println("from thread:"+Thread.currentThread()+"-"+(++i));
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				notify();
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
