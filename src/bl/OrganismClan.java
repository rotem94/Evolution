package bl;

import bl.abstracts.Organism;

public class OrganismClan {

    private int balance;
    private float multiplication;
    private float mutation;
    private Organism organismInfo;

    public OrganismClan(int balance, float multiplication, float mutation, Organism organismInfo) {
        this.balance = balance;
        this.multiplication = multiplication;
        this.mutation = mutation;
        this.organismInfo = organismInfo;
    }
}
