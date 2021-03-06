package listeners;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import plan.WeeksPlan;

public class ProgressSaver {

    private static ProgressSaver instance;

    private String path;
    private File saveFile;
    private int week;
    private int currentDayOfWeek;
    private Optional<WeeksPlan> weeksPlan;

//    String recordPath = this.getExternalFilesDir("/").getAbsolutePath();

    public ProgressSaver(String path) {
        this.path = path;
        this.saveFile = new File(path + "/saveFile.txt");
        if (saveFile.exists()) {
            try {
                FileReader fr = new FileReader(saveFile);
                BufferedReader br = new BufferedReader(fr);
                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.contains("week")) {
                        week = Integer.parseInt(line.substring(5));
                    }
                    if (line.contains("day")) {
                        currentDayOfWeek = Integer.parseInt(line.substring(4));
                    }
                }
                fr.close();
                weeksPlan = Arrays.stream(WeeksPlan.values())
                        .filter(e -> e.getWeekNumber() == week)
                        .findFirst();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //weeksPlan = Optional.of(WeeksPlan.WEEK_1);
            week = 1;
            currentDayOfWeek = WeeksPlan.WEEK_1.getDay1().getDayNumber();
        }
        if (week == 0) {
            week = 1;
        }
        weeksPlan = Arrays.stream(WeeksPlan.values())
                .filter(e -> e.getWeekNumber() == week).findFirst();
    }

    private void saveProgressInSaveFile(int week, int day) throws IOException {
        saveFile.delete();
        currentDayOfWeek = day;
        this.week = week;
        this.saveFile = new File(path + "/saveFile.txt");
        FileWriter writer = new FileWriter(saveFile);

        writer.write("week " + this.week);
        writer.write(String.format("%n"));
        writer.write("day " + currentDayOfWeek);
        writer.flush();
        writer.close();
    }

    public Optional<WeeksPlan> getWeekPlan() {
        return weeksPlan;
    }

    public int getCurrentDayOfWeek() {
        return currentDayOfWeek;
        //return instance.currentDayOfWeek;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public void setCurrentDayOfWeek(int currentDayOfWeek) {
        this.currentDayOfWeek = currentDayOfWeek;
    }

    public static void initInstance(String path) {
        if (instance == null) {
            // Create the instance
            instance = new ProgressSaver(path);
        }
    }

    public static ProgressSaver getInstance() {
        // Return the instance
        return instance;
    }

    public void saveForThisWeek(int dayNumber) {
        try {
            saveProgressInSaveFile(week, dayNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveNextWeek() {
        try {
            week += 1;
            saveProgressInSaveFile(week, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
