package temp;

import dao.jdbcdaoimpl.SkillDAOImpl;
import domain.Skill;
import tools.DBCPConnectionManager;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.SQLException;

/**
 * Created by User on 12.06.2017.
 */
public class SkillDAOTEST {
    public static void main(String[] args) throws SQLException {
        //common-dbcp пул коннекшенов к БД
        //Java.util.concurent - threadpool

        //Прописать иньекцию класса коннекшена к БД

        Skill skill = new Skill("PYTHON");

        SkillDAOImpl skillDAO = new SkillDAOImpl();

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://193.151.13.50:3306/hw1_1" +
                "?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        dataSource.setUsername("troy");
        dataSource.setPassword("2206");

        DBCPConnectionManager dbcpConnectionManager = new DBCPConnectionManager();

        skillDAO.setConnectionManager(dbcpConnectionManager);
//        skillDAO.create(skill);

        System.out.println(skillDAO.read(4L));
        skill = new Skill("C++");
        skill.setId(3);
        skillDAO.update(skill);
        skill.setId(0);
        skillDAO.delete(skill);

        System.out.println(skillDAO.readAll());
    }
}
