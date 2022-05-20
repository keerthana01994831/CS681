package edu.umb.cs681.hw009;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentSingleton{
	private static AtomicReference<ConcurrentSingleton> ARinstance = new AtomicReference<>();
	public static ConcurrentSingleton getInstance(){
		
		ConcurrentSingleton cs_obj = ARinstance.get();
		
		if(cs_obj == null) {
			cs_obj = new ConcurrentSingleton();
			
			if(!ARinstance.compareAndSet(null, cs_obj)) {
				cs_obj = ARinstance.get();
			}
		}
		return cs_obj;
	} 
	
	public static void main(String[] args){
		for(int i=0; i<10; i++){
		new Thread(
				()->{System.out.println(ConcurrentSingleton.getInstance());}).start();
		}
	}
}