package bl.organisms;

import bl.abstracts.Organism;

public class NeanderthalOrganism extends Organism {

    public NeanderthalOrganism(float strength, float intelligence, float technologicalMeans) {
        super(strength, intelligence, technologicalMeans);
    }

    public void updateOrganismByConstant(int constant) {
        updateStrengthByConstant(constant);
    }

    public String getType() {
        return "Neanderthal";
    }
}
