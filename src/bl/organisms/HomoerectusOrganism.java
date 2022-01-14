package bl.organisms;

public class HomoerectusOrganism extends Organism {

    public HomoerectusOrganism(float strength, float intelligence, float technologicalMeans) {
        super(strength, intelligence, technologicalMeans);
    }

    public void updateOrganismCharacteristicByConstant(int constant) {
        updateTechnologicalMeansByConstant(constant);
    }

    public String getType() {
        return "Homoerectus";
    }
}
