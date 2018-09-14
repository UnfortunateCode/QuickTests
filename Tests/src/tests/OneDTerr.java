package tests;

public class OneDTerr {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		double [][] qArr = {{0.0,0.0,0.2,0.1,0.1,0.1,0.1,0.2,0.4,0.0,0.0},
				{0.0,0.0,0.3,0.2,0.2,0.3,0.3,0.2,0.2,0.4,0.0},
				{0.0,0.0,0.5,0.4,0.3,0.3,0.2,0.2,0.1,0.2,0.2}};
		double [][] pArr = {{0.0,0.0,0.1,0.1,0.2,0.0,0.0,0.2,0.4,0.0,0.0},
				{0.0,0.0,0.3,0.3,0.2,0.5,0.0,0.2,0.2,0.4,0.0},
				{0.0,0.0,0.6,0.3,0.2,0.2,0.3,0.2,0.1,0.2,0.2}};

		int []points = {8,5,3,2,1};
		int i = 0;
		int i1,i2, min, max, per;
		int w = 70;
		int h = 15;
		int [][]map = new int[h][w];
		double r;
		int sum = 0;
		for (int a = 0; a < w+h-1; ++a) {
			for (int b = 0; b <= a; ++b) {
				if ((a-b) >= w) {
					continue;
				}
				if (b >= h) {
					break;
				}

				if (b == 0 && (a-b) == 0) {
					i = 0;
				} else if (b == 0) {
					i = map[b][a-b-1];
				} else if ((a-b) == 0) {
					i = map[b-1][a-b];
				} else {
					i1 = map[b][a-b-1];
					i2 = map[b-1][a-b];
					if (i1==i2) {
						i = i1;
					} else {
						min = Math.min(i1, i2);
						max = Math.max(i1, i2);
						per = (max - min)*points[max]+points[min];
						if (per * Math.random() < points[min]) {
							i = min;
						} else {
							i = max;
						}
					}
				}
				r = Math.random();

				if (r < pArr[0][i]) { 
					i -= 2; 
				} else if ((r -= pArr[0][i]) < pArr[1][i+1] ) {
					i -= 1;
				} else if ((r -= pArr[1][i+1]) < pArr[2][i+2]) {

				} else if ((r -= pArr[2][i+2]) < pArr[1][i+2]) {
					i += 1;
				} else {
					i += 2;
				}
//System.err.println("[" + b + "][" + (a-b) + "]: " + i);
				map[b][a-b] = i;
				sum += i;
			}
		}

		for (int y = 0; y < h; ++y) {
			for (int x = 0; x < w; ++x) {
				System.out.print(map[y][x]);
			}
			System.out.println();
		}

		System.out.println("Avg Height: " + (((double)sum / (double)(w*h))));
		sum = 0;
		for (int y = 1; y < h-1; ++y) {
			for (int x = 1; x < w-1; ++x) {
				if (    map[y-1][x] == 0 &&
						map[y][x-1] == 0 &&
						map[y+1][x] == 0 &&
						map[y][x+1] == 0) {
					System.out.println("Town Candidate: (" + x + ", " + y + ")");
					++sum;
				}
			}
		}
		System.out.println(sum + " total town candidate spots");

	}
}


