package bl.organisms;

public class NeanderthalOrganism extends Organism {

    public NeanderthalOrganism(float strength, float intelligence, float technologicalMeans) {
        super(strength, intelligence, technologicalMeans);
    }

    public void updateOrganismCharacteristicByConstant(int constant) {
        updateStrengthByConstant(constant);
    }

    public String getType() {
        return "Neanderthal";
    }
}
