package temp;

import dao.CustomerDao;
import dao.hibernatedaoimpl.CustomerHibernateDaoImpl;

public class CustomerTest {
    public static void main(String[] args) {
        CustomerDao customerDao = new CustomerHibernateDaoImpl();
        System.out.println(customerDao.readAll());
    }
}
