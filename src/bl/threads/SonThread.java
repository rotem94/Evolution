package bl.threads;

import bl.threadlocks.ThreadLockCondition;
import bl.threadlocks.LifeCycleCountDown;
import ui.EvolutionLogger;
import java.util.concurrent.locks.Lock;

public abstract class SonThread extends BaseThread{

    private final ThreadLockCondition threadLockCondition;

    private Lock fatherThreadLock;
    private LifeCycleCountDown remainingSiblingsThreadsWaiting;
    private EvolutionLogger logger;

    public abstract String toString();

    public SonThread() {
        super();
        threadLockCondition = new ThreadLockCondition();
    }

    public void run() {
        while(isThreadAlive()) {
            waitForNewLifeCycle();
            callNewLifeCycle();
            logThread();
        }
    }

    public void waitForNewLifeCycle() {
        threadLockCondition.awaitNewLifeCycle(remainingSiblingsThreadsWaiting, fatherThreadLock);
    }

    public void callNewLifeCycle() {
        remainingSiblingsThreadsWaiting.countDownWithThreadAndFatherLocks(threadLockCondition, fatherThreadLock);
    }

    private void logThread() {
        logger.log(this.toString());
    }

    public void notifyNewLifeCycle(LifeCycleCountDown remainingSiblingsThreadsWaiting) {
        setRemainingSiblingsThreadsWaiting(remainingSiblingsThreadsWaiting);

        threadLockCondition.notifyNewLifeCycle();
    }

    private void setRemainingSiblingsThreadsWaiting(LifeCycleCountDown remainingSiblingsThreadsWaiting) {
        threadLockCondition.lock();
        this.remainingSiblingsThreadsWaiting = remainingSiblingsThreadsWaiting;
        threadLockCondition.unlock();
    }

    public void addGivenInfo(LifeCycleCountDown remainingSiblingsThreadsWaiting, Lock fatherThreadLock) {
        this.fatherThreadLock = fatherThreadLock;
        this.remainingSiblingsThreadsWaiting = remainingSiblingsThreadsWaiting;
    }

    public void addLogger(EvolutionLogger logger) {
        this.logger = logger;
    }
}
