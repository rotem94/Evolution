package bl.organisms;

public class HomosepianOrganism extends Organism {

    public HomosepianOrganism(float strength, float intelligence, float technologicalMeans) {
        super(strength, intelligence, technologicalMeans);
    }

    public void updateOrganismCharacteristicByConstant(int constant) {
        updateIntelligenceByConstant(constant);
    }

    public String getType() {
        return "Homosepian";
    }
}
