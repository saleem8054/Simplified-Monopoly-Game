import java.util.Map;

public class Greedy extends Player {

    public Greedy(String name, String type, int cash) {
        super(name, type, cash);
    }

    @Override
    public boolean canSpend(Field field) {
        if(this.getCash() >= field.getPrice()){
            return true;
        }
        return false;
    }
}
