package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by User on 12.06.2017.
 */
public class ConnectionManager {

    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/hw1_1?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String pass = "2206";

        return DriverManager.getConnection(url, user, pass);
    }
}
