package com.thread.waitnotify;

import java.util.LinkedList;

public class Processor {
	
	private LinkedList<Integer>list=new LinkedList<Integer>();
	private final int LIMIT=10;
	private Object lock=new Object();
	
	public void produce()throws InterruptedException{
		int value=0;
		while(true){
			synchronized(lock){
				while(list.size()==LIMIT){
					//wait must be used in synchronized block.
					lock.wait();
				}
				list.add(value++);
				//notify must be used in synchronized block.
				lock.notify();
			}	
		}
	}
	
	public void consume()throws InterruptedException{
		while(true){
			synchronized (lock) {
				while(list.size()==0){
					lock.wait();
				}
				System.out.print("List size: "+list.size());
				int value=list.removeFirst();
				System.out.println(" : Element removed "+value);
				lock.notify();
			}
			
		}
	}
}
