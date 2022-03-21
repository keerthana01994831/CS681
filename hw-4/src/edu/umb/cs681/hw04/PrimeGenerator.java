package edu.umb.cs681.hw04;
import java.util.LinkedList;
public class PrimeGenerator {
    protected long from, to;
    protected LinkedList<Long> primes = new LinkedList<Long>();
    public PrimeGenerator(long from, long to){
        if(from >= 1 && to > from){
			this.from = from;
			this.to = to; 
		}else{
			throw new RuntimeException("Wrong input values: from=" + from + " to="+ to);
		} 
    }
	public LinkedList<Long> getPrimes(){ 
		return primes; 
	};
	protected static boolean isPrime(long n) {  
	    if (n <= 1) {  
	        return false;  
	    }  
	    for (long i = 2; i < Math.sqrt(n); i++) {  
	        if (n % i == 0) {  
	            return false;  
	        }  
	    }  
	    return true;  
	}  
	public void generatePrimes(){
	    for (long n = from; n <= to; n++) {
	if( isPrime(n) ){ primes.add(n); } }
	}

}
