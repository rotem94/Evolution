package bl.threads;

import bl.threadlocks.LifeCycleCountDown;
import ui.EvolutionLogger;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SonThreadGroup {

    private final ArrayList<SonThread> sonThreads;

    private EvolutionLogger logger;


    public SonThreadGroup() {
        sonThreads = new ArrayList<>();
    }

    public void addLogger(EvolutionLogger logger) {
        this.logger = logger;
    }

    public void startThreads() {
        addSonThreadsLogger();

        LifeCycleCountDown remainingSonThreadsNewLifeCycles = new LifeCycleCountDown(sonThreads.size());
        addFatherLockAndGivenInfoToSonThreads(remainingSonThreadsNewLifeCycles);

        runThreads();
        remainingSonThreadsNewLifeCycles.await();
    }

    private void addSonThreadsLogger() {
        for (SonThread sonThread: sonThreads)
            sonThread.addLogger(logger);
    }

    private void addFatherLockAndGivenInfoToSonThreads(LifeCycleCountDown remainingSonThreadsNewLifeCycles) {
        Lock fatherThreadLock = new ReentrantLock();

        for (SonThread sonThread: sonThreads)
            sonThread.addGivenInfo(remainingSonThreadsNewLifeCycles, fatherThreadLock);
    }

    private void runThreads() {
        for (SonThread sonThread: sonThreads)
            sonThread.start();
    }

    public void callThreadsLifeCycle() {
        LifeCycleCountDown remainingSonThreadsNewLifeCycles = new LifeCycleCountDown(sonThreads.size());

        for (SonThread clanThread: sonThreads)
            clanThread.notifyNewLifeCycle(remainingSonThreadsNewLifeCycles);

        waitForAllSonThreadsLifeCycle(remainingSonThreadsNewLifeCycles);
    }

    private void waitForAllSonThreadsLifeCycle(LifeCycleCountDown remainingThreadsNewLifeCycles) {
        remainingThreadsNewLifeCycles.await();
    }

    public void addThread(SonThread clanThread) {
        sonThreads.add(clanThread);
    }

    public String getInfoWithFatherThreatName(String fatherName) {
        StringBuilder info = new StringBuilder();

        for (SonThread sonThread: sonThreads) {
            String clanThreadInfo =  fatherName + " -> " + sonThread.toString() + "\n";
            info.append(clanThreadInfo);
        }

        return info.toString();
    }
}
