package temp;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * Created by User on 12.06.2017.
 */
public class ConnPoolTEST {
    public static void main(String[] args) {
        BasicDataSource basicDataSource= new BasicDataSource();
        basicDataSource.setUrl("jdbc:mysql://35.198.143.85/goit?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        basicDataSource.setUsername("goit");
        basicDataSource.setPassword("goit");

    }
}
