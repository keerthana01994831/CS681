package edu.umb.cs681.hw07;
import java.util.concurrent.locks.ReentrantLock;
public class ConcurrentSingleton {
	private static ConcurrentSingleton concurrent_singleton = null;
	private static ReentrantLock lock = new ReentrantLock();
	public static ConcurrentSingleton getInstance(){
		lock.lock();
		try {
			if (concurrent_singleton == null)
				concurrent_singleton = new ConcurrentSingleton();
			return concurrent_singleton;
		} finally {
			lock.unlock();
		}
	}
}