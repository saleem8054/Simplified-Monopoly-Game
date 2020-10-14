public class Service extends Field {

    public Service(String type, int price) {
        super(type, price);
    }

    @Override
    public boolean stepOn(Player player) {
        if (player.mustPayToField(this)) {
            System.out.println(player + " has landed on " + this + " and pays to the bank " + this.getPrice());
            player.setCash(-this.getPrice());
            System.out.println(player + " updated cash is " + player.getCash() + "\n");
            return true;
        } else {
            return false;
        }
    }
}
