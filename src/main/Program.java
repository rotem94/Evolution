package main;

import bl.organisms.Organism;
import bl.organisms.OrganismClan;
import bl.threads.OrganismClanThread;
import bl.threads.Continent;
import bl.threads.Planet;
import bl.organisms.*;

public class Program {

    private static final float STARTING_STRENGTH = 1f;
    private static final float STARTING_INTELLIGENCE = 1f;
    private static final float STARTING_TECHNOLOGICAL_MEANS = 1f;

    private static final int STARTING_MULTIPLICATION = 3;
    private static final int STARTING_BALANCE = 1;

    private static final float STARTING_MUTATION = 1.5f;

    public static void main(String[]args) {
        createPlanet();
    }

    private static void createPlanet() {
        Planet planet = Planet.getInstance();

        addContinentsToPlanet(planet);
        startPlanet(planet);
    }

    private static void addContinentsToPlanet(Planet planet) {
        Continent europe = getEuropeContinent();
        Continent asia = getAsiaContinent();

        planet.addContinent(europe);
        planet.addContinent(asia);
    }

    private static void startPlanet(Planet planet) {
        planet.start();
    }

    private static Continent getEuropeContinent() {
        Continent europe = new Continent("Europe");

        OrganismClanThread homoebolisClanThread = getHomoebolisOrganismClanThread();
        OrganismClanThread homoerectusClanThread = getHomoerectusOrganismClanThread();

        europe.addClanThread(homoebolisClanThread);
        europe.addClanThread(homoerectusClanThread);

        return europe;
    }

    private static Continent getAsiaContinent() {
        Continent asia = new Continent("Asia");

        OrganismClanThread homofloresiensisClanThread = getHomofloresiensisOrganismClanThread();
        OrganismClanThread homosepianClanThread = getHomosepianOrganismClanThread();
        OrganismClanThread neanderthalClanThread = getNeanderthalOrganismClanThread();

        asia.addClanThread(homofloresiensisClanThread);
        asia.addClanThread(homosepianClanThread);
        asia.addClanThread(neanderthalClanThread);

        return asia;
    }

    private static OrganismClanThread getHomoebolisOrganismClanThread() {
        Organism homoeobloisOrganism = new HomoebolisOrganism(STARTING_STRENGTH, STARTING_INTELLIGENCE,
                STARTING_TECHNOLOGICAL_MEANS);
        String clanName = "Homoebolis";

        return getNewClanThreadForGivenOrganismAndClanName(homoeobloisOrganism, clanName);
    }

    private static OrganismClanThread getHomoerectusOrganismClanThread() {
        Organism homoerectusOrganism = new HomoerectusOrganism(STARTING_STRENGTH, STARTING_INTELLIGENCE,
                STARTING_TECHNOLOGICAL_MEANS);
        String clanName = "Homoerectus";

        return getNewClanThreadForGivenOrganismAndClanName(homoerectusOrganism, clanName);
    }

    private static OrganismClanThread getHomofloresiensisOrganismClanThread() {
        Organism homofloresiensisOrganism = new HomofloresiensisOrganism(STARTING_STRENGTH, STARTING_INTELLIGENCE,
                STARTING_TECHNOLOGICAL_MEANS);
        String clanName = "Homofloresiensis";

        return getNewClanThreadForGivenOrganismAndClanName(homofloresiensisOrganism, clanName);
    }

    private static OrganismClanThread getHomosepianOrganismClanThread() {
        Organism homosepianOrganism = new HomosepianOrganism(STARTING_STRENGTH, STARTING_INTELLIGENCE,
                STARTING_TECHNOLOGICAL_MEANS);
        String clanName = "Homosepian";

        return getNewClanThreadForGivenOrganismAndClanName(homosepianOrganism, clanName);
    }

    private static OrganismClanThread getNeanderthalOrganismClanThread() {
        Organism neanderthalOrganism = new NeanderthalOrganism(STARTING_STRENGTH, STARTING_INTELLIGENCE,
                STARTING_TECHNOLOGICAL_MEANS);
        String clanName = "Neanderthal";

        return getNewClanThreadForGivenOrganismAndClanName(neanderthalOrganism, clanName);
    }

    private static OrganismClanThread getNewClanThreadForGivenOrganismAndClanName(Organism organism, String clanName) {
        OrganismClan clan = new OrganismClan(STARTING_BALANCE, STARTING_MULTIPLICATION, STARTING_MUTATION,
                clanName, organism);

        return new OrganismClanThread(clan);
    }
}
