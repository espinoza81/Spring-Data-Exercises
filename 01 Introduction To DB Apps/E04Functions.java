import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class E04Functions {
    static void add_minion_to_villain(int villain_id, int minion_id) throws SQLException {
        PreparedStatement add_m_to_v = E04AddMinion.connection.prepareStatement(
                E01_Statement.E04_INSERT_MINIONS_VILLAINS_SQL);

        add_m_to_v.setInt(1, minion_id);
        add_m_to_v.setInt(2, villain_id);
        add_m_to_v.executeUpdate();

        System.out.printf(E01_Outputs.E04_SUCCESSFULLY_ADDED_MINION_TO_VILLAIN,
                E04AddMinion.minion_name, E04AddMinion.villain);
    }

    static int add_minion(int minion_age, int town_id) throws SQLException {
        PreparedStatement add_minion = E04AddMinion.connection.prepareStatement(
                E01_Statement.E04_INSERT_MINIONS_SQL, Statement.RETURN_GENERATED_KEYS);

        add_minion.setString(1, E04AddMinion.minion_name);
        add_minion.setInt(2, minion_age);
        add_minion.setInt(3, town_id);
        add_minion.executeUpdate();
        ResultSet rs = add_minion.getGeneratedKeys();
        rs.next();

        return rs.getInt(1);
    }

    static int add_villain_if_not_exist() throws SQLException {
        PreparedStatement has_villain = E04AddMinion.connection.prepareStatement(
                E01_Statement.E04_GET_VILLAIN_ID_BY_NAME_SQL);

        has_villain.setString(1, E04AddMinion.villain);
        ResultSet rs = has_villain.executeQuery();

        if (!rs.next()) {
            PreparedStatement add_villain = E04AddMinion.connection.prepareStatement(
                    E01_Statement.E04_INSERT_VILLAINS_SQL, Statement.RETURN_GENERATED_KEYS);

            add_villain.setString(1, E04AddMinion.villain);
            add_villain.executeUpdate();
            rs = add_villain.getGeneratedKeys();
            rs.next();

            System.out.printf(E01_Outputs.E04_SUCCESSFULLY_ADDED_VILLAIN,
                    E04AddMinion.villain);
        }

        return rs.getInt(1);
    }

    static int add_town_if_not_exist(String minion_town) throws SQLException {
        PreparedStatement has_town = E04AddMinion.connection.prepareStatement(
                E01_Statement.E04_GET_TOWN_ID_BY_NAME_SQL);

        has_town.setString(1, minion_town);
        ResultSet rs = has_town.executeQuery();

        if (!rs.next()) {
            PreparedStatement add_town = E04AddMinion.connection.prepareStatement(
                    E01_Statement.E04_INSERT_TOWN_SQL, Statement.RETURN_GENERATED_KEYS);

            add_town.setString(1, minion_town);
            add_town.executeUpdate();
            rs = add_town.getGeneratedKeys();
            rs.next();

            System.out.printf(E01_Outputs.E04_SUCCESSFULLY_ADDED_TOWN, minion_town);
        }

        return rs.getInt(1);
    }
}
