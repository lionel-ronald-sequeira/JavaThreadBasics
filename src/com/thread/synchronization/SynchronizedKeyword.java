package com.thread.synchronization;
/**
 * Program for synchronized keyword.
 * @author lionel
 * 
 */
public class SynchronizedKeyword {
	
	public int count=0;
	
	/*
	 * any thread calling increment will acquire the lock so that when it 
	 * accesses a shared variable no other thread gets access to it.
	 */
	public synchronized void increment()
	{
		count++;
	}
	public static void main(String[] args) {
		SynchronizedKeyword sk=new SynchronizedKeyword();
		sk.work();
		
	}
	
	public void work(){
		Thread t1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<=1000;i++){
					increment();
				}
			}
		});
		
		Thread t2=new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<=1000;i++){
					increment();
				}
			}
		});
		
		t1.start();
		t2.start();
		try {
			
			/*
			 * join keyword is used so that one thread pauses for other thread to complete.
			 * In this example t1,t2 should complete before main thread finishes.
			 */
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		System.out.println(count);
	}
}
