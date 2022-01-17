package bl.events;

import bl.threads.SonThread;

import java.util.ArrayList;

public abstract class ThreadEvent {

    private final ArrayList<? extends SonThread> threads;

    public ThreadEvent(ArrayList<? extends SonThread> threads) {
        this.threads = threads;
    }

    public ArrayList<? extends SonThread> getThreads() {
        return threads;
    }

    public int numberOfThreads() {
        return threads.size();
    }
}
