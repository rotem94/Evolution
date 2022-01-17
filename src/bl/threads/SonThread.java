package bl.threads;

import bl.threadlocks.ThreadLockCondition;
import bl.threadlocks.LifeCycleCountDown;
import java.util.concurrent.locks.Lock;

public abstract class SonThread extends BaseThread{

    private final ThreadLockCondition threadLockCondition;

    private Lock fatherThreadLock;
    private LifeCycleCountDown remainingSiblingsThreadsWaiting;

    public abstract String toString();
    public abstract void callNewLifeCycle();

    public SonThread() {
        super();
        threadLockCondition = new ThreadLockCondition();
    }

    public void waitForNewLifeCycle() {
        waitForNotification();
    }

    public void waitForBattlesToEnd() {
        waitForNotification();
    }

    private void waitForNotification() {
        threadLockCondition.awaitNewLifeCycle(remainingSiblingsThreadsWaiting, fatherThreadLock);
    }

    public void finishedStep() {
        remainingSiblingsThreadsWaiting.countDownWithThreadAndFatherLocks(threadLockCondition, fatherThreadLock);
    }

    public void notifyNewLifeCycle(LifeCycleCountDown remainingSiblingsThreadsWaiting) {
        notifyThread(remainingSiblingsThreadsWaiting);
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

    public void notifyContinueOfLifeCycle(LifeCycleCountDown remainingThreadsFinishingLifeCycle) {
        notifyThread(remainingThreadsFinishingLifeCycle);
    }

    private void notifyThread(LifeCycleCountDown remainingThreadsDoingProcess) {
        setRemainingSiblingsThreadsWaiting(remainingThreadsDoingProcess);

        threadLockCondition.notifyThread();
    }
}
