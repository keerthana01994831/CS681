package edu.umb.cs681.hw05;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
public class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator {
	private boolean done = false;
	private ReentrantLock lock = new ReentrantLock();
	public RunnableCancellablePrimeGenerator(long from, long to) {
		super(from, to);
	}
	public void setDone() {
		lock.lock();
		try {
			done = false;
		} finally {
			lock.unlock();
		}
	}
	public void generatePrimes() {
		for (long n = from; n <= to; n++) {
			lock.lock();
			try {
				if (done) {
					break;
				}
				if (isPrime(n)) {
					this.primes.add(n);
				}
			} finally {
				lock.unlock();
			}
		}
	}
	public static void main(String[] args) {
		RunnableCancellablePrimeGenerator generator_obj = new RunnableCancellablePrimeGenerator(1,100);
		Thread thread = new Thread(generator_obj);
		thread.start();
		generator_obj.setDone();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//generator.getPrimeNumbers().forEach( (Long prime)-> System.out.println(prime + ", ") );
		LinkedList<Long> primes = generator_obj.getPrimeNumbers();
		for(long prime : primes) {
			System.out.println(prime);
		}
		System.out.println("\n" + generator_obj.getPrimeNumbers().size() + " prime numbers are found.");
	}
}