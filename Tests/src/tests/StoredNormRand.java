package tests;

public class StoredNormRand {
	double []stored;
	double sum;
	int num;
	boolean filled;
	
	// Constructor requires size of stored arr
	public StoredNormRand(int size) {
		if (size <= 0) {
			size = 1;
		}
		stored = new double[size];
		num = 0;
		sum = 0.0;
		filled = false;
	}
	
	// returns the average percent from the currently
	// filled in arr
	public double getAvgPercent() {
		if (filled) {
			return sum / stored.length; 
		}
		if (num == 0) {
			return 0.5;
		}
		return sum / num;
	}
	
	// stores a value in the arr, which will alter
	// the Average Percent
	public void store(double p) {
		if (filled) {
			sum -= stored[num];
		}
		stored[num] = p;
		sum += p;
		if (++num >= stored.length) {
			num = 0;
		}
	}
	
	// resets the array to null
	public void reset() {
		filled = false;
		num = 0;
		sum = 0.0;
	}
	
	// uses the default rand function to find
	// a base percent for norm
	public double norm() {
		return norm(rand());
	}
	
	// normalizes the given percent based on the
	// average percent. A high average will start
	// to return more lower numbers, and vice versa
	public double norm(double p) {
		if (p > 0.9999999999999998) {
			p = 0.9999999999999998;
		} else if (p < 0) {
			p = 0;
		}
		
		store(p);
		
		double avg = getAvgPercent();
		
		if (p <= avg) {
			return (0.5 * p / avg);
		} else {
			return (0.5 + 0.5 * (p - avg) / (1 - avg));
		}
	}
	
	// uses the default rand function to find
	// a base percent for peek
	public double peek() {
		return peek(rand());
	}
	
	// peeks at what the return value would have been
	// if the given value was normalized. Does not alter
	// the array
	public double peek(double p) {
		if (p > 0.9999999999999998) {
			p = 0.9999999999999998;
		} else if (p < 0) {
			p = 0;
		}
		
		double nAvg;
		
		if (filled) {
			int oldNum = num - 1;
			if (oldNum < 0) {
				oldNum = stored.length - 1;
			}
			nAvg = getAvgPercent() + (p - stored[oldNum])/stored.length;
		} else {
			nAvg = p;
			for (int i = 0; i < num; ++i) {
				nAvg += stored[i];
			}
			nAvg /= (num + 1);
		}
		
		if (p <= nAvg) {
			return (0.5 * p / nAvg);
		} else {
			return (0.5 + 0.5 * (p - nAvg) / (1 - nAvg));
		}
	}
	
	// default rand function, override this to get
	// different behaviors from the default
	public double rand() {
		double p = Math.random();
	
		if (p < 0) {
			p = 0;
		} else if (p >= 1) {
			p = 0.9999999999999998;
		}
		
		return p;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StoredNormRand rN = new StoredNormRand(100);
		
		int total;
		double perc;
		int [][] values;
		int i,j;
		
		for (j = 0; j < 1; ++j) {
		total = 20;
		
		values = new int[10][];
		for (i = 0; i < 10; ++i) {
			values[i] = new int[2];
			values[i][0] = 0;
			values[i][1] = 0;
		}
		
		//System.out.println("orig\t\tnorm");
		for (i = 0; i < total; ++i) {
			perc = Math.random();//rN.rand(0);
			
			if (i == 0 && perc == 0.0) {
				--i;
				continue;
			}
			//System.out.println(perc + "\t\t" + rN.norm(perc));
			++values[(int)Math.floor(10.0*perc)][0];
			++values[(int)Math.floor(10.0*rN.norm(perc))][1];
		}
		
		System.out.println("val\torig\tnorm");
		for (i = 0; i < 10; ++i) {
			System.out.println((i+1) + "\t" + values[i][0] + "\t" + values[i][1]);
		}
		System.out.println("pe" + (rN.getAvgPercent()));
		}
		
		int sum = 0, num = 0;
		for (i = 0; i < 100; ++i) {
			j = 0;
			while ((rN.peek(perc = Math.random())) < 0.8) {
				++j;
			}
			sum += j;
			++num;
			rN.norm(perc);
			System.out.println("Test " + i + " took " + j + " tries");
			System.out.println("\tpe: " + (rN.getAvgPercent()));
		}
		System.out.println("Tests averaged " + ((double)sum / (double)num));


	}

}
