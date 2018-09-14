package tests;

import java.util.Random;

public class randNormalizer {
	double experPercentSum;
	int experNum;

	public randNormalizer() {
		experPercentSum = 0.0;
		experNum = 0;
	}

	public void reset() {
		experPercentSum = 0.0;
		experNum = 0;
	}

	public double norm(double percent) {
		if (percent > 0.9999999999999998) {
			percent = 0.9999999999999998;
		} else if (percent < 0) {
			percent = 0;
		}
		experPercentSum += percent;
		++experNum;

		double experPer = experPercentSum / experNum;

		//System.err.print("p: " + percent  + " pe: " + experPer + " ");
		if (percent <= experPer) {
			// percent / experPer <= 1, since percent <= experPer
			//System.err.println("p: " + percent + " pe: " + experPer);
			//System.err.print("p/pe: " + (percent / experPer));
			//System.err.println(" pn: " +  (0.5 * (percent / experPer)));
			return 0.5 * (percent / experPer);
		} else {
			// 0 <= (percent - experPer) <= 1, since percent >= experPer
			//                                 and 0<=p<=1 for both
			// percent <= 1 -> percent - experPer <= 1 - experPer
			// percent-experPer / 1-experPer <= 1
			//System.err.println("pn: " + (0.5 + 0.5 * (percent - experPer) / (1 - experPer)));
			//System.err.println("p-pe: " + (percent - experPer) + " 1-pe: " + (1.0 - experPer));
			//System.err.print("p-pe/1-pe: " + ((percent - experPer) / (1.0 - experPer)));
			//System.err.println(".5p-pe/1-pe: " + (0.5*(percent - experPer) / (1.0 - experPer)));
			return 0.5 + 0.5 * (percent - experPer) / (1 - experPer); 
		}
	}

	public double peek(double percent) {
		if (percent > 0.9999999999999998) {
			percent = 0.9999999999999998;
		} else if (percent < 0) {
			percent = 0;
		}
		double ePSTemp = experPercentSum + percent;
		int eNTemp = experNum + 1;

		double experPer = ePSTemp / eNTemp;

		//System.err.print("p: " + percent  + " pe: " + experPer + " ");
		if (percent <= experPer) {
			// percent / experPer <= 1, since percent <= experPer
			//System.err.println("p: " + percent + " pe: " + experPer);
			//System.err.print("p/pe: " + (percent / experPer));
			//System.err.println(" pn: " +  (0.5 * (percent / experPer)));
			return 0.5 * (percent / experPer);
		} else {
			// 0 <= (percent - experPer) <= 1, since percent >= experPer
			//                                 and 0<=p<=1 for both
			// percent <= 1 -> percent - experPer <= 1 - experPer
			// percent-experPer / 1-experPer <= 1
			//System.err.println("pn: " + (0.5 + 0.5 * (percent - experPer) / (1 - experPer)));
			//System.err.println("p-pe: " + (percent - experPer) + " 1-pe: " + (1.0 - experPer));
			//System.err.print("p-pe/1-pe: " + ((percent - experPer) / (1.0 - experPer)));
			//System.err.println(".5p-pe/1-pe: " + (0.5*(percent - experPer) / (1.0 - experPer)));
			return 0.5 + 0.5 * (percent - experPer) / (1 - experPer); 
		}
	}

	public double next() {
		double percent = rand(0);

		experPercentSum += percent;
		++experNum;

		if (percent <= experPercentSum) {
			return (0.5 * percent * experNum) / experPercentSum;
		} else {
			double experPer = experPercentSum / experNum;
			return 0.5 + 0.5 * (percent - experPer) / (1 - experPer); 
		}
	}

	private double rand(int i) {
		if (i >= 1) {
			return Math.random();
		}
		Random r = new Random();
		double p = r.nextFloat()*0.2+0.8;
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

		randNormalizer rN = new randNormalizer();
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
			System.out.println("pe" + (rN.experPercentSum / rN.experNum));
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
			System.out.println("\tpe: " + (rN.experPercentSum / rN.experNum));
		}
		System.out.println("Tests averaged " + ((double)sum / (double)num));

	}

}
