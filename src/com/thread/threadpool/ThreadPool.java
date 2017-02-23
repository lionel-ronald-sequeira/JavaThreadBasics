package com.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable{
	public int id;
	
	public Processor(int id) {
		this.id=id;
	}
	
	@Override
	public void run() {
		System.out.println("Starting task :"+id);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Completed task :"+id);
	}
}
public class ThreadPool {
	public static void main(String[] args) {
		//created thread using executor service
		ExecutorService executor=Executors.newFixedThreadPool(2);
		for(int i=0;i<5;i++){
			executor.submit(new Processor(i));
		}
		executor.shutdown();
		try {
			//making the main thread to wait till all threads have finished.
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("All tasks are completed");
	}
}
