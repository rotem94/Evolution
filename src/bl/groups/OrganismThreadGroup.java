package bl.groups;

import bl.data.FightPossibility;
import bl.data.OrganismClanThreadInBattle;
import bl.exceptions.BattleWasNotCreatedException;
import bl.threads.OrganismClanThread;
import java.util.ArrayList;

public class OrganismThreadGroup extends SonThreadGroup{

    public void callBattles() {
        ArrayList<OrganismClanThreadInBattle>organismsForBattle = getOrganismsForBattle();

        for (int i = 0; i < organismsForBattle.size(); i++)
            createBattlesForOrganismThread(i, organismsForBattle);
    }

    private ArrayList<OrganismClanThreadInBattle> getOrganismsForBattle() {
        ArrayList<OrganismClanThread>organismClanThreads = this.copyThreadsAsOrganisms();
        ArrayList<OrganismClanThreadInBattle>organismsForBattle = new ArrayList<>();

        for (OrganismClanThread organismClanThread: organismClanThreads) {
            OrganismClanThreadInBattle organismClanThreadInBattle = new OrganismClanThreadInBattle(organismClanThread);
            organismsForBattle.add(organismClanThreadInBattle);
        }

        return organismsForBattle;
    }

    private void createBattlesForOrganismThread(int currentThreadIndex,
                                                ArrayList<OrganismClanThreadInBattle> organismsThreadsForBattle) {
        try{
            tryCreatingBattlesForOrganismThread(currentThreadIndex, organismsThreadsForBattle);
        }
        catch(BattleWasNotCreatedException ex) {
            //Empty catch
        }
    }

    private void tryCreatingBattlesForOrganismThread(int currentThreadIndex,
                                                     ArrayList<OrganismClanThreadInBattle> organismsThreadsForBattle) {
        OrganismClanThreadInBattle currentThread = organismsThreadsForBattle.get(currentThreadIndex);
        currentThread.throwThreadInBattleExceptionIfInBattle();
        createBattleForThread(currentThreadIndex, organismsThreadsForBattle);
    }

    private void createBattleForThread(int currentThreadIndex,
                                       ArrayList<OrganismClanThreadInBattle> organismsThreadsForBattle) {
        boolean fightCreated = false;

        for (int candidateIndex = currentThreadIndex + 1;
             candidateIndex < organismsThreadsForBattle.size() && !fightCreated; candidateIndex++) {
            try{
                tryRunningBattle(organismsThreadsForBattle, currentThreadIndex, candidateIndex);
                fightCreated = true;
            }
            catch(BattleWasNotCreatedException ex) {
                // Empty catch
            }
        }
    }

    private void tryRunningBattle(ArrayList<OrganismClanThreadInBattle> organismsThreadsForBattle,
                                  int currentThreadIndex, int candidateIndex) {
        throwExceptionIfCandidateInBattle(organismsThreadsForBattle, candidateIndex);
        tryCreatingFight(organismsThreadsForBattle, currentThreadIndex, candidateIndex);
        removeEliminatedClans(organismsThreadsForBattle, currentThreadIndex, candidateIndex);
    }

    private void throwExceptionIfCandidateInBattle(ArrayList<OrganismClanThreadInBattle> organismsThreadsForBattle,
                                                   int candidateIndex) {
        OrganismClanThreadInBattle organismClanThreadInBattle = organismsThreadsForBattle.get(candidateIndex);
        organismClanThreadInBattle.throwThreadInBattleExceptionIfInBattle();
    }

    private void tryCreatingFight(ArrayList<OrganismClanThreadInBattle> organismsThreadsForBattle,
                                  int currentThreadIndex, int candidateIndex) {
        FightPossibility fightPossibility = new FightPossibility(organismsThreadsForBattle, currentThreadIndex,
                candidateIndex);
        fightPossibility.tryCreatingFight();
    }

    private void removeEliminatedClans(ArrayList<OrganismClanThreadInBattle> organismsThreadsForBattle,
                                       int currentThreadIndex, int candidateIndex) {
        removeClanIfEliminated(organismsThreadsForBattle, candidateIndex);
        removeClanIfEliminated(organismsThreadsForBattle, currentThreadIndex);
    }

    private void removeClanIfEliminated(ArrayList<OrganismClanThreadInBattle> organismsThreadsForBattle,
                                        int threatIndex) {
        OrganismClanThreadInBattle thread = organismsThreadsForBattle.get(threatIndex);

        if(thread.checkIfOrganismEliminated()) {
            thread.stopThread();
            removeClanFromContinent(threatIndex);
        }
    }

    private void removeClanFromContinent(int threatIndex) {
        removeThreadFromGroup(threatIndex);
    }
}
