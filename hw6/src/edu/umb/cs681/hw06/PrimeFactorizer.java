package edu.umb.cs681.hw06;
import java.util.LinkedList;
// Generates prime factors of a given number (dividend)
// Prime factors are generated in the range of 2 and dividend
// from is the lower bound of the range (2)
// to is the upper bound of the range (d
public class PrimeFactorizer {
	protected long div, from, to;
	protected LinkedList<Long> factors = new LinkedList<Long>();
	public PrimeFactorizer(long x){
		if(x >= 2){
			this.div = x;
			this.from = 2;
			this.to = x;
		}else{
			throw new RuntimeException("Input must be >= 2");
		}
	}
	public long getDividend() {
		return div; 
	}
	public long getFrom(){ 
		return from; 
	}
	public long getTo(){ 
		return to; 
	}
	public LinkedList<Long> getPrimeFactors(){ 
		return factors; 
	}
	public void generatePrimeFactors() {
		long n = 2;
		while( div != 1 && n <= to ){
			if(div % n == 0) {
				factors.add(n);
				div /= n;
			}else {
				if(n==2){ 
					n++; 
				}
				else{
					n += 2; 
				}
			}
		}
	}
}