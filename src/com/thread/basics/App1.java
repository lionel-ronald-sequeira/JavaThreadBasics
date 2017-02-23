package com.thread.basics;

/**
 * Program to implement the thread by extending Thread class.
 * @author lionel
 *
 */
class Runner extends Thread{
	@Override
	public void run() {
		for(int i=0;i<10;i++){
			System.out.println("Hello :"+i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	
}

public class App1 {
	public static void main(String[] args) {
		Runner runner=new Runner();
		/* if you directly call run method on instance runner 
		 * a new thread wont start.The execution will start in 
		 * the main thread itself. To start a new thread of 
		 * execution use start method. 
		 */
		runner.start();
	}
}
