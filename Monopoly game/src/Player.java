import java.util.List;
import java.util.Map;

public abstract class Player implements Comparable<Player> {
    private String name;
    private String type;
    private int cash;
    private int sittingIndex;

    public Player(String name, String type, int cash) {
        this.name = name;
        this.type = type;
        this.cash = cash;
        this.sittingIndex = 0;
    }

    public boolean mustPayToField(Field field){
        return this.getCash() > field.getPrice();
    }

    public abstract boolean canSpend(Field field);

    @Override
    public String toString() {
        return  type +" "+ name;
    }

    @Override
    public int compareTo(Player player) {
        if(this.getCash() == player.getCash()){
            return 0;
        }
        return this.cash > player.getCash() ? 1 : -1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSittingIndex() {
        return sittingIndex;
    }

    public void setSittingIndex(int newSittingIndex, int fieldSize) {
        if(sittingIndex <= fieldSize){
            this.sittingIndex = this.sittingIndex + newSittingIndex;
            this.sittingIndex = this.sittingIndex > fieldSize ? (this.sittingIndex%fieldSize) : (sittingIndex);
        }
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash += cash;
    }
}
