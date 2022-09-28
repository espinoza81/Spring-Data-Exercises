import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class E05ChangeTownNamesCasing {
    private static String country;

    public static void main(String[] args) throws SQLException {
        Connection connection = E00Connection.getConnection();

        Scanner console = new Scanner(System.in);

        country = console.nextLine();

        int update_records = setTownNameUppercase(connection);

        if(update_records == 0) {
            System.out.println("No" + E01_Outputs.E05_COUNT_TOWNS_AFFECTED);

        } else {
            System.out.println(update_records + E01_Outputs.E05_COUNT_TOWNS_AFFECTED);

            ResultSet towns_name = getTownsName(connection);

            printTownsName(towns_name);
        }

        connection.close();
    }

    private static void printTownsName(ResultSet towns_name) throws SQLException {
        List<String> towns = new ArrayList<>();

        while (towns_name.next()) {
            towns.add(towns_name.getString("name"));
        }

        System.out.println(towns);
    }

    private static ResultSet getTownsName(Connection connection) throws SQLException {
        PreparedStatement towns_stmt = connection.prepareStatement(E01_Statement.E05_SELECT_TOWNS_BY_COUNTRY_SQL);

        towns_stmt.setString(1, country);

        return towns_stmt.executeQuery();
    }

    private static int setTownNameUppercase(Connection connection) throws SQLException {
        PreparedStatement update_towns = connection.prepareStatement(E01_Statement.E05_UPDATE_TOWNS_NAME_SQL);

        update_towns.setString(1, country);

        return update_towns.executeUpdate();
    }
}
