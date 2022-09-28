import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class E06RemoveVillain {
    private static int villainId;
    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        Scanner console = new Scanner(System.in);

        connection = E00Connection.getConnection();

        villainId = E03GetMinionName.getVillainId(console);

        ResultSet villainName = getVillainName();

        if (!villainName.next()) {
            System.out.println(E01_Outputs.E06_NO_VILLAIN_WHIT_ID);
            connection.close();
            return;
        }

        connection.setAutoCommit(false);

        try {
            String villain = villainName.getString("name");

            int releasedMinions = deleteRecords(E01_Statement.E06_DELETE_MINIONS_VILLAIN_BY_ID_SQL);

            deleteRecords(E01_Statement.E06_DELETE_VILLAIN_BY_ID_SQL); //delete villain

            connection.commit();

            System.out.printf(E01_Outputs.E06_SUCCESSFULLY_DELETED_VILLAIN, villain);
            System.out.printf(E01_Outputs.E06_SUCCESSFULLY_RELEASED_MINIONS, releasedMinions);

        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }

        connection.close();
    }

    private static int deleteRecords(String sql) throws SQLException {
        PreparedStatement deleteStmt = connection.prepareStatement(sql);

        deleteStmt.setInt(1, villainId);

        return deleteStmt.executeUpdate();
    }

    private static ResultSet getVillainName() throws SQLException {
        PreparedStatement name = connection.prepareStatement(E01_Statement.E03_GET_VILLAIN_NAME_BY_ID_SQL);

        name.setInt(1, villainId);

        return name.executeQuery();
    }
}
