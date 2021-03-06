package edu.umb.cs681.hw08;
import java.util.concurrent.locks.ReentrantLock;
public class RunnableCancellableInterruptiblePrimeFactorizer extends
RunnableCancellablePrimeFactorizer {
	private boolean done = false;
	private ReentrantLock lock = new ReentrantLock();
	public RunnableCancellableInterruptiblePrimeFactorizer(long div, long from, long to) {
		super(div, from, to);
	}
	public void setDone() {
		lock.lock();
		try {
			done = true;
		} 
		finally {
			lock.unlock();
		}
	}
	public void generatePrimeFactors() {
		long n = from;
		while (div != 1 && n <= to) {
			lock.lock();
			try {
				if (done) {
					System.out.println("Factors generation stopped");
					this.factors.clear();
					break;
				}
				if (n > 2 && n%2 == 0) {
					n++;
					continue;
				}
				if (div % n == 0) {
					factors.add(n);
					div /= n;
				} else {
					if (n == 2) {
						n++;
					} else {
						n += 2;
					}
				}
			} 
			finally {
				lock.unlock();
			}
			try {
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) {
				continue;
			}
		}
	}
	public static void main(String[] args) {
		RunnableCancellableInterruptiblePrimeFactorizer obj = new
		RunnableCancellableInterruptiblePrimeFactorizer(9,2, 100);
		Thread t = new Thread(obj);
		t.start();
		try {
			Thread.sleep(3000);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		obj.setDone();
		t.interrupt();
		try {
			t.join();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("\n" + "Prime Factors for the given dividends are: " + obj.getPrimeFactors());
	}
}