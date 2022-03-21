package edu.umb.cs681.hw01;
import java.util.ArrayList;
import java.util.Comparator; 
import java.util.List;
import java.util.stream.Collectors;
public class Car {
    private String car_make;
    private String car_model;
    private int car_mileage;
    private int car_year;
    private int dom_count;
    public Car(String make, String model, int year, int mileage, int dominationCount) {
		this.car_make = make;
		this.car_model = model;
		this.car_year = year;
		this.car_mileage = mileage;
		this.dom_count = dominationCount;
    }
    public int getDomCount() {
    	return this.dom_count; 
    }
    public String getMake() {
    	return this.car_make; }
    public String getModel() {
    	return this.car_model; 
    }
    public int getYear() {
    	return this.car_year; 
    }
    public float getPrice() {
    	return this.car_year; 
    }
    public int getMileage() {
    	return this.car_mileage; 
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		List<Car> cars_list = new ArrayList<>();
		cars_list.add(new Car("Lykn", "Hypersport", 2019, 3, 1)); 
		cars_list.add(new Car("Venom", "GT", 2018, 4, 3));
		cars_list.add(new Car("Buggati", "Vyron", 2020, 2, 2)); 
		cars_list.add(new Car("Mercedes", "AMG", 2016, 12, 4));
		System.out.println("Ordered by Mileage:");
		List<Car> orderedByMileage = (List)cars_list.stream().sorted(Comparator.comparingInt(Car::getMileage)).collect(Collectors.toList());
		orderedByMileage.forEach(car_mileage -> System.out.println(car_mileage));
		System.out.println("Ordered by Make:");
		List<Car> orderedByMake = (List)cars_list.stream().sorted(Comparator.comparing(Car::getMake)).collect(Collectors.toList());
		orderedByMake.forEach(car_make -> System.out.println(car_make));
		System.out.println("Ordered by Domination Count:");
		List<Car> orderedByDomCount = (List)cars_list.stream().sorted(Comparator.comparingInt(Car::getDomCount)).collect(Collectors.toList());
		orderedByDomCount.forEach(dom_count -> System.out.println(dom_count));
		System.out.println("Ordered by Model:");
		List<Car> orderedByModel = (List)cars_list.stream().sorted(Comparator.comparing(Car::getModel)).collect(Collectors.toList());
		orderedByModel.forEach(model -> System.out.println(model));
	} 
   }