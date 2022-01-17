package bl.threadlocks;

import bl.exceptions.UnknownException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadLockCondition {

    private final Lock threadLock;
    private final Condition lifeCycleCalled;

    public ThreadLockCondition() {
        threadLock = new ReentrantLock();
        lifeCycleCalled = threadLock.newCondition();
    }

    public void awaitNewLifeCycle(LifeCycleCountDown remainingThreadsWaiting, Lock fatherThreadLock) {
        try {
            tryWaitingThreadLockFatherLock(remainingThreadsWaiting, fatherThreadLock);
        } catch (InterruptedException e) {
            throw new UnknownException("Unknown error occurred...");
        }
    }

    private void tryWaitingThreadLockFatherLock(LifeCycleCountDown remainingThreadsWaiting,
                                                   Lock fatherThreadLock) throws InterruptedException {
        lock();

        remainingThreadsWaiting.countDownWithThreadFatherLock(fatherThreadLock);
        lifeCycleCalled.await();

        unlock();
    }

    public void notifyThread() {
        lock();
        lifeCycleCalled.signal();
        unlock();
    }

    public void lock() {
        threadLock.lock();
    }

    public void unlock() {
        threadLock.unlock();
    }
}
