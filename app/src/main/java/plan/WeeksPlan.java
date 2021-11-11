package plan;

public enum WeeksPlan {
    WEEK_1(1,
            new DayOfWeek(1, 5, 60000, 300000),
            new DayOfWeek(2, 5, 90000, 270000),
            new DayOfWeek(3, 5, 120000, 240000)),
    WEEK_2(2,
            new DayOfWeek(1, 5, 120000, 240000),
            new DayOfWeek(2, 5, 150000, 210000),
            new DayOfWeek(3, 5, 180000, 180000)),
    WEEK_3(3,
            new DayOfWeek(1, 5, 1, 5),
            new DayOfWeek(2, 5, 1, 5),
            new DayOfWeek(3, 5, 1, 5)),
    WEEK_4(4,
            new DayOfWeek(1, 5, 1, 5),
            new DayOfWeek(2, 5, 1, 5),
            new DayOfWeek(3, 5, 1, 5)),
    WEEK_5(5,
            new DayOfWeek(1, 5, 1, 5),
            new DayOfWeek(2, 5, 1, 5),
            new DayOfWeek(3, 5, 1, 5)),
    WEEK_6(6,
            new DayOfWeek(1, 5, 1, 5),
            new DayOfWeek(2, 5, 1, 5),
            new DayOfWeek(3, 5, 1, 5)),
    WEEK_7(7,
            new DayOfWeek(1, 5, 1, 5),
            new DayOfWeek(2, 5, 1, 5),
            new DayOfWeek(3, 5, 1, 5)),
    WEEK_8(8,
            new DayOfWeek(1, 5, 1, 5),
            new DayOfWeek(2, 5, 1, 5),
            new DayOfWeek(3, 5, 1, 5)),
    WEEK_9(9,
            new DayOfWeek(1, 5, 1, 5),
            new DayOfWeek(2, 5, 1, 5),
            new DayOfWeek(3, 5, 1, 5)),
    WEEK_10(10,
            new DayOfWeek(1, 5, 1, 5),
            new DayOfWeek(2, 5, 1, 5),
            new DayOfWeek(3, 5, 1, 5)),
    WEEK_11(11,
            new DayOfWeek(1, 5, 1, 5),
            new DayOfWeek(2, 5, 1, 5),
            new DayOfWeek(3, 5, 1, 5)),
    WEEK_12(12,
            new DayOfWeek(1, 5, 1, 5),
            new DayOfWeek(2, 5, 1, 5),
            new DayOfWeek(3, 5, 1, 5));

    int weekNumber;
    DayOfWeek day1;
    DayOfWeek day2;
    DayOfWeek day3;

    WeeksPlan(int weekNumber, DayOfWeek day1, DayOfWeek day2, DayOfWeek day3) {
        this.weekNumber = weekNumber;
        this.day1 = day1;
        this.day2 = day2;
        this.day3 = day3;
    }

    public DayOfWeek getDay(int number) {
        DayOfWeek dayToReturn;
        switch (number) {
            case 1:
                dayToReturn = getDay1();
                break;
            case 2:
                dayToReturn = getDay2();
                break;
            case 3:
                dayToReturn = getDay3();
                break;
            default:
                dayToReturn = WeeksPlan.WEEK_1.getDay1();
        }
        return dayToReturn;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public DayOfWeek getDay1() {
        return day1;
    }

    public DayOfWeek getDay2() {
        return day2;
    }

    public DayOfWeek getDay3() {
        return day3;
    }

}

