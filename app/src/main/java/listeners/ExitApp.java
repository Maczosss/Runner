package listeners;

import android.app.Activity;
import android.widget.Toast;

public class ExitApp {

    private long timeFromLastBackClick;

    public ExitApp(Activity activity) {
        Toast.makeText(activity.getApplicationContext(),
                "Press again to leave",
                Toast.LENGTH_LONG).show();
        this.timeFromLastBackClick = System.currentTimeMillis();
    }

    public boolean checkIfExit(Activity activity) {
        long time = System.currentTimeMillis();
        if (time - timeFromLastBackClick < 2000) {
            return false;
        } else {
            timeFromLastBackClick = time;
            Toast.makeText(activity.getApplicationContext(),
                    "Press again to leave",
                    Toast.LENGTH_LONG).show();
            return true;
        }
    }
}
