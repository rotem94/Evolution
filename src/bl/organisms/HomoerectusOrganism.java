package bl.organisms;

import bl.abstracts.Organism;

public class HomoerectusOrganism extends Organism {

    public HomoerectusOrganism(float strength, float intelligence, float technologicalMeans) {
        super(strength, intelligence, technologicalMeans);
    }

    public void updateOrganismByConstant(int constant) {
        updateTechnologicalMeansByConstant(constant);
    }

    public String getType() {
        return "Homoerectus";
    }
}
