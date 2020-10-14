import java.util.Map;

public abstract class Field {
    private String type;
    private int price;

    public Field(String type, int price) {
        this.type = type;
        this.price = price;
    }

    public int checkIfPropertyAvailable(Player player){
        return 0;
    }

    public abstract boolean stepOn(Player player);

    @Override
    public String toString() {
        return  type +" with the price of "+ price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
