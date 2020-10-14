public class Tactical extends Player {
    private int counter = 1;

    public Tactical(String name, String type, int cash) {
        super(name, type, cash);
    }

    @Override
    public boolean canSpend(Field field) {
        counter++;
        return counter % 2 == 0 && this.getCash() >= field.getPrice();
    }
}
