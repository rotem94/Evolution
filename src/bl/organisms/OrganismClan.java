package bl.organisms;

public class OrganismClan {

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

    public void callNewLifeCycle() {
        organismInfo.updateCharacteristicsByConstantAndMutation(CONSTANT_TO_UPDATE_ORGANISM, mutation);
        updateBalanceByMultiplication();
    }

    private void updateBalanceByMultiplication() {
        balance *= multiplication;
    }

    public String toString() {
        return name + " -> " + "[" + organismInfo + ", balance=" + balance + "][" + organismInfo.getType() + "]";
    }

    public String getType() {
        return organismInfo.getType();
    }

    public float getPower() {
        return organismInfo.sumProperties();
    }

    public int getAttackPoints() {
        return balance * 2;
    }

    public int getDefensePoints() {
        return (int) Math.ceil((float)balance / 2);
    }

    public void decreaseBalance(int number) {
        balance -= number;
    }

    public boolean checkIfOrganismEliminated() {
        return balance <= 0;
    }
}
