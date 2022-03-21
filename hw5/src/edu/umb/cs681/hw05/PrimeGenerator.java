package edu.umb.cs681.hw05;
import java.util.LinkedList;
public class PrimeGenerator {
	protected long from, to;
	protected LinkedList<Long> primes = new LinkedList<Long>();
	public PrimeGenerator(long from, long to) {
		if(from >= 1 && to > from) {
			this.from = from;
			this.to = to;
		}else {
			throw new RuntimeException("from must be greater than 1 and to must be greater than from." + from + " to=" + to);
		}
	}
	public LinkedList<Long> getPrimeNumbers() { 
		return primes; 
	}
	protected boolean isEven(long n) {
		if(n%2 == 0){
			return true; 
		}
		else{ 
			return false;
		}
	}
	protected boolean isPrime(long number) {
		// Numbers 0,1 and negative numbers are not prime
        if (number <= 1) {
            return false;
        }
 
        // 2 is prime
        else if (number == 2) {
            return true;
        }
 
        // Check if number is a multiple of 2 as all even numbers are not prime except 2
        else if (number % 2 == 0) {
            return false;
        }
 
        // Find if number has any other factor other than 1 and itself
        for (long n = 3; n <= (long) Math.sqrt(number); n += 2)
        {
            if (number % n == 0) {
                return false;
            }
        }
        return true;
	}
	public void generatePrimes(){
		for (long n = from; n <= to; n++) {
			if( isPrime(n) ){ 
				primes.add(n); 
			}
		}
	}
	// public static void main(String[] args) {
	// // Single-threaded prime number generation (with generatePrimes())
	// PrimeGenerator generate = new PrimeGenerator(1, 100);
	// generate.generatePrimes();
	// generate.getPrimeNumbers().forEach( (Long primeNumber)-> System.out.print(primeNumber + ", ") );
	// System.out.println("\n" + gen.getPrimeNumbers().size() + " prime numbers are found.");
	//
	// // Single-threaded prime number generation (without using generatePrimes())
	// PrimeGenerator generate2 = new PrimeGenerator(1, 100);
	// List<Long> primes = LongStream.rangeClosed(generate2.from, generate2.to)
	// .filter( (long num)->generate2.isPrime(num) )
	// .boxed()
	// .collect(Collectors.toList());
	// primes.forEach( (Long primeNumber)-> System.out.print(primeNumber + ", ") );
	// System.out.println("\n" + primes.size() + " prime numbers are found.");
	//
	// // Single-threaded prime number generation (without using generatePrimes())
	// PrimeGenerator generator3 = new PrimeGenerator(1, 100);
	// long size = LongStream.rangeClosed(generator3.from, generator3.to)
	// .filter( (long n)->gen3.isPrime(n) )
	// .reduce( 0L, (long count, long n)->{
	// System.out.print(n + ", ");
	// return ++count;} );
	// System.out.println("\n" + size + " prime numbers are found.");
	//
	// }
}