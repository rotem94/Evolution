package bl.organisms;

public class HomofloresiensisOrganism extends Organism {

    public HomofloresiensisOrganism(float strength, float intelligence, float technologicalMeans) {
        super(strength, intelligence, technologicalMeans);
    }

    public void updateOrganismCharacteristicByConstant(int constant) {
        updateTechnologicalMeansAndIntelligenceByHalfConstant((float)constant / 2);
    }

    public String getType() {
        return "Homofloresiensis";
    }
}
