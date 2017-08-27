package tools;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by User on 12.06.2017.
 */
public class DBCPConnectionManager extends ConnectionManager  {
    private static BasicDataSource dataSource;
    static {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://35.198.143.85:3306/goit" +
                "?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        dataSource.setUsername("goit");
        dataSource.setPassword("goit");
    }



    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
