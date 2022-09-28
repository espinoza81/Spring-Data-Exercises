import java.sql.*;
import java.util.Scanner;

public class E09IncreaseAgeStoredProcedure {
    private static Connection connection;
    private static int minionId;
    public static void main(String[] args) throws SQLException {

        minionId = getMinionId();

        connection = E00Connection.getConnection();

        ResultSet minionInfo = getMinionInfo();

        if(minionInfo.next()) {
            updateMinionAge();

            minionInfo = getMinionInfo();

            printMinionInfo(minionInfo);

        } else {
            System.out.printf(E01_Outputs.E09_PRINT_NO_MINION_WHIT_ID, minionId);
        }

        connection.close();
    }

    private static void printMinionInfo(ResultSet rs) throws SQLException {
        rs.next();

        String name = rs.getString("name");
        int age = rs.getInt("age");

        System.out.println( name + " " + age);
    }

    private static ResultSet getMinionInfo() throws SQLException {
        PreparedStatement selectMinion = connection.prepareStatement(E01_Statement.E09_SELECT_MINIONS_INFO_BY_ID_SQL);

        selectMinion.setInt(1, minionId);

        return selectMinion.executeQuery();
    }

    private static void updateMinionAge() throws SQLException {
        CallableStatement updateMinionAge = connection.prepareCall(E01_Statement.E09_CALL_PROCEDURE);

        updateMinionAge.setInt(1, minionId);

        updateMinionAge.executeUpdate();
    }

    public static int getMinionId() {
        Scanner console = new Scanner(System.in);

        System.out.print(E01_Outputs.E03_ENTER_ID);

        return Integer.parseInt(console.nextLine());
    }
}

//in SQL database

//CREATE PROCEDURE `usp_get_older`(minion_id INT)
//BEGIN
//      UPDATE minions
//      SET age = age + 1
//      WHERE id = minion_id;
//END
