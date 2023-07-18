package gr.aueb.cf.schoolapp.dao.dbutil;

import gr.aueb.cf.schoolapp.service.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {

    private DBHelper() {}

    public static void eraseData() throws SQLException {
        Connection connection = null;
        ResultSet rs = null;

        try {
            connection = DBUtil.getConnection();
            connection.prepareStatement("SET @@foreign_key_checks = 0").executeUpdate();
            rs = connection.prepareStatement("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = 'schooldb'").executeQuery();
            List<String> tables = mapRsToList(rs);

            for (String table : tables) {
                connection.prepareStatement("DELETE FROM " + table).executeUpdate();
                connection.prepareStatement("ALTER TABLE " + table + " AUTO_INCREMENT=1").executeUpdate();
            }
            connection.prepareStatement("SET @@foreign_key_checks = 1").executeUpdate();
        } finally {
            try {
                if (rs != null) rs.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<String> mapRsToList(ResultSet rs) throws SQLException {
        List<String> list = new ArrayList<>();

        while (rs.next()) {
            list.add(rs.getString(("TABLE_NAME")));
        }
        return list;
    }
}
