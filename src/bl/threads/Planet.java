package bl.threads;

import bl.accessories.ThreadSleep;
import ui.EvolutionLogger;

public class Planet extends BaseThread{

    private static Planet planet;

    private final SonThreadGroup continents;
    private final EvolutionLogger logger;


    public static Planet getInstance() {
        if(planet == null)
            planet = new Planet();

        return planet;
    }


    private Planet() {
        super();

        logger = new EvolutionLogger();
        continents = new SonThreadGroup();

        continents.addLogger(logger);
    }

    public void run() {
        logMessage("Planet started");
        startContinents();

        while(isThreadAlive())
            runPlanetLifeCycles();
    }

    private void logMessage(String message) {
        logger.log(message);
    }

    private void startContinents() {
        continents.startThreads();
    }

    private void runPlanetLifeCycles() {
        callNewLifeCycle();
        waitSecond();
    }

    private void callNewLifeCycle() {
        continents.callThreadsLifeCycle();
    }

    private void waitSecond() {
        ThreadSleep.sleep(1);
    }

    public void addContinent(Continent continent) {
        continents.addThread(continent);
    }
}
