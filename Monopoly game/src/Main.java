import java.io.FileNotFoundException;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        Database data = new Database();
        try {
            data.read("Fields.txt","Players.txt","Dice.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            System.exit(-1);
        } catch (InvalidInputException ex) {
            System.out.println("Invalid input!");
            System.exit(-1);
        }

        data.report();

        Player player = Collections.max(data.getPlayers());
        System.out.println("Winner is: "+player+" "+player.getCash());
    }
}
