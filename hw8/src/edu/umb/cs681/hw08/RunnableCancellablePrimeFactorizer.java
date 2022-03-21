package edu.umb.cs681.hw08;
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
				if( n > 2 && n%2 == 0) {
					n++;
					continue;
				}
				if(div % n == 0) {
					factors.add(n);
					div /= n;
				}else {
					if(n==2){ n++; }
					else{ n += 2; }
				}
			} 
			finally {
				lock.unlock();
			}
		}
	}
}
