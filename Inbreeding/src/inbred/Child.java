package inbred;

import java.util.LinkedList;

public class Child {
	int id;
	LinkedList<Integer[]> anc;
	
	public Child(int p1, int p2, int maxH) {
		anc = new LinkedList<Integer[]>();
		for (int i = 0; i < maxH; ++i) {
			anc.add(new Integer[(int)Math.pow(2,i+1)]);
			if (i > 0) {
				for (int j = 0; j < anc.get(i).length; ++j) {
					anc.get(i)[j] = -1;
				}
			}
		}
		anc.get(0)[0] = p1;
		anc.get(0)[1] = p2;
	}
	
	public String toString() {
		String s = id + ": ";
		int i;
		for (Integer[] a : anc) {
			s += "[ ";
			for (i = 0; i < a.length; ++i) {
				s += a[i] + " ";
			}
			s += "] ";
		}
		return s;
	}
	
	public Child mateWith(Child b) {
		Child c = new Child(id, b.id, anc.size());
		int M;
		for (int i = 1; i < anc.size(); ++i) {
			M = anc.get(i).length / 2;
			for (int j = 0; j < M; ++j) {
				c.anc.get(i)[j] = anc.get(i-1)[j];
				c.anc.get(i)[j+M] = b.anc.get(i-1)[j];
			}
		}
		
		return c;
	}
	
	public boolean relatedTo(Child b) {
		if (id == b.id) {
			return true;
		}
		
		for (int i = 0; i < anc.size(); ++i) {
			if (anc.get(i)[0] == -1) {
				return false;
			}
			for (int j = 0; j < anc.get(i).length; ++j) {
				for (int k = 0; k < b.anc.get(i).length; ++k) {
					if (anc.get(i)[j] == b.anc.get(i)[k]) {
						return true;
					}
				}
			}
		}
		
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Child c;
		LinkedList<Child> gen, nextGen;

		int nM = 1, nF = 1;
		boolean unFound = true;
		
		for (int maxH = 1; maxH < 20; ++maxH) {
			//System.err.println("maxH: " + maxH);
			nM = (int)Math.pow(2, maxH)-1;
			unFound = true;
			while (unFound) {
				gen = new LinkedList<Child>();
				++nM;
				for (nF = (int)Math.pow(2, maxH); nF <= nM; ++nF) {
					//System.err.println("Atmpt: " + nM + " + " + nF);
					// Attempting test with nM males and nF females
					
					// Create first generation
					for (int m = 0; m < nM; ++m) {
						for (int f = nM; f < nM + nF; ++f) {
							c = new Child(m,f,maxH);
							c.id = gen.size();
							gen.add(c);
						}
					}
					/*System.out.println();
					for (Child erc : gen) {
						System.out.println(erc);
					}*/
					// loop through generations
					while (gen.size() > 0 && gen.size() < (nM + nF) * 500) {
						//System.err.print(gen.size() + "-");
						nextGen = new LinkedList<Child>();
						
						for (int i = 0; i < gen.size()-1; ++i) {
							for (int j = i+1; j < gen.size(); ++j) {
								if (!gen.get(i).relatedTo(gen.get(j))) {
									c = gen.get(i).mateWith(gen.get(j));
									c.id = nextGen.size();
									nextGen.add(c);
								}
							}
						}
						
						gen = nextGen;
						/*for (Child erc : gen) {
							System.out.println(erc);
						}*/
						//System.err.print(gen.size() + " ");
					}
					//System.err.println();
					/*
					try {
						System.in.read();
					} catch (Exception e) {
						
					}
					*/
					// Loop ended, one of two conditions
					if (gen.size() >= (nM + nF) * 500) {
						unFound = false;
						System.out.println(maxH + ": " + nM + " males and " + nF + " females with " + gen.size());
						/*for (Child erc : gen) {
							System.out.println(erc);
						}*/
						break;
					}
					
					// otherwise the population died out, continue to increase
					// initial size until growth succeeded.					
				}
			}
		}
	}

}
