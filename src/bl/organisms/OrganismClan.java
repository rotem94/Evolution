package bl.organisms;

import bl.abstracts.Organism;

public class OrganismClan extends Thread{

    private static final int CONSTANT_TO_UPDATE_ORGANISM = 2;

    private int balance;

    private final int multiplication;
    private final float mutation;
    private final String name;
    private final Organism organismInfo;

    public OrganismClan(int balance, int multiplication, float mutation, String name, Organism organismInfo) {
        this.balance = balance;
        this.multiplication = multiplication;
        this.mutation = mutation;
        this.name = name;
        this.organismInfo = organismInfo;
    }

    public void run() {
        newLifeCycle();
        System.out.println(this);
        newLifeCycle();
        System.out.println(this);
    }

    public void newLifeCycle() {
        organismInfo.updateCharacterByConstantAndRandomOneByMutation(CONSTANT_TO_UPDATE_ORGANISM, mutation);
        updateBalanceByMultiplication();
    }

    private void updateBalanceByMultiplication() {
        balance *= multiplication;
    }

    public String toString() {
        return name + " -> " + "[" + organismInfo + ", balance=" + balance + "][" + organismInfo.getType() + "]";
    }
}
