package temp;

import dao.CompanyDao;
import dao.hibernatedaoimpl.CompanyHibernateDaoImpl;

public class CompanyTest {
    public static void main(String[] args) {
        CompanyDao companyDao = new CompanyHibernateDaoImpl();
        System.out.println(companyDao.readAll());
    }
}
