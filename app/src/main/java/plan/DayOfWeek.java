package plan;

public class DayOfWeek {

    int dayNumber;
    int numberOfIntervals;
    long timeToRun;
    long timeToWalk;

    DayOfWeek(int dayNumber, int numberOfIntervals, long timeToRun, long timeToWalk) {
        this.dayNumber = dayNumber;
        this.numberOfIntervals = numberOfIntervals;
        this.timeToRun = timeToRun;
        this.timeToWalk = timeToWalk;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int number){dayNumber = number;}

    public int getNumberOfIntervals() {
        return numberOfIntervals;
    }

    public long getTimeToRun() {
        return timeToRun;
    }

    public long getTimeToWalk() {
        return timeToWalk;
    }
}
