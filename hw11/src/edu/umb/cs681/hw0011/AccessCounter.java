package edu.umb.cs681.hw0011;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AccessCounter {
	private static Map<Path, Integer> accessCountMap = new HashMap<>();

	private ReentrantReadWriteLock rwLock;

	public AccessCounter(){
		rwLock = new ReentrantReadWriteLock();
	}

	public void increment(Path path){
		rwLock.writeLock().lock();
		try{
			if(accessCountMap.containsKey(path))
				accessCountMap.put(path,accessCountMap.get(path)+1);
			else
				{accessCountMap.put(path,1);}
		} finally {
			rwLock.writeLock().unlock();
		}
	}

	public int getCount(Path path){
		rwLock.readLock().lock();
		int count=0;
		try{
			if(accessCountMap.containsKey(path))
				count= accessCountMap.get(path);
		} finally {
			rwLock.readLock().unlock();
		}
		return count;
	}
	
	private static AccessCounter ac = null;
	private static ReentrantLock lock1 = new ReentrantLock();
	public static AccessCounter getInstance(){
		lock1.lock();
		try{
		if(ac==null){ ac = new AccessCounter(); }
		return ac;
		}finally{
		lock1.unlock();
	}}



}

