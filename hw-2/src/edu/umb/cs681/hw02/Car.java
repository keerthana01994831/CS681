package edu.umb.cs681.hw02;
import java.util.ArrayList; import java.util.List;
public class Car {
	private String car_make;
    private String car_model;
    private int car_mileage;
    private int car_price;
    private int car_year;
    private int dom_count;
    public Car(String make, String model, int year, int mileage,int price, int dominationCount) {
		this.car_make = make;
		this.car_model = model;
		this.car_year = year;
		this.car_mileage = mileage;
		this.car_price = price;
		this.dom_count = dominationCount;
    }
    public int getDomCount() {
    	return this.dom_count; 
    }
    public String getMake() {
    	return this.car_make;
    }
    public String getModel() {
    	return this.car_model; 
    }
    public int getYear() {
    	return this.car_year; 
    }
    public int getPrice() {
    	return this.car_price; 
    }
    public int getMileage() {
    	return this.car_mileage; 
    }
    public static void main(String[] args) {
    	List<Car> Car_list = new ArrayList<>();
		Car_list.add(new Car("Lykn", "Hypersport", 2019, 3, 10000000, 1)); 
		Car_list.add(new Car("Venom", "GT", 2018, 4, 20000000, 3));
		Car_list.add(new Car("Buggati", "Vyron", 2020, 2, 15000000, 2));
		Car_list.add(new Car("Mercedes", "AMG", 2016, 12, 100000, 4));
		int highest_cost = Car_list.stream().map((Car car) ->car.getPrice()).reduce(0, (result,price)->result > price ? result : price); 
		System.out.println("Price of most expensive car is $"+highest_cost);
		int lowest_cost = Car_list.stream().map((Car car) ->car.getPrice()).reduce(1000000000, (result, price)->price>result ? result :price);
		System.out.println("Price of cheapest car $"+lowest_cost);
		int avg_cost = Car_list.stream().map((Car car) ->car.getPrice()).reduce(0, (result,price) -> result+price, (finalResult,intermediateResult) -> finalResult)/Car_list.size();
		System.out.println("Avg price for the available cars is: " + avg_cost);
	}
}
