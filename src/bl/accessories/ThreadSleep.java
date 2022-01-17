package bl.accessories;

import bl.exceptions.UnknownException;

import java.util.concurrent.TimeUnit;

public class ThreadSleep {

    public static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new UnknownException("Unknown exception occurred..");
        }
    }
}
