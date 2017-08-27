package temp;

import dao.jdbcdaoimpl.ProjectDAOImpl;
import tools.ConnectionManager;
import tools.DBCPConnectionManager;

import java.sql.SQLException;

/**
 * Created by User on 24.06.2017.
 */
public class ProjectDAOTest {
    static ProjectDAOImpl projectDAO = new ProjectDAOImpl();

    public static void main(String[] args) throws SQLException {

        DBCPConnectionManager dbcpConnectionManager = new DBCPConnectionManager();
        ConnectionManager connectionManager = new ConnectionManager();
        projectDAO.setConnectionManager(dbcpConnectionManager);
//        projectDAO.setConnectionManager(connectionManager);

        read(1L);

        System.out.println(projectDAO.readAll());
        System.out.println(projectDAO.readAll());

    }

    private static void read (Long id) throws SQLException {
        System.out.println(projectDAO.read(id));
    }
}
