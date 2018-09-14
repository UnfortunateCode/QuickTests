package demult;

public class Fact {
	long a;
	long b;
	
	public Fact() {
		a = b = 1L;
	}
	public Fact(long l, long k) {
		a = l;
		b = k;
	}
	public void set(long l, long k) {
		a = l;
		b = k;
	}
	public long[] get() {
		long []l = {a, b};
		return l;
	}
	public String toString() {
		return "[" + a + ", " + b + "]";
	}

}
