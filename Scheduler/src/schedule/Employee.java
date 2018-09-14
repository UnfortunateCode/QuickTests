package schedule;

// This class holds the Employee information
// including current schedules and availabilities
class Employee {
	Avail[] weekAvails;
	int minLength;
	int maxLength;

	Employee(int minL, int maxL) {
		minLength = minL;
		maxLength = maxL;

		weekAvails = new Avail[7];
		for (int i = 0; i < 7; ++i) {
			weekAvails[i] = new Avail(minL, maxL);
		}
	}

	Employee() {
		minLength = 0;
		maxLength = -1;

		weekAvails = new Avail[7];
		for (int i = 0; i < 7; ++i) {
			weekAvails[i] = new Avail();
		}
	}
	
	public boolean hasNext(int day) {
		return weekAvails[day].hasNext();
	}
	
	public Period next(int day) {
		return weekAvails[day].next();
	}
	
	public void resetIter(int day) {
		weekAvails[day].resetIter();
	}
}