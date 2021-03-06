package edu.umb.cs681.hw0014;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
	final ConcurrentMap<Path, AtomicLong> counterMap = new ConcurrentHashMap<>();
	private ReentrantLock lock = new ReentrantLock();  

	public void increment(Path path){
		lock.lock();
		try{
			counterMap.computeIfAbsent(path, p -> new AtomicLong()).incrementAndGet();
		} finally {
			lock.unlock();
		}
	}

	public long getCount(Path path){
		lock.lock();
		try{
			AtomicLong l = counterMap.get(path);
		    return l == null ? 0 : l.get();
		} finally {
			lock.unlock();
		}
	}
	
	private static AccessCounter ac = null;
	private static ReentrantLock lock1 = new ReentrantLock();
	// Factory method to create or return the singleton instance
	public static AccessCounter getInstance(){
		lock1.lock();
		try{
			if(ac==null){ ac = new AccessCounter(); }
			return ac;
		}finally{
			lock1.unlock();
		}
	}



}

