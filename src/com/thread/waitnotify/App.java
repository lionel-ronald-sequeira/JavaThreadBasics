package com.thread.waitnotify;

public class App {
	public static void main(String[] args) {
		Processor processor=new Processor();
		Thread produceThread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					processor.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread consumeThread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					processor.consume();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		produceThread.start();
		consumeThread.start();
		
		try {
			produceThread.join();
			consumeThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
