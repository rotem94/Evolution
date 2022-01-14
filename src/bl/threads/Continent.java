package bl.threads;

public class Continent extends SonThread{

    private final SonThreadGroup clanThreads;
    private final String name;


    public Continent(String name) {
        super();

        clanThreads = new SonThreadGroup();
        this.name = name;
    }

    public void run() {
        clanThreads.startThreads();
        super.run();
    }

    public void callNewLifeCycle() {
        clanThreads.callThreadsLifeCycle();
        super.callNewLifeCycle();
    }

    public void addClanThread(OrganismClanThread clanThread) {
        clanThreads.addThread(clanThread);
    }

    @Override
    public String toString() {
        return null;
    }

}
