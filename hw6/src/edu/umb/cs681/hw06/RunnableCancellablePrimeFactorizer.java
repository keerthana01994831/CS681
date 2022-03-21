package edu.umb.cs681.hw06;
import java.util.concurrent.locks.ReentrantLock;
public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {
	private boolean done = false;
	private ReentrantLock lock = new ReentrantLock();
	public RunnableCancellablePrimeFactorizer (long div, long from, long to){
		super(div, from, to);
	}
	public void setDone() {
		lock.lock();
		try {
			done = false;
		}
		finally {
			lock.unlock();
		}
	}
	public void generatePrimeFactors(){
		long n = from;
		while( div != 1 && n <= to ){
			lock.lock();
			try{
				if(done){
					break;
				}
				if( div > 2 && n%2 == 0) {
					n++;
					continue;
				}
				if(div % n == 0) {
					factors.add(n);
					div /= n;
				}else {
					if(n==2){ 
						n++; 
					}
					else{
						n += 2;
					}
				}
			} finally {
				lock.unlock();
			}
		}
	}
	public static void main(String[] args) {
		RunnableCancellablePrimeFactorizer gen = new RunnableCancellablePrimeFactorizer(963, 2,(long) Math.sqrt(963));
		Thread thread = new Thread(gen);
		thread.start();
		gen.setDone();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Prime factors for the given value are: " + gen.getPrimeFactors() + "\n");
	}
}