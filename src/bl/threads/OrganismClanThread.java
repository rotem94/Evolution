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
        super.callNewLifeCycle();
    }

    public String toString() {
        return clanInfo.toString();
    }
}
