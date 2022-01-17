package bl.threads;

import bl.groups.OrganismThreadGroup;
import ui.EvolutionLogger;

public class Continent extends SonThread{

    private final OrganismThreadGroup clanThreads;
    private final String name;

    private EvolutionLogger logger;


    public Continent(String name) {
        super();

        clanThreads = new OrganismThreadGroup();
        this.name = name;
    }

    public void run() {
        clanThreads.startThreads();
        runContinent();

        super.run();
    }

    public void runContinent() {
        while(isThreadAlive()) {
            runLifeCycleIteration();
            logContinent();
        }
    }

    private void runLifeCycleIteration() {
        waitForNewLifeCycle();
        callBattles();
        waitForBattlesToEnd();
        callNewLifeCycle();
    }

    private void logContinent() {
        logger.log(this.toString());
    }

    public void callBattles() {
        clanThreads.callBattles();
        finishedStep();
    }

    public void callNewLifeCycle() {
        clanThreads.callThreadsLifeCycle();
        finishedStep();
    }

    public void addClanThread(OrganismClanThread clanThread) {
        clanThreads.addThread(clanThread);
    }

    public void addLogger(EvolutionLogger logger) {
        this.logger = logger;
    }

    public String toString() {
        StringBuilder continentInfo = new StringBuilder();

        for (int i = 0; i < clanThreads.size(); i++) {
            String clanFullInfo = getContinentClanThreadInfo(clanThreads.getThreadInfo(i));
            continentInfo.append(clanFullInfo);
        }

        return continentInfo.toString();
    }

    private String getContinentClanThreadInfo(String clanInfo) {
        return name + " -> " + clanInfo + "\n";
    }

}
