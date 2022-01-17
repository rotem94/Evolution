package bl.threadlocks;

import bl.exceptions.UnknownException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

public class LifeCycleCountDown extends CountDownLatch {

    public LifeCycleCountDown(int count) {
        super(count);
    }

    public void await() {
        try {
            tryWaiting();
        } catch (InterruptedException e) {
            throw new UnknownException("Unknown error occurred...");
        }
    }

    private void tryWaiting() throws InterruptedException {
        super.await();
    }

    public void countDownWithThreadAndFatherLocks(ThreadLockCondition threadLock,
                                                     Lock fatherThreadLock) {
        threadAndFatherLocks(threadLock, fatherThreadLock);
        super.countDown();
        threadAndFatherUnlocks(fatherThreadLock, threadLock);
    }

    private void threadAndFatherLocks(ThreadLockCondition threadLock, Lock fatherThreadLock) {
        threadLock.lock();
        fatherThreadLock.lock();
    }

    private void threadAndFatherUnlocks(Lock fatherThreadLock, ThreadLockCondition threadLock) {
        fatherThreadLock.unlock();
        threadLock.unlock();
    }

    public void countDownWithThreadFatherLock(Lock fatherThreadLock) {
        fatherThreadLock.lock();
        super.countDown();
        fatherThreadLock.unlock();
    }
}
