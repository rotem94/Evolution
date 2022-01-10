package main;

import bl.abstracts.Organism;
import bl.organisms.HomoebolisOrganism;
import bl.organisms.OrganismClan;

public class Program {

    private static final float STARTING_STRENGTH = 1f;
    private static final float STARTING_INTELLIGENCE = 1f;
    private static final float STARTING_TECHNOLOGICAL_MEANS = 1f;

    private static final int STARTING_MULTIPLICATION = 3;
    private static final int STARTING_BALANCE = 1;

    private static final float STARTING_MUTATION = 1.5f;

    public static void main(String[]args) {
        createHomoebolisOrganismClan();
        createHomoerectusOrganismClan();
        createHomofloresiensisOrganismClan();
        createHomosepianOrganismClan();
        createNeanderthalOrganismClan();
    }

    private static void createHomoebolisOrganismClan() {
        Organism homoeobloisOrganism = new HomoebolisOrganism(STARTING_STRENGTH, STARTING_INTELLIGENCE,
                STARTING_TECHNOLOGICAL_MEANS);
        String clanName = "Clan1";
        createNewClanForGivenOrganism(homoeobloisOrganism, clanName);
    }

    private static void createHomoerectusOrganismClan() {
    }

    private static void createHomofloresiensisOrganismClan() {
    }

    private static void createHomosepianOrganismClan() {
    }

    private static void createNeanderthalOrganismClan() {
    }

    private static void createNewClanForGivenOrganism(Organism organism, String clanName) {
        OrganismClan clan = new OrganismClan(STARTING_BALANCE, STARTING_MULTIPLICATION, STARTING_MUTATION,
                clanName, organism);
        clan.start();
    }
}
