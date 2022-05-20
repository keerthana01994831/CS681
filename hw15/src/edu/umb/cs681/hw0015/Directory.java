package edu.umb.cs681.hw0015;


import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

public class Directory extends FSElement{
	
	private ConcurrentLinkedQueue<FSElement> children;

	public Directory(Directory parent, String name) {
		super(parent, name, 0, LocalDateTime.now());
		children = new ConcurrentLinkedQueue<FSElement>();
		isDir = true;
		rl = new ReentrantLock();
	}

	public ConcurrentLinkedQueue<FSElement> getChildren() {
		
		return this.children;
		
	}
	
	public Directory getDirectory(String directory) {
		rl.lock();
		try {
			String foundDirectory = null;
			Directory dir = null;
			
			
			Iterator<FSElement> itr = children.iterator();
			while (itr.hasNext()) {
			    foundDirectory = itr.next().getName();
				
				if (foundDirectory == directory){
					
					dir = (Directory) itr.next();
				}
			}
			
			return dir;
		}finally {
			rl.unlock();
		}
	}
	
	public void appendChild (FSElement element) {
		rl.lock();
		try {
			children.add(element);
		} finally {
			rl.unlock();
		}
	}
	public int getTotalSize () {
		rl.lock();
		try {
			int totalSize = 0;
			
			for (FSElement element: children){
			
					totalSize+= element.getSize();
				
			}
			
			return totalSize;
		} finally {
			rl.unlock();
		}
		
	}
	
	public int getSize() {
		return this.size;
	}
    @Override 
	public String toString() {
		return getName();
	}


	@Override
	public boolean isDirectory() {
		return this.isDir;
	}

}
