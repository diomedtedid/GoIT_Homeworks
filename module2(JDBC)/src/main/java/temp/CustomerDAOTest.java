package temp;

import dao.CustomerDAO;
import dao.jdbcdaoimpl.CustomerDAOImpl;
import domain.Customer;

import java.sql.SQLException;

/**
 * Created by User on 22.07.2017.
 */
public class CustomerDAOTest {
    static CustomerDAO customerDAO = new CustomerDAOImpl();
    public static void main(String[] args) throws SQLException {
//        System.out.println(customerDAO.readAll());
//        System.out.println(customerDAO.read(1l));
//        create();
        delete(5);
    }

    static void create() throws SQLException {
        Customer customer = new Customer();
        customer.setName("test1");
        customer.setLastName("test1");
        customerDAO.create(customer);
    }

    static void update() {

    }

    static void delete (long id) throws SQLException {
        Customer customer = new Customer();
        customer.setId(id);
        customerDAO.delete(customer);
    }
}
