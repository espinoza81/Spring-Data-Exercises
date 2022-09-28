import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class E02GetVillainsNames {
    public static void main(String[] args) throws SQLException {
        Connection connection = E00Connection.getConnection();

        PreparedStatement stmt = connection.prepareStatement(E01_Statement.E02_GET_VILLAINS_NAMES_SQL);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String name = rs.getString("name");
            int count = rs.getInt("count_min");
            System.out.println(name + " " + count);
        }
        connection.close();
    }
}
