package bl.organisms;

import bl.abstracts.Organism;

public class HomosepianOrganism extends Organism {

    public HomosepianOrganism(float strength, float intelligence, float technologicalMeans) {
        super(strength, intelligence, technologicalMeans);
    }

    public void updateOrganismByConstant(int constant) {
        updateIntelligenceByConstant(constant);
    }

    public String getType() {
        return "Homosepian";
    }
}
