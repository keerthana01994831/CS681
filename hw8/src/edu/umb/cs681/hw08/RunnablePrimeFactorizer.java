package edu.umb.cs681.hw08;

public class RunnablePrimeFactorizer extends PrimeFactorizer implements Runnable {
	public RunnablePrimeFactorizer(long div, long from, long to) {
		super(div);
		if (from >= 2 && to >= from) {
			this.from = from;
			this.to = to;
		} else {
			throw new RuntimeException("from must be >= 2, and to must be >= from." + "from==" + from + " to==" + to);
		}
	}
	public void generatePrimeFactors() {
		long n = from;
		while( div != 1 && n <= to ){
			if( n > 2 && n%2 == 0) {
				n++;
				continue;
			}
			if(div % n == 0) {
				factors.add(n);
				div /= n;
			}else {
				if(n==2){ n++; }
				else{ n += 2; }
			}
		}
	}
	public void run() {
		generatePrimeFactors();
		System.out.println("Thread #" + Thread.currentThread().getId() + " generated " + factors);
	}
}