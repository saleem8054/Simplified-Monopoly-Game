public class Careful extends Player {
    private final int initialCash;

    public Careful(String name, String type, int cash) {
        super(name, type, cash);
        this.initialCash = cash;
    }

    @Override
    public boolean canSpend(Field field) {
        return this.getCash() > initialCash / 2 && this.getCash() >= field.getPrice();
    }
}
