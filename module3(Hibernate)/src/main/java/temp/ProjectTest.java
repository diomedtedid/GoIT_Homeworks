package temp;

import dao.ProjectDao;
import dao.hibernatedaoimpl.ProjectHibernateDaoImpl;

public class ProjectTest {
    public static void main(String[] args) {
        ProjectDao projectDao = new ProjectHibernateDaoImpl();
        long begine = System.currentTimeMillis();
        System.out.println(projectDao.readAll());
        System.out.println("TOTAL TIME IS " + (System.currentTimeMillis() - begine));

//        System.out.println(projectDao.read(1L));
    }
}
