package temp;

import dao.GeneralDao;
import dao.hibernatedaoimpl.SkillHibernateDaoImpl;
import entity.SkillEntity;

public class SkillTest {
    public static void main(String[] args) {
        SkillEntity skillEntity = new SkillEntity("module3");
        GeneralDao skillDao = new SkillHibernateDaoImpl();
        skillEntity.setId(20);
//        skillDao.delete(skillEntity);
//        skillDao.create(skillEntity);

        System.out.println(skillDao.readAll());
    }
}
