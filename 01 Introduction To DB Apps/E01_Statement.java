public class E01_Statement {
    static final String E02_GET_VILLAINS_NAMES_SQL = """
                SELECT v.`name` AS name, count(distinct(mv.minion_id)) AS count_min
                FROM minions_villains AS mv
                JOIN villains AS v
                ON mv.villain_id = v.id
                GROUP BY mv.villain_id
                HAVING count_min > 15
                ORDER BY count_min DESC;""";

    static final String E03_GET_VILLAIN_NAME_BY_ID_SQL = "SELECT `name` FROM villains WHERE id = ?;";

    static final String E03_GET_MINIONS_NAMES_BY_VILLAIN_ID_SQL = """
                SELECT m.`name`, m.age
                FROM minions_villains AS mv
                JOIN minions AS m
                ON m.id = mv.minion_id
                WHERE mv.villain_id = ?
                GROUP BY mv.minion_id;""";

    static final String E04_INSERT_MINIONS_VILLAINS_SQL = "INSERT INTO minions_villains (minion_id, villain_id) VALUES (?, ?);";

    static final String E04_INSERT_MINIONS_SQL = "INSERT INTO minions (`name`, age, town_id) VALUES (?, ?, ?);";

    static final String E04_GET_VILLAIN_ID_BY_NAME_SQL = "SELECT id FROM villains WHERE `name` = ?;";

    static final String E04_GET_TOWN_ID_BY_NAME_SQL = "SELECT id FROM towns WHERE `name` = ?;";

    static final String E04_INSERT_VILLAINS_SQL = "INSERT INTO villains(`name`, `evilness_factor`) VALUES (?, 'evil');";

    static final String E04_INSERT_TOWN_SQL = "INSERT INTO towns(`name`) VALUES (?);";

    static final String E05_UPDATE_TOWNS_NAME_SQL = "UPDATE towns SET `name` = upper(`name`) WHERE country = ? ORDER BY id;";

    static final String E05_SELECT_TOWNS_BY_COUNTRY_SQL = "SELECT `name` FROM towns WHERE country = ?;";

    static final String E06_DELETE_VILLAIN_BY_ID_SQL = "DELETE FROM villains WHERE id = ?;";

    static final String E06_DELETE_MINIONS_VILLAIN_BY_ID_SQL = "DELETE FROM minions_villains WHERE villain_id = ?;";

    static final String E07_SELECT_ALL_MINIONS_SQL = "SELECT `name` FROM minions ORDER BY id;";

    static final String E08_UPDATE_MINIONS_BY_ID_SQL = "UPDATE minions SET age = age + 1, `name` = lower(`name`) WHERE id IN (%s);";

    static final String E08_SELECT_ALL_MINIONS_SQL = "SELECT `name`, age FROM minions ORDER BY id;";

    static final String E09_SELECT_MINIONS_INFO_BY_ID_SQL = "SELECT `name`, age FROM minions WHERE id = ?;";

    static final String E09_CALL_PROCEDURE = "{CALL usp_get_older(?)}";
}
