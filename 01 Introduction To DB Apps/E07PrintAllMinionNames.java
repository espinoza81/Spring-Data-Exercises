import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;

public class E07PrintAllMinionNames {
    public static void main(String[] args) throws SQLException {
        Connection connection = E00Connection.getConnection();

        PreparedStatement getAllMinions = connection.prepareStatement(E01_Statement.E07_SELECT_ALL_MINIONS_SQL);
        ResultSet rs = getAllMinions.executeQuery();

        ArrayDeque<String> minionsName = new ArrayDeque<>();

        while (rs.next()) {
            minionsName.offer(rs.getString("name"));
        }

        while (minionsName.size() > 2){
            System.out.println(minionsName.pollFirst());
            System.out.println(minionsName.pollLast());
        }

        while(!minionsName.isEmpty()) {
            System.out.println(minionsName.poll());
        }

        connection.close();
    }
}
