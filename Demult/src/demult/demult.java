package demult;

import java.util.LinkedList;

public class demult {
	
	public static final int[][][] odd = {{{0,0,0},{0,0,0},{0,0,0},{0,0,0}}, //0
	                                     {{1,1,0},{3,7,2},{7,3,2},{9,9,8}}, //1
	                                     {{0,0,0},{0,0,0},{0,0,0},{0,0,0}}, //2
										 {{1,3,0},{3,1,0},{7,9,6},{9,7,6}}, //3
										 {{0,0,0},{0,0,0},{0,0,0},{0,0,0}}, //4
										 {{0,0,0},{0,0,0},{0,0,0},{0,0,0}}, //5
										 {{0,0,0},{0,0,0},{0,0,0},{0,0,0}}, //6
										 {{1,7,0},{7,1,0},{3,9,2},{9,3,2}}, //7
										 {{0,0,0},{0,0,0},{0,0,0},{0,0,0}}, //8
										 {{1,9,0},{9,1,0},{7,7,4},{3,3,0}}};//9
	
	public static final int[][] div = {{0,0,0,0,0,0,0,0,0,0},
		 							   {0,1,2,3,4,5,6,7,8,9},
		 							   {0,0,0,0,0,0,0,0,0,0},
		 							   {0,7,4,1,8,5,2,9,6,3},
		 							   {0,0,0,0,0,0,0,0,0,0},
		 							   {0,0,0,0,0,0,0,0,0,0},
		 							   {0,0,0,0,0,0,0,0,0,0},
		 							   {0,3,6,9,2,5,8,1,4,7},
		 							   {0,0,0,0,0,0,0,0,0,0},
		 							   {0,9,8,7,6,5,4,3,2,1}};

	public static long[] demultiply(long prod) {
//System.out.println("DEMULTIPLYING: " + prod);
		int t = (int)(prod % 10);
//System.out.println("t: " + t);
		long []r = new long[2];
		if (t == 5) {
			r[0] = 5;
			r[1] = prod / 5;
		} else if (t % 2 == 0) {
			r[0] = 2;
			r[1] = prod / 2;
		} else {
			int []p = rintToArr(prod);
			int []a = new int[p.length];
			int []b = new int[p.length];
			int c;
			int m = 3;
			LinkedList<Fact> f = new LinkedList<Fact>();
			do {
				a[0] = odd[t][m][0];
				b[0] = odd[t][m][1];
				c = odd[t][m][2];
//System.out.print("p:");
//disp(p);
//System.out.println();
//System.out.print("a:");
//disp(a);
//System.out.println();
//System.out.print("b:");
//disp(b);
//System.out.println();
//System.out.println("c: " + c);
//System.out.println("d: " + 1);
			} while (!dm(p,a,b,c,1,f) && --m >= 0);
			
			if (f.size() > 0) {
				r = f.peek().get();
			} else {
				r[0] = 1;
				r[1] = prod;
			} 
		}
		
		return r;
	}
	public static void disp(int []a) {
		int i;
		for (i = 0; i < a.length; ++i) {
			System.out.print(" " + a[i]);
		}
	}
	public static boolean dm(int []p, int []a, int []b, long c, int d, LinkedList<Fact> f) {
//System.out.print("p:");
//disp(p);
//System.out.println();
//System.out.print("a:");
//disp(a);
//System.out.println();
//System.out.print("b:");
//disp(b);
//System.out.println();
//System.out.println("c: " + c);
//System.out.println("d: " + d);

        if (rarrToInt(a) * rarrToInt(b)<0) {
        	return false;
        }
//System.out.println(rarrToInt(a) + "\t*\t" + rarrToInt(b) + "\t=\t" + rarrToInt(a)*rarrToInt(b));

        if (d == p.length) {
        	if (rarrToInt(a) * rarrToInt(b) == rarrToInt(p) &&
        			rarrToInt(a) != 1 && rarrToInt(b) != 1) {
//System.out.println(rarrToInt(p) + "=" + rarrToInt(a) + "*" + rarrToInt(b) + "=" + rarrToInt(a)*rarrToInt(b));
        		f.add(new Fact(rarrToInt(a), rarrToInt(b)));
//System.out.println(f.getLast());
        		return true;
        	}
        	return false;
        }
        if (rarrToInt(a)*rarrToInt(b)>rarrToInt(p)) {
        	return false;
        } else if (rarrToInt(a) * rarrToInt(b) == rarrToInt(p)) {
        	f.add(new Fact(rarrToInt(a), rarrToInt(b)));
        	return true;
        }
		int i;
		long carry, sum=0;
		for (i = 1; i < d; ++i) {
			sum += a[i]*b[d-i];
		}
		for (i = 9; i >= 0; --i) {
			a[d] = i;
			b[d] = div[a[0]][(int)(((p[d] - (a[d]*b[0] + sum + c))%10 + 10) % 10)];
			carry = (sum + c + a[d]*b[0] + a[0]*b[d]) / 10;
			if (dm(p,a,b,carry,d+1,f)) {
				return true;
			}
		}
		a[d] = 0;
		b[d] = 0;
		return false;
	}
	public static int[] rintToArr(long num) {
//System.out.println("rintToArr of " + num);
		String s = Long.toString(num);
		int []p = new int[s.length()];
		for (int i = 0; i < p.length; ++i) {
//System.out.println("charAt(" + p.length + "-" + i + "-1)=" + s.charAt(p.length-i-1));
//System.out.println(Character.digit(s.charAt(p.length-i-1), 10));
			p[i] = Character.digit(s.charAt(p.length-i-1), 10);
//System.out.println(p[i]);
		}
//System.out.print("p: ");
//disp(p);
//System.out.println();
		return p;
		

	}
	public static long rarrToInt(int []arr) {
		long num = 0;
		int i;
		for (i = arr.length-1; i >=0; --i) {
			num = 10*num+arr[i];
		}
		return num;
	}
	public static String isPrime(long i) {
		String s = "";
		for (long m = (int)(Math.sqrt(i)); m > 1; --m) {
			if (i % m == 0) {
				s += m + " * " + (i/m);
				return s;
			}
		}
		return "true";
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long i, t;
		long []r;
		long n = 268435457193721L;
		//r = demultiply(n);
		//System.out.println(n + "=" + r[0] + "*" + r[1] + "=" + (r[0]*r[1]));
		for (i = n+2; i < n+100000; i+=2) {
			System.gc();
			t = System.currentTimeMillis();
			r = demultiply(i);
			t = System.currentTimeMillis() - t;
			System.out.print((i) + "\t=\t" + r[0] + "\t*\t" + r[1] + "\tin\t" + t);/*
			if (r[0] == 1 || r[1] == 1) {
				System.out.println("\t" + isPrime(2*i+1));
			} else {*/
				System.out.println();/*
			}*/
		}
	}

}
