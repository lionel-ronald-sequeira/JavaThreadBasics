package com.thread.producerconsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {
	
	//BlockingQueue is thread safe.It handles synchronization itself.
	private BlockingQueue<Integer> queue=new ArrayBlockingQueue<Integer>(10);
	
	public void produce(){
		while(true){
			Random randomGenerator=new Random();
			int num=randomGenerator.nextInt(100);
			System.out.println("Producer produces :"+num);
			try {
				//if queuesize ==size then the queue will wait till queuesize becomes less than size.
				queue.put(num);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void consume(){
		while(true){
			try {
				Thread.sleep(1000);
				//if queuesize ==0 then the queue will wait till queuesize becomes greater than 1.
				int num=queue.take();
				System.out.println("Consumer consumes :"+num);
				System.out.println("Queue size :"+queue.size());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		ProducerConsumer pc=new ProducerConsumer();
		
		Thread producerThread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				pc.produce();
			}
		});
		
		
		Thread consumerThread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				pc.consume();
			}	
		});
		
		producerThread.start();
		consumerThread.start();
		
		try {
			producerThread.join();
			consumerThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
