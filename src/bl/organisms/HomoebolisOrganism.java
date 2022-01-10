package bl.organisms;

import bl.abstracts.Organism;

public class HomoebolisOrganism extends Organism {

    public HomoebolisOrganism(float strength, float intelligence, float technologicalMeans) {
        super(strength, intelligence, technologicalMeans);
    }

    public void updateOrganismByConstant(int constant) {
        updateStrengthAndIntelligenceByHalfConstant((float)constant / 2);
    }

    public String getType() {
        return "Homoebolis";
    }
}
