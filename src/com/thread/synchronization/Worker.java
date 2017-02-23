package com.thread.synchronization;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
/**
 * 
 * @author lionel
 *
 */
public class Worker {
	
	private Random random=new Random();
	
	private List<Integer>list1=new ArrayList<Integer>();
	private List<Integer>list2=new ArrayList<Integer>();
	
	private Object lock1=new Object();
	private Object lock2=new Object();
	
	/*
	 * If you apply synchronized keyword to stageOne and stageTwo methods since there is only one lock 
	 * if a thread2 has to do stageTwo and a thread1 is in stageOne then thread2 has to wait till thread1 finishes
	 * Since both are trying to update different shared variables ,you need to use synchronized block with different objects
	 * in both the methods.
	 * In this example any thread that wants execute stageOne has to acquire lock on lock1 
	 * and any thread that wants execute stageTwo has to acquire lock on lock2
	 * 
	 */
	public  void stageOne(){
		synchronized (lock1) {
			try{
				Thread.sleep(1);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			list1.add(random.nextInt(100));
		}
	}
	
	public  void stageTwo(){
		synchronized (lock2) {
			try{
				Thread.sleep(1);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			list2.add(random.nextInt(100));
		}
	}
	
	public void process(){
		for(int i=0;i<1000;i++){
			stageOne();
			stageTwo();
		}
	}
	
	public void main(){
		System.out.println("Starting.........");
		long start=System.currentTimeMillis();
		Thread t1=new Thread(new Runnable() {
			@Override
			public void run() {
				process();
			}
		});
		
		Thread t2=new Thread(new Runnable() {
			@Override
			public void run() {
				process();
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		long end=System.currentTimeMillis();
		long output=end-start;
		System.out.println("Time taken :"+output);
		System.out.println("List1 :"+list1.size()+" List2 :"+list2.size());
	}
}
