package com.example.demo;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SimpleComm {
	public static void main(String[] args) {
		CricketProducer cricketProducer = new CricketBoard();
		FootBallProducer footBallProducer = new FootBallBoard();
		CricketConsumer cricketConsumer = new CricketConsumerWeb(cricketProducer);
		FootBallConsumer footBallConsumer = new FootBallConsumerWeb(footBallProducer);
		cricketProducer.subscribe(cricketConsumer);
		footBallProducer.subscribe(footBallConsumer);
		cricketProducer.setRuns(100);
		footBallProducer.setGoal(2);
	}
}


interface CricketProducer {
	public void subscribe(CricketConsumer consumer);
	public void unSubscribe(CricketConsumer consumer);
	public void notifyConsumers();
	public void setRuns(int runs);
	public int getRuns();
}

interface FootBallProducer {
	public void subscribe(FootBallConsumer consumer);
	public void unSubscribe(FootBallConsumer consumer);
	public void notifyConsumers();
	public void setGoal(int goals);
	public int getGoal();
}

interface CricketConsumer {
	public void updateRuns();
}

interface FootBallConsumer {
	public void updateGoals();
}

class CricketBoard implements CricketProducer{
	private int runs;
	private CricketConsumer cricketConsumer;
	
	public void subscribe(CricketConsumer consumer) {
		this.cricketConsumer = consumer;
	}	
	
	@Override
	public void unSubscribe(CricketConsumer consumer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyConsumers() {
		this.cricketConsumer.updateRuns();		
	}

	@Override
	public void setRuns(int runs) {
		this.runs = runs;		
		notifyConsumers();
	}

	@Override
	public int getRuns() {		
		return runs;
	}
	
}
class FootBallBoard implements FootBallProducer{
	private int goals;
	private FootBallConsumer footBallConsumer; 

	@Override
	public void subscribe(FootBallConsumer consumer) {
		this.footBallConsumer = consumer;
	}

	@Override
	public void unSubscribe(FootBallConsumer consumer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyConsumers() {
		this.footBallConsumer.updateGoals();		
	}

	@Override
	public void setGoal(int goals) {
		this.goals = goals;
		notifyConsumers();
	}

	@Override
	public int getGoal() {		
		return goals;
	}
	
}


class CricketConsumerWeb implements CricketConsumer {
	private int runs;
	private CricketProducer cricketProducer;			

	public CricketConsumerWeb(CricketProducer cricketProducer) {
		this.cricketProducer = cricketProducer;
	}
	
	@Override
	public void updateRuns() {
		this.runs = cricketProducer.getRuns();
		System.out.println(runs);
	}
}

class FootBallConsumerWeb implements FootBallConsumer {
	private int goals;
	private FootBallProducer footBallProducer;			

	public FootBallConsumerWeb(FootBallProducer footBallProducer) {
		this.footBallProducer = footBallProducer;
	}
	
	@Override
	public void updateGoals() {		
		this.goals = footBallProducer.getGoal();
		System.out.println(goals);
	}
}
