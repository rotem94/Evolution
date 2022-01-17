package bl.threads;

import bl.organisms.OrganismClan;

public class OrganismClanThread extends SonThread{

    private final OrganismClan clanInfo;

    public OrganismClanThread(OrganismClan clanInfo) {
        super();

        this.clanInfo = clanInfo;
    }

    public void callNewLifeCycle() {
        clanInfo.callNewLifeCycle();
        finishedStep();
    }

    public void run() {
        while(isThreadAlive()) {
            waitForNewLifeCycle();
            waitForBattlesToEnd();
            callNewLifeCycle();
        }
    }

    public String toString() {
        return clanInfo.toString();
    }

    public float getOrganismThreadPower() {
        return clanInfo.getPower();
    }

    public int getDefensePoints() {
        return clanInfo.getDefensePoints();
    }

    public int getAttackPoints() {
        return clanInfo.getAttackPoints();
    }

    public void battleCasualties(int casualties) {
        clanInfo.decreaseBalance(casualties);
    }

    public boolean checkIfOrganismEliminated() {
        return clanInfo.checkIfOrganismEliminated();
    }
}
