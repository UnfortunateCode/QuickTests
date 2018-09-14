package tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class pointNines {
	public static int eval(int points) {
		 int p = points;
		 int d = 1;
		 int a = 0;
		 while (p > a + 5) {
			 a += 5;
			 ++d; 
			 p -= a;
		 }
		 return p / d + a;
	 }
	
	/**
	 * The total for a level is the previous level total plus an increment.
	 * The increment is 1 for levels 1-5, 2 for 6-10, etc.
	 * 
	 * @param lvl
	 * @return x[n] = x[n-1]+floor(x[n-1] / 5)
	 */
	public static int numTotal(int lvl) {
		return (1+(lvl-1)/5)*(1+(lvl-1)%5)+5*(((lvl-1)/5)*(1+(lvl-1)/5)/2);
	}
	
	
	public static void main(String[] args) {
		/*try {
			String s;
			FileWriter fw = new FileWriter("a.txt");
			for (int i = 0; i < 15000; ++i) {
				fw.append((int)(Math.random()*100) + ",");
			}
			fw.close();
			FileReader fr = new FileReader("a.txt");
			BufferedReader br = new BufferedReader(fr);
			s = br.readLine();
			System.out.println(s);
		} catch (Exception e) {
			
		}
		/*
		Scanner sc = new Scanner(System.in);
		String in;
		while (true) {
			//System.out.println(">");
			try {
				in = sc.next("([A-Z]).*");
				//System.out.println(in);
				System.out.println(in.charAt(0));
			} catch (Exception e) {
				//System.out.println(e);
				sc = new Scanner(System.in);
			}
		}*/
		
		
		 
		double d;
		for (int i = 0; i < 100; ++i) {
			System.out.println(i + ": " + numTotal(i) + " " + (numTotal(i)-numTotal(i-1)));
		}
		System.out.println("1900 points gets one to lvl " + eval(1900));
		System.out.println("or two to lvl " + eval(950));
		System.out.println("or three to lvl " + eval(633));
		System.out.println("or four to lvl " + eval(1900/4));
		System.out.println("or five to lvl " + eval(1900/5));
		System.out.println("or six to lvl " + eval(1900/6));
		System.out.println("or seven to lvl " + eval(1900/7));
		System.out.println("or all eight to lvl " + eval(1900/8));
		
		d = .9;
		int i = 1;
		
		while(Math.floor(d) == 0) {
			++i;
			d =  d * .1 + .9;
			System.err.println(i + ": " + d);
			
		}
	}
}
