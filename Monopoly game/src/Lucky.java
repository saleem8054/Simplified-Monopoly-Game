public class Lucky extends Field {

    public Lucky(String type, int price) {
        super(type, price);
    }

    @Override
    public boolean stepOn(Player player) {
        System.out.println(player + " has landed on " + this + " and gets " + this.getPrice() + " as a bonus.");
        player.setCash(this.getPrice());
        System.out.println(player + " updated cash is " + player.getCash() + "\n");
        return true;
    }
}
