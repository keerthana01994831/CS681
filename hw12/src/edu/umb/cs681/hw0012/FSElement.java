package edu.umb.cs681.hw0012;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

@SuppressWarnings("unused")
public abstract class FSElement {
	
	private String dir_name;
	private LocalDateTime createdAt; 
	protected int size;
	
	private Directory parent;
	protected boolean isFile;
	protected boolean isDir;
	
	protected ReentrantLock rl; 
	
	
	public FSElement (Directory parent, String dir_name, int size, LocalDateTime createdAt){
		this.parent = parent;
		this.dir_name = dir_name;
		this.size = size;
		this.createdAt = createdAt;
		
	}
	
	public Directory getParent () {
		return this.parent;
	}
	
	public boolean isFile (){
		return this.isFile;
		
	}
	
	public abstract boolean isDirectory();
	
	public void setName (String dir_name){
		this.dir_name = dir_name;
	}
	
	public String getName (){
		return this.dir_name;
	}
	
	
	
	public int getSize () {
		return this.size;
	}

	public LocalDateTime getCreated() {
		return createdAt;
	}



}
