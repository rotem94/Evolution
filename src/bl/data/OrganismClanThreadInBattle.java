package bl.data;

import bl.exceptions.BattleWasNotCreatedException;
import bl.threads.OrganismClanThread;

public class OrganismClanThreadInBattle {

    private final OrganismClanThread thread;
    private boolean inBattle;

    public OrganismClanThreadInBattle(OrganismClanThread thread) {
        this.thread = thread;

        inBattle = false;
    }

    public float getOrganismThreadPower() {
        return thread.getOrganismThreadPower();
    }

    public void attack(OrganismClanThreadInBattle defendingThread) {
        setThreadInBattle();

        int attackerCasualties = defendingThread.getDefensePoints();
        thread.battleCasualties(attackerCasualties);
    }

    public void defend(OrganismClanThreadInBattle attackingThread) {
        setThreadInBattle();

        int defenderCasualties = attackingThread.getAttackPoints();
        thread.battleCasualties(defenderCasualties);
    }

    private void setThreadInBattle() {
        inBattle = true;
    }

    public int getDefensePoints() {
        return thread.getDefensePoints();
    }

    public int getAttackPoints() {
        return thread.getAttackPoints();
    }

    public boolean checkIfOrganismEliminated() {
        return thread.checkIfOrganismEliminated();
    }

    public void stopThread() {
        thread.stopThread();
    }

    public void throwThreadInBattleExceptionIfInBattle() {
        if(inBattle)
            throw new BattleWasNotCreatedException("Couldn't create battle! Thread is already in one.");
    }
}
