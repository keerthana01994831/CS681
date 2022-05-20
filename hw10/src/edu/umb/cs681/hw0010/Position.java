package edu.umb.cs681.hw0010;

import java.util.*;
public final class Position implements Runnable{
	private final double latitude,longitude,altitude;
	
	public Position(double longitude,double latitude,double altitude)
	{
		this.latitude=latitude;
		this.longitude=longitude;
		this.altitude=altitude;
	}
	
	public void run(){  
		Position pos=new Position(this.longitude,this.latitude,this.altitude);
		Aircraft airCraft=new Aircraft(pos);
		airCraft.setPosition(pos);
		System.out.println("Position : "+airCraft.getPosition()); 
		System.out.println("poition after change in latitude : " + pos.changeLat(163.023));
	}  
  
	public static void main(String args[]){  
		Runnable r1 = new Position(20.6,75.125,48.425);
		Thread th1 = new Thread(r1, "New Thread");    	
		th1.start(); 
		
	}  
	
	public double getLatitude()
	{
		return latitude;
	}
	
	public double getLongitude()
	{
		return longitude;
	}
	
	public double getAltitude()
	{
		return altitude;
	}
	
	
	public String toString(){
		return latitude + " latitude," + longitude + " longitude," + altitude + " altitude.";
	}
	
	public boolean equals( Position pos ){
	if( this.toString().equals(pos.toString()) ){
		return true; 
		}
	else{ 
		return false; 
		} 
	}	
	
	ArrayList<Double> getCoordinate(){
		ArrayList<Double> co_ordinates=new ArrayList<>();
		co_ordinates.add(this.latitude);
		co_ordinates.add(this.longitude);
		co_ordinates.add(this.altitude);
		return co_ordinates;
	}
	Position changeLat(double newLatitude){
		return new Position(this.longitude,newLatitude , this.altitude); 
		
		}
		
	Position changeLong(double newLongitude){
		return new Position(newLongitude, this.latitude, this.altitude); 
	}

	Position changeAlt(double newAltitude){
		return new Position(this.longitude, this.latitude, newAltitude); 
	}

	
	
	
}
class Aircraft {
	private Position position; 
	public Aircraft(Position pos){
		position = pos; 
		}
	public void setPosition(Position pos){
		position = pos;  
	 }
	public Position getPosition(){
		return position;
	} 
}




