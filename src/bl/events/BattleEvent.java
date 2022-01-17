package bl.events;

import bl.threadlocks.LifeCycleCountDown;
import bl.threads.SonThread;
import java.util.ArrayList;

public class BattleEvent extends ThreadEvent{

    public BattleEvent(ArrayList<? extends SonThread> threads) {
        super(threads);
    }

    public void runThreadsBattles() {
        LifeCycleCountDown remainingThreadsBattling = new LifeCycleCountDown(numberOfThreads());

        for (SonThread thread: getThreads())
            thread.notifyNewLifeCycle(remainingThreadsBattling);

        waitForAllThreadsToFinishBattles(remainingThreadsBattling);
    }

    private void waitForAllThreadsToFinishBattles(LifeCycleCountDown remainingThreads) {
        remainingThreads.await();
    }
}
