public class Property extends Field {
    private Player playerWhoOwns;
    private boolean hasHouse;

    public Property(String type, int price, Player playerWhoOwns) {
        super(type, price);
        this.playerWhoOwns = playerWhoOwns;
        this.hasHouse = false;
    }

    @Override
    public boolean stepOn(Player player) {
        String stopMessage = Color.ANSI_BLUE + player + " has stopped playing for now. he has " + player.getCash() + Color.ANSI_RESET + "\n";
        if (this.playerWhoOwns == null) { // if house is not owned by someone
            if (player.canSpend(this)) { //check to see if a player is greedy,careful or tactic basically.
                this.playerWhoOwns = player;
                System.out.println(player + " has bought the property for 1000");
                player.setCash(-1000);
                System.out.println(player + " updated cash is " + player.getCash() + "\n");
            } else {
                System.out.println(stopMessage);
            }
            return true;
        } else if (this.playerWhoOwns == player && !this.hasHouse) { //owner is same but he has not bought the house.
            if (player.canSpend(this)) { //checks to see if a player is greedy,careful or tactic
                hasHouse = true;        //set the hasHouse field to true.
                System.out.println(player + " has build the house for 4000");
                player.setCash(-4000);
                System.out.println(player + " updated cash is " + player.getCash() + "\n");
            } else {
                System.out.println(stopMessage);
            }
            return true;
        } else if (this.playerWhoOwns != player && !this.hasHouse) { //if he lands on someone property and house is not build.
            System.out.println(player + " has landed on " + this.playerWhoOwns + " property.");
            if (player.mustPayToField(this)) { //if the current player can pay the cash to the owner
                this.playerWhoOwns.setCash(500);
                System.out.println("Owner of the property " + this.playerWhoOwns + " gets 500");
                System.out.println(this.playerWhoOwns + " updated cash is " + this.playerWhoOwns.getCash());
                player.setCash(-500);
                System.out.println(player + " loses 500");
                System.out.println(player + " updated cash is " + player.getCash() + "\n");
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println(player + " has landed on " + this.playerWhoOwns + " property");
            if (player.mustPayToField(this)) { //if the player can pay
                System.out.println(this.playerWhoOwns + " also has a house on it.");
                this.playerWhoOwns.setCash(2000);
                System.out.println("Owner of the property " + this.playerWhoOwns + " gets 2000");
                System.out.println("Owner of the property " + this.playerWhoOwns + " updated cash is " + this.playerWhoOwns.getCash());
                player.setCash(-2000);
                System.out.println(player + " loses 2000");
                System.out.println(player + " updated cash is " + player.getCash() + "\n");
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public int checkIfPropertyAvailable(Player player) {
        if (this.playerWhoOwns == player) {
            this.playerWhoOwns = null;  //if it is own by a player who has no cash,then his ownership is removed
            if (this.hasHouse) {
                this.hasHouse = false; //and if he owns the house on the same property then his house is also removed.
            }
            return 1;
        }
        return -1;
    }

    @Override
    public String toString() {
        return getType() + " " + getPrice() + " " + this.playerWhoOwns.getName();
    }

    public Player getPlayerWhoOwns() {
        return playerWhoOwns;
    }

    public boolean HasHouse() {
        return hasHouse;
    }

    public void setHasHouse(boolean hasHouse) {
        this.hasHouse = hasHouse;
    }

    public void setPlayerWhoOwns(Player playerWhoOwns) {
        this.playerWhoOwns = playerWhoOwns;
    }
}
