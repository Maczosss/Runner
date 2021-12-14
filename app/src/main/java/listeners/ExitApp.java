package listeners;

import android.app.Activity;
import android.widget.Toast;

public class ExitApp {

    private long timeFromLastBackClick = 0;
    private int numberOfClicks = 0;
    private final int TIMES_FOR_USER_TO_CLICK = 2;

    public ExitApp(Activity activity) {
        Toast.makeText(activity.getApplicationContext(),
                "Press again to leave",
                Toast.LENGTH_LONG).show();
        this.timeFromLastBackClick = System.currentTimeMillis();
        this.numberOfClicks++;
    }

    public boolean checkIfExit(Activity activity) {
        long time = System.currentTimeMillis();
        if(time-timeFromLastBackClick < 2000000000L){
            return false;
        }
        else{
            timeFromLastBackClick = time;
            Toast.makeText(activity.getApplicationContext(),
                    "Press again to leave",
                    Toast.LENGTH_LONG).show();
            return true;
        }
    }
}
