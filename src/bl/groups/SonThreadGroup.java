package bl.groups;

import bl.exceptions.CastingException;
import bl.threads.OrganismClanThread;
import bl.threads.SonThread;
import bl.events.BattleEvent;
import bl.events.LifeCycleEndingEvent;
import bl.events.ThreadsStartEvent;
import java.util.ArrayList;

public abstract class SonThreadGroup {

    private final ArrayList<SonThread> sonThreads;

    public SonThreadGroup() {
        sonThreads = new ArrayList<>();
    }

    public void startThreads() {
        ThreadsStartEvent threadsStartEvent = new ThreadsStartEvent(sonThreads);
        threadsStartEvent.startThreads();
    }

    public void callThreadsLifeCycle() {
        runBattles();
        continueNewLifeCycle();
    }

    private void runBattles() {
        BattleEvent battleEvent = new BattleEvent(sonThreads);
        battleEvent.runThreadsBattles();
    }

    private void continueNewLifeCycle() {
        LifeCycleEndingEvent lifeCycleEndingEvent = new LifeCycleEndingEvent(sonThreads);
        lifeCycleEndingEvent.endThreadsLifeCycles();
    }

    public void addThread(SonThread clanThread) {
        sonThreads.add(clanThread);
    }

    public int size() {
        return sonThreads.size();
    }

    public String getThreadInfo(int i) {
        return sonThreads.get(i).toString();
    }

    public ArrayList<OrganismClanThread> copyThreadsAsOrganisms() {
        try {
            return tryCopyingThreatsAsOrganisms();
        }
        catch(Exception exception) {
            throw new CastingException("Some threads aren't of type 'OrganismClanThread'");
        }
    }

    private ArrayList<OrganismClanThread> tryCopyingThreatsAsOrganisms() {
        ArrayList<OrganismClanThread>copiedList = new ArrayList<>();

        for (SonThread sonThread: sonThreads)
            copiedList.add((OrganismClanThread) sonThread);

        return copiedList;
    }

    public void removeThreadFromGroup(int threatIndex) {
        sonThreads.remove(threatIndex);
    }
}
