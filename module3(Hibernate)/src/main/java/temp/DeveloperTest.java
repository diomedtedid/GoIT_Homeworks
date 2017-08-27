package temp;

import dao.DeveloperDao;
import dao.hibernatedaoimpl.DeveloperHibernateDaoImpl;
import entity.Developer;
import entity.SkillEntity;

import java.util.Arrays;

public class DeveloperTest {
    public static void main(String[] args) {
        Developer developer = new Developer();
        developer.setName("testName1");
        developer.setLastName("testLastName1");
        developer.setEmail("test1@test1.com");
        developer.setSalary(100500);

        SkillEntity skillEntity1 = new SkillEntity("ASP");
        skillEntity1.setId(9);

        SkillEntity skillEntity2 = new SkillEntity("someSkill");

        developer.setSkillEntityList(Arrays.asList(skillEntity1));

        System.out.println(developer);

        DeveloperDao developerDao = new DeveloperHibernateDaoImpl();
//        developerDao.create(developer);

        developer.setId(16);
        developerDao.delete(developer);

    }
}
