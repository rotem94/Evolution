package bl.events;

import bl.threadlocks.LifeCycleCountDown;
import bl.threads.SonThread;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadsStartEvent extends ThreadEvent {

    public ThreadsStartEvent(ArrayList<? extends SonThread> threads) {
        super(threads);
    }

    public void startThreads() {
        LifeCycleCountDown remainingThreadsNewLifeCycles = new LifeCycleCountDown(numberOfThreads());
        addFatherLockAndGivenInfoToThreads(remainingThreadsNewLifeCycles);

        runThreads();
        waitForAllThreadsToStart(remainingThreadsNewLifeCycles);
    }

    private void addFatherLockAndGivenInfoToThreads(LifeCycleCountDown remainingThreadsNewLifeCycles) {
        Lock fatherThreadLock = new ReentrantLock();

        for (SonThread thread: getThreads())
            thread.addGivenInfo(remainingThreadsNewLifeCycles, fatherThreadLock);
    }

    private void runThreads() {
        for (SonThread sonThread: getThreads())
            sonThread.start();
    }

    private void waitForAllThreadsToStart(LifeCycleCountDown remainingThreadsNewLifeCycles) {
        remainingThreadsNewLifeCycles.await();
    }
}
