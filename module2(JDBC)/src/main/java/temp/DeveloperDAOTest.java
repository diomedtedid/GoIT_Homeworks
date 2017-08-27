package temp;

import dao.jdbcdaoimpl.DeveloperDAOImpl;
import domain.Developer;
import domain.Skill;
import tools.DBCPConnectionManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 17.06.2017.
 */
public class DeveloperDAOTest {
    static DeveloperDAOImpl developerDAO = new DeveloperDAOImpl();


    public static void main(String[] args) throws SQLException {


        DBCPConnectionManager dbcpConnectionManager = new DBCPConnectionManager();
        developerDAO.setConnectionManager(dbcpConnectionManager);


//        System.out.println(developerDAO.read(7L));
//        System.out.println(developerDAO.readAll());
//        create();
//        delete(25);
        update(27);
    }

    private static void create() throws SQLException {
        Developer developer = new Developer();
        developer.setName("TestName1");
        developer.setLastName("TestLastName1");
        developer.setEmail("test1@test.com");
        developer.setSalary(1.1);
        List<Skill> skillList = new ArrayList<>();
        skillList.add(new Skill("JAVA"));
        skillList.add(new Skill("c++"));
        skillList.add(new Skill("delphy"));
        developer.setSkillList(skillList);

        developerDAO.create(developer);
    }

    private static void delete(long id) throws SQLException {
        Developer developer = new Developer();
        developer.setId(id);
        developerDAO.delete(developer);
    }

    private static void update (long id) throws SQLException {
        Developer developer = developerDAO.read(id);
        developer.setName("HUI1");
        List<Skill> skillList = developer.getSkillList();
        skillList.remove(1);
        skillList.add(new Skill("C#"));

        developerDAO.update(developer);
    }
}
