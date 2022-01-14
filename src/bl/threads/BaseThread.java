package bl.threads;

public class BaseThread extends Thread{

    private boolean threadAlive;

    public BaseThread() {
        threadAlive = true;
    }

    public void stopThread() {
        threadAlive = false;
    }

    public boolean isThreadAlive() {
        return threadAlive;
    }
}
