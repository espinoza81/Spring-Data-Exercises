import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class E04AddMinion {
    static String minion_name;
    static String villain;
    static Connection connection;

    public static void main(String[] args) throws SQLException {
        Scanner console = new Scanner(System.in);
        String[] minion = console.nextLine().split("\\s+");

        minion_name = minion[1];
        int minion_age = Integer.parseInt(minion[2]);
        String minion_town = minion[3];

        villain = console.nextLine().split("\\s+")[1];

        connection = E00Connection.getConnection();

        int town_id = E04Functions.add_town_if_not_exist(minion_town);

        int villain_id = E04Functions.add_villain_if_not_exist();

        int minion_id = E04Functions.add_minion(minion_age, town_id);

        E04Functions.add_minion_to_villain(villain_id, minion_id);

        connection.close();
    }
}
