package bl.organisms;

import java.util.Random;

public abstract class Organism {

    private enum Characteristic {
        STRENGTH,
        INTELLIGENCE,
        TECHNOLOGICAL_MEANS
    }

    private float strength;
    private float intelligence;
    private float technologicalMeans;


    public Organism(float strength, float intelligence, float technologicalMeans) {
        this.strength = strength;
        this.intelligence = intelligence;
        this.technologicalMeans = technologicalMeans;
    }


    public abstract void updateOrganismCharacteristicByConstant(int constant);
    public abstract String getType();


    public float sumProperties() {
        return strength + intelligence + technologicalMeans;
    }

    public void updateCharacteristicsByConstantAndMutation(int constant, float mutation) {
        updateOrganismInFifthProbabilityByConstant(constant);
        multiplyRandomCharacteristicByMutation(mutation);
    }

    private void updateOrganismInFifthProbabilityByConstant(int constant) {
        if (toPerformUpdate())
            updateOrganismCharacteristicByConstant(constant);
    }

    private boolean toPerformUpdate() {
        Random randomNumber = new Random();
        final int numberFive = 5;
        int randomNumberFromOneToFive = randomNumber.nextInt(numberFive - 1) + 1;

        return randomNumberFromOneToFive == 1;
    }

    public void multiplyRandomCharacteristicByMutation(float mutation) {
        switch(getRandomCharacteristic()) {
            case STRENGTH:
                strength *= mutation;
                break;
            case INTELLIGENCE:
                intelligence *= mutation;
                break;
            default:
                technologicalMeans *= mutation;
        }
    }

    private Characteristic getRandomCharacteristic() {
        Random randomSelectionOfCharacteristic = new Random();
        int lastCharacteristicOption = Characteristic.values().length;
        int selectedCharacteristicIndex = randomSelectionOfCharacteristic.nextInt(lastCharacteristicOption);

        return Characteristic.values()[selectedCharacteristicIndex];
    }

    public void updateIntelligenceByConstant(int constant) {
        intelligence += constant;
    }

    public void updateStrengthByConstant(int constant) {
        strength += constant;
    }

    public void updateTechnologicalMeansByConstant(int constant) {
        technologicalMeans += constant;
    }

    public void updateTechnologicalMeansAndIntelligenceByHalfConstant(float halfConstant) {
        intelligence += halfConstant;
        technologicalMeans += halfConstant;
    }

    public void updateStrengthAndIntelligenceByHalfConstant(float halfConstant) {
        intelligence += halfConstant;
        strength += halfConstant;
    }

    public String toString() {
        return "strength=" + strength + ", intelligence=" + intelligence + ", " +
                "technologicalMeans=" + technologicalMeans;
    }
}
