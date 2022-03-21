package edu.umb.cs681.hw07;
public class RunnableConcurrentSingleton implements Runnable {
	public void run() {
		System.out.println(ConcurrentSingleton.getInstance());
	}
	public static void main(String[] args) {
		Thread a = new Thread(new RunnableConcurrentSingleton());
		Thread b = new Thread(new RunnableConcurrentSingleton());
		Thread c = new Thread(new RunnableConcurrentSingleton());
		a.start();
		b.start();
		c.start();
	}
}