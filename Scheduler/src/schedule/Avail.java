package schedule;

import java.util.Iterator;
import java.util.LinkedList;

// this class holds the day's availability
class Avail implements Iterator<Period> {
	LinkedList<AvailNode> avails;
	int minLength;
	int maxLength;
	int avNum;

	private class AvailNode implements Iterator<Period> {
		int firstStart;
		int lastStart;
		int lastFinish;

		int selectedStart;
		int selectedLength;

		public AvailNode(int fs, int ls, int lf) {
			firstStart = fs;
			lastStart = ls;
			lastFinish=lf;

			selectedStart = firstStart;
			selectedLength = 0;
		}

		public boolean hasNext() {
			return (selectedStart != lastStart || selectedStart + selectedLength - 1 < lastFinish);
		}

		public Period next() {
			++selectedLength;
			if (selectedStart + selectedLength - 1 > lastFinish || (maxLength > 0 && selectedLength > maxLength)) {
				++selectedStart;
				selectedLength = minLength;
				if (selectedStart > lastStart || selectedStart + selectedLength - 1 > lastFinish) {
					return null;
				}
			}
			return new Period(selectedStart, selectedLength);
		}

		public void remove() {}

		void resetIter() {
			selectedStart = firstStart;
			selectedLength = 0;
		}
	}

	Avail() {
		minLength = 0;
		maxLength = -1;  
		avNum = 0;

		avails = new LinkedList<AvailNode>();
	}

	Avail(int minL, int maxL) {
		minLength = minL;
		maxLength = maxL;
		avNum = 0;

		avails = new LinkedList<AvailNode>();
	}

	boolean add(int fs, int ls, int lf) {
		return avails.add(new AvailNode(fs, ls, lf));
	}



	public boolean hasNext() {
		while (avNum < avails.size()) {
			if (avails.get(avNum).hasNext()) {
				return true;
			}
			avails.get(avNum).resetIter();
			++avNum;
		}
		return false;
	}

	public Period next() {
		return avails.get(avNum).next();
	}

	public void remove() {};

	void resetIter() {
		for (AvailNode av : avails) {
			av.resetIter();
		}
		avNum = 0;
	}
}