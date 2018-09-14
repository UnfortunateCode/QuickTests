package schedule;

// Holds a single period

class Period {
  int start;
  int length;

  Period() {
    start = 0;
    length = 0;
  }

  Period(int s) {
    start = s;
    length = 0;
  }
 
  Period(int s, int l) {
    start = s;
    length = 0;
  }

  public void setStart(int s) {
    start = s;
  }

  public void setLength(int l) {
    length = l;
  }

  public void resetToStart(int s) {
    start = s;
    length = 0;
  }
}