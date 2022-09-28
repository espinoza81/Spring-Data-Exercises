import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class E00Connection {
    public static Connection getConnection() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "1qaz2wsx");

        return DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions", props);
    }
}
