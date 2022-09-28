import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class E08IncreaseMinionsAge {
    public static void main(String[] args) throws SQLException {
        Scanner console = new Scanner(System.in);

        List<String> minionsId = Arrays.stream(console.nextLine().split("\\s+")).collect(Collectors.toList());

        Connection connection = E00Connection.getConnection();

        updateMinions(minionsId, connection);

        printMinions(connection);

        connection.close();
    }

    private static void printMinions(Connection connection) throws SQLException {
        PreparedStatement selectMinions = connection.prepareStatement(E01_Statement.E08_SELECT_ALL_MINIONS_SQL);
        ResultSet printMinions = selectMinions.executeQuery();

        while (printMinions.next()) {
            String name = printMinions.getString("name");
            int age = printMinions.getInt("age");

            System.out.println(name + " " + age);
        }
    }

    private static void updateMinions(List<String> minionsId, Connection connection) throws SQLException {
        String updateMinionsSQL = String.format(E01_Statement.E08_UPDATE_MINIONS_BY_ID_SQL,
                        minionsId.stream()
                        .map(v -> "?")
                        .collect(Collectors.joining(", ")));

        PreparedStatement updateMinions = connection.prepareStatement(updateMinionsSQL);

        for(int i = 1; i <= minionsId.size(); i++) {
            updateMinions.setInt(i, Integer.parseInt(minionsId.get(i-1)));
        }

        updateMinions.executeUpdate();
    }
}
