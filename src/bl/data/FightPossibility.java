package bl.data;

import bl.exceptions.BattleWasNotCreatedException;

import java.util.ArrayList;

public class FightPossibility {

    private static final float SIGNIFICANT_DIFFERENCE = 5;

    private enum WhoIsStronger {
        EQUAL,
        CURRENT_THREAD,
        CANDIDATE_THREAD
    }

    private final ArrayList<OrganismClanThreadInBattle>organismsForFight;
    private final int firstThreadIndex;
    private final int secondThreadIndex;

    public FightPossibility(ArrayList<OrganismClanThreadInBattle> organismsForFight, int firstThreadIndex,
                            int secondThreadIndex) {
        this.organismsForFight = organismsForFight;
        this.firstThreadIndex = firstThreadIndex;
        this.secondThreadIndex = secondThreadIndex;
    }

    public void tryCreatingFight() {
        WhoIsStronger strongerThread = compareBetweenOrganismsPower();

        switch (strongerThread) {
            case CURRENT_THREAD:
                firstThreadAttackSecondThread();
                break;
            case CANDIDATE_THREAD:
                secondThreadAttackFirstThread();
                break;
            default:
                throw new BattleWasNotCreatedException("Couldn't create battle! Clan threads have equal power!");
        }
    }

    private void firstThreadAttackSecondThread() {
        leftThreadAttackRightThread(firstThreadIndex, secondThreadIndex);
    }

    private void secondThreadAttackFirstThread() {
        leftThreadAttackRightThread(secondThreadIndex, firstThreadIndex);
    }

    private void leftThreadAttackRightThread(int attackingThreadIndex, int defendingThreadIndex) {
        OrganismClanThreadInBattle attackingThread = organismsForFight.get(attackingThreadIndex);
        OrganismClanThreadInBattle defendingThread = organismsForFight.get(defendingThreadIndex);

        startBattle(attackingThread, defendingThread);
    }

    private void startBattle(OrganismClanThreadInBattle attackingThread, OrganismClanThreadInBattle defendingThread) {
        attackingThread.attack(defendingThread);
        defendingThread.defend(attackingThread);
    }

    private WhoIsStronger compareBetweenOrganismsPower() {
        OrganismClanThreadInBattle firstThread = organismsForFight.get(firstThreadIndex);
        OrganismClanThreadInBattle secondThread = organismsForFight.get(secondThreadIndex);

        float currentClanPower = firstThread.getOrganismThreadPower();
        float candidateClanPower = secondThread.getOrganismThreadPower();
        float powerBalanceBetweenThreads = currentClanPower - candidateClanPower;

        return compareOrganismsByPowerBalance(powerBalanceBetweenThreads);
    }

    private WhoIsStronger compareOrganismsByPowerBalance(float powerBalanceBetweenThreads) {
        if(powerBalanceBetweenThreads > SIGNIFICANT_DIFFERENCE)
            return WhoIsStronger.CURRENT_THREAD;

        if(powerBalanceBetweenThreads < -SIGNIFICANT_DIFFERENCE)
            return WhoIsStronger.CANDIDATE_THREAD;

        return WhoIsStronger.EQUAL;
    }
}
