package bl.events;

import bl.threadlocks.LifeCycleCountDown;
import bl.threads.SonThread;

import java.util.ArrayList;

public class LifeCycleEndingEvent extends ThreadEvent{

    public LifeCycleEndingEvent(ArrayList<? extends SonThread> threads) {
        super(threads);
    }

    public void endThreadsLifeCycles() {
        LifeCycleCountDown remainingThreadsFinishingLifeCycle = new LifeCycleCountDown(numberOfThreads());

        for (SonThread thread: getThreads())
            thread.notifyContinueOfLifeCycle(remainingThreadsFinishingLifeCycle);

        waitForThreadsToEnd(remainingThreadsFinishingLifeCycle);
    }

    private void waitForThreadsToEnd(LifeCycleCountDown remainingThreadsFinishingLifeCycle) {
        remainingThreadsFinishingLifeCycle.await();
    }
}
