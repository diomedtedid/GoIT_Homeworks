package temp;

import dao.GeneralDao;
import dao.SkillDao;
import dao.hibernatedaoimpl.SkillHibernateDaoImpl;
import entity.Skill;

public class SkillTest {
    public static void main(String[] args) {
        Skill skill = new Skill("module3");
        GeneralDao skillDao = new SkillHibernateDaoImpl();
        skill.setId(20);
//        skillDao.delete(skill);
//        skillDao.create(skill);

        System.out.println(skillDao.readAll());
    }
}
