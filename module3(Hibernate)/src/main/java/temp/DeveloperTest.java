package temp;

import dao.DeveloperDao;
import dao.hibernatedaoimpl.DeveloperHibernateDaoImpl;
import entity.DeveloperEntity;
import entity.SkillEntity;

import java.util.Arrays;

public class DeveloperTest {
    public static void main(String[] args) {
        DeveloperEntity developerEntity = new DeveloperEntity();
        developerEntity.setName("testName1");
        developerEntity.setLastName("testLastName1");
        developerEntity.setEmail("test1@test1.com");
        developerEntity.setSalary(100500);

        SkillEntity skillEntity1 = new SkillEntity("ASP");
        skillEntity1.setId(9);

        SkillEntity skillEntity2 = new SkillEntity("someSkill");

        developerEntity.setSkillEntityList(Arrays.asList(skillEntity1));

        System.out.println(developerEntity);

        DeveloperDao developerDao = new DeveloperHibernateDaoImpl();
//        developerDao.create(developerEntity);

        developerEntity.setId(16);
        developerDao.delete(developerEntity);

    }
}
