package edu.umb.cs681.hw0017;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors; 
import java.util.stream.Stream;

public class CovidDataCSV implements Runnable{
    public static void main(String[] args) {
    	ExecutorService e = Executors.newCachedThreadPool();
		try{
			for(int i=0;i<4;i++){
				e.execute(new CovidDataCSV());
			}
			TimeUnit.SECONDS.sleep(5);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		e.shutdown();
    }
	
	@SuppressWarnings("rawtypes")
	@Override
	public void run() {
		
		Path file_path = Paths.get("./src/Data/CovidPVI_Data.txt"); 
		try( Stream<String> data = Files.lines(file_path) ){
			List<List<String>> matrix = data.map( d -> { 
				return Stream.of( d.split(",")).map(value->value.substring(0, value.length())) .collect( Collectors.toList() ); 
			}).collect(Collectors.toList());
			List totalPopulation = matrix.parallelStream().filter((i) -> i.get(4).contains("Massachusetts")).collect(Collectors.toList());
			List suffolkDeaths = matrix.parallelStream().filter((i) -> i.get(5).contains("Suffolk")).collect(Collectors.toList()).get(0);
			
			String massachusettsDeaths = matrix.parallelStream().filter((i) -> i.get(4).contains("Massachusetts"))
					.map((i) -> i.get(7)).reduce("0", (subtotal, element) -> String.valueOf(Integer.parseInt(subtotal) + Integer.parseInt(element)));
			System.out.println("\n1. Deaths occurred in the Suffolk county of the Massachusetts state are: " + suffolkDeaths.get(7)+ " - " + Thread.currentThread().getName());
			System.out.println("\n2. Total deaths in State of Massachusetts are: " + massachusettsDeaths+ " - " + Thread.currentThread().getName());
			System.out.println("\n3. Average number of deaths in State of Massachusetts are: " + Integer.parseInt(massachusettsDeaths)/totalPopulation.size()+ " - " + Thread.currentThread().getName());
		}
        catch(IOException e) {
        	e.printStackTrace(); 
        }
	}
} 
