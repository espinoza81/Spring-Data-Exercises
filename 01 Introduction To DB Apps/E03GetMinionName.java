import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class E03GetMinionName {
    private static int villainId;
    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        Scanner console = new Scanner(System.in);

        connection = E00Connection.getConnection();

        villainId = getVillainId(console);

        ResultSet villainName = getResultSet(E01_Statement.E03_GET_VILLAIN_NAME_BY_ID_SQL);

        if (villainName.next()) {
            String villain = villainName.getString("name");
            System.out.printf(E01_Outputs.E03_PRINT_VILLAIN_NAME, villain);

            printMinions();

        } else {
            System.out.printf(E01_Outputs.E03_PRINT_NO_VILLAIN_WHIT_ID, villainId);
        }

        connection.close();
    }

    private static ResultSet getResultSet(String sql) throws SQLException {
        PreparedStatement name = connection.prepareStatement(sql);

        name.setInt(1, villainId);
        return name.executeQuery();
    }

    static int getVillainId(Scanner console) {
        System.out.print(E01_Outputs.E03_ENTER_ID);
        return Integer.parseInt(console.nextLine());
    }

    private static void printMinions() throws SQLException {
        ResultSet minions_names = getResultSet(E01_Statement.E03_GET_MINIONS_NAMES_BY_VILLAIN_ID_SQL);

        for (int i = 1; minions_names.next(); i++) {
            String minion_name = minions_names.getString("name");
            int age = minions_names.getInt("age");
            System.out.printf(E01_Outputs.E03_PRINT_MINIONS_INFO, i, minion_name, age);
        }
    }
}