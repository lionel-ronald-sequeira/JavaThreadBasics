package com.thread.synchronization;

import java.util.Scanner;
/**
 * Program for volatile keyword.
 * @author lionel
 */
class Processor extends Thread{
	/*
	 *Volatile keyword
	 *volatile keyword prevents thread to not cache the variable when its not changed within the thread. 
	 */
	private volatile boolean running=true;
	@Override
	public void run() {
		while(running){
			System.out.println("Hello");
		}
	}
	
	public void shutdown(){
		running=false;
	}
}

public class VolatileKeyword {
	public static void main(String[] args) {
		Processor proc=new Processor();
		proc.start();
		
		Scanner sc=new Scanner(System.in);
		sc.hasNextLine();
		
		proc.shutdown();
	}
}
