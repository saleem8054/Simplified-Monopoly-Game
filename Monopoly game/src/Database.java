import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.List;

public class Database {
    private final Map<Integer, Field> fields;
    private List<Player> players;
    private Queue<Integer> dices;

    public Database() {
        this.fields = new LinkedHashMap<>();
        this.players = new ArrayList<>();
        this.dices = new ArrayDeque<>();
    }

    public void read(String filename, String filename2, String filename3) throws FileNotFoundException, InvalidInputException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
        int numOfFields = sc.nextInt();
        int index = 1;
        while (sc.hasNext()) {
            Field field;
            switch (sc.next()) {
                case "S":
                    field = new Service("Service", sc.nextInt());
                    break;
                case "P":
                    field = new Property("Property", sc.nextInt(), null);
                    break;
                case "L":
                    field = new Lucky("Lucky", sc.nextInt());
                    break;
                default:
                    throw new InvalidInputException();
            }
            this.fields.put(index++, field);
        }

        sc = new Scanner(new BufferedReader(new FileReader(filename2)));

        int numOfPlayers = sc.nextInt();
        while (sc.hasNext()) {
            Player player;
            switch (sc.next()) {
                case "G":
                    player = new Greedy(sc.next(), "Greedy", 10000);
                    break;
                case "C":
                    player = new Careful(sc.next(), "Careful", 10000);
                    break;
                case "T":
                    player = new Tactical(sc.next(), "Tactical", 10000);
                    break;
                default:
                    throw new InvalidInputException();
            }
            this.players.add(player);
        }
        sc = new Scanner(new BufferedReader(new FileReader(filename3)));
        while (sc.hasNext()) {
            this.dices.add(sc.nextInt());
        }
    }

    public void report() {
        int k = 0;
        while (!dices.isEmpty()) {
            int dice = dices.remove();
            players.get(k).setSittingIndex(dice, fields.size());
            int sittingIndex = players.get(k).getSittingIndex();
            Field field = fields.get(sittingIndex);
            if (!field.stepOn(players.get(k))) {
                System.out.println(Color.ANSI_RED + players.get(k) + " is unable to pay to " + field.getType() + " the required money,hence loses." + Color.ANSI_RESET);
                if (checkProperty(players.get(k), fields)) {
                    System.out.println(Color.ANSI_GREEN + players.get(k) + " properties become available." + Color.ANSI_RESET);
                }
                players.remove(k--);
                System.out.println();
            }
            k++;
            k = k == players.size() ? 0 : k;
        }
    }

    public boolean checkProperty(Player player, Map<Integer, Field> fields) { //checks if the player has any property if he is not able to pay any cash.
        boolean flag = false;
        for (Field field : fields.values()) {
            if (field.checkIfPropertyAvailable(player) == 1) {
                flag = true;
            }
        }
        return flag;
    }

    public Queue<Integer> getDices() {
        return dices;
    }

    public void setDices(Queue<Integer> dices) {
        this.dices = dices;
    }

    public Map<Integer, Field> getFields() {
        return fields;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
