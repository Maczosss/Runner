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

    public void setDayNumber(int number) {
        dayNumber = number;
    }

    public int getNumberOfIntervals() {
        return numberOfIntervals;
    }

    public long getTimeToRun() {
        return timeToRun;
    }

    public float getTimeToRunInMinutes() {
        return timeToRun / 1000f;
    }

    public long getTimeToWalk() {
        return timeToWalk;
    }

    public float getTimeToWalkInMinutes() {
        return timeToWalk / 1000f;
    }


    public String getSpecification() {
        return "You would have to make " + numberOfIntervals + " intervals,\nrunning for " + getMinutes(timeToRun)
                + " and walking for " + getMinutes(timeToWalk);
    }

    public float getMinutes(long millis) {
        return (millis / 1000f) / 60f;
    }
}
