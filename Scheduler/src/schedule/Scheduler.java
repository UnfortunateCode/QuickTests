package schedule;

import java.util.LinkedList;
import java.util.TreeSet;

public class Scheduler extends Thread {
	Schedule sched;
	TreeSet<Schedule> schedules;
	LinkedList<Employee> people;

	boolean running;
	boolean finished;

	int day;
	int []cp = new int[7];
	int limit;
	boolean backday;

	public Scheduler() {
		sched = new Schedule();
		schedules = new TreeSet<Schedule>();
		people = new LinkedList<Employee>();

		running = false;
		finished = false;

		limit = 0;
	}

	public void run() {
		synchronized(this) {
			if (!running) {
				schedules = new TreeSet<Schedule>();
				sched = new Schedule();

				day = 0;
				for (int i = 0; i < cp.length; ++i) {
					cp[i] = 0;
				}
				backday = false;
			}
		}

		continueTask();
	}

	public void pauseTask() {
		synchronized(this) {
			running = false;
		}
	}

	public void resumeTask() {
		synchronized(this) {
			running = true;
		}

		continueTask();
	}

	public void restartTask() {
		synchronized(this) {
			running = false;
		}

		run();
	}



	private void continueTask() {

		while (day >= 0) {
			if (day >= 7) {
				if (sched.getScore() >= limit) {
					schedules.add(new Schedule(sched));
				}
				--day;
			} else {
				while (!sched.isCovered(day) || backday) {
					if (!running) {
						// At this point, day has been set, the
						// day isn't covered, and the data will
						// definitely get to this position if
						// paused and resumed.
						// Missing a pause is no problem, it will
						// just loop once more (fast).
						return;
					}
					backday = false;
					if (cp[day] < 0) {
						cp[day] = 0;
						--day;
						backday = true;
						break;
					} else if (cp[day] >= people.size()) {
						--cp[day];
					} else {
						if (people.get(cp[day]).hasNext(day)) {
							sched.modify(day, cp[day], people.get(cp[day]).next(day));
							++cp[day];
						} else {
							people.get(cp[day]).resetIter(day);
							--cp[day];
						}
					}
				}
				++day;
				cp[day] = 0;
			}
		}

		// day < 0
		// all applicable schedules stored in schedules
		finished = true;
	}

	public int addEmployee(Employee e) {
		people.add(e);
		return people.indexOf(e);
	}

	public Employee getEmployee(int i) {
		return people.get(i);
	}
}

