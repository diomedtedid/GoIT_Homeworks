package dao.jdbcdaoimpl;

import dao.CustomerDAO;
import dao.ProjectDAO;
import domain.Customer;
import domain.Project;
import tools.ConnectionManager;
import tools.DBCPConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 20.07.2017.
 */
public class CustomerDAOImpl implements CustomerDAO {
    private ProjectDAO projectDAO = new ProjectDAOImpl();
    private ConnectionManager connectionManager = new DBCPConnectionManager();

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void create(Customer entity) throws SQLException {
        String createQuery = "INSERT INTO goit.customers (cust_name, cust_lastname) VALUES (?, ?);";
        try (Connection connection = connectionManager.getConnection()){
            try {
                connection.setAutoCommit(false);

                try (PreparedStatement preparedStatement = connection.prepareStatement(createQuery)) {
                    preparedStatement.setString(1, entity.getName());
                    preparedStatement.setString(2, entity.getLastName());
                    preparedStatement.executeUpdate();
                }

                List<Project> projectList = entity.getProjectList();
                if (projectList != null) {
                    if (!projectList.isEmpty()) {
                        long lastId;
                        String idQuery = "SELECT cust_id FROM goit.customers ORDER BY cust_id DESC LIMIT 1";

                        try (PreparedStatement preparedStatement = connection.prepareStatement(idQuery)){
                            ResultSet resultSet = preparedStatement.executeQuery();
                            resultSet.next();
                            lastId = resultSet.getLong("cust_id");
                            entity.setId(lastId);
                        }

                        writeProjectList (connection, entity);
                    }
                }


                connection.commit();
                connection.setAutoCommit(true);
            }catch (Exception e) {
                //Если произошла ошибка на любом из этапов откатываем все добавления
                e.printStackTrace();
                connection.rollback();
                connection.setAutoCommit(true);
            }
        }
    }

    private void writeProjectList(Connection connection, Customer entity) throws SQLException {
        List<Project> projectList = entity.getProjectList();
        List<Project> generalProjectList = this.projectDAO.readAll();

        String connectionQuery = "INSERT INTO goit.customers_has_projects (customers_id, projects_id) VALUES (?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(connectionQuery)){
            for (Project project : projectList) {
                int index = generalProjectList.indexOf(project);
                if (index != -1) {
                    preparedStatement.setLong(1, entity.getId());
                    preparedStatement.setLong(2, generalProjectList.get(index).getId());
                    preparedStatement.addBatch();
                }
            }

            preparedStatement.executeBatch();
        }
    }

    @Override
    public Customer read(Long key) throws SQLException {
        Customer customer = null;
        String readQuery = "SELECT * FROM goit.customers WHERE cust_id = ?";
        try (Connection connection = connectionManager.getConnection()){
            try (PreparedStatement preparedStatement = connection.prepareStatement(readQuery)){
                preparedStatement.setLong(1, key);

                try (ResultSet resultSet = preparedStatement.executeQuery()){
                    if (resultSet.next()) {
                        customer = new Customer();
                        customer.setId(key);
                        customer.setName(resultSet.getString("cust_name"));
                        customer.setLastName(resultSet.getString("cust_lastname"));

                        setAndFillProjectList (connection, customer);
                    }
                }

            }

        }


        return customer;
    }

    private void setAndFillProjectList(Connection connection, Customer customer) throws SQLException {
        List<Project> projectList = new ArrayList<>();

        String connectionQuery = "SELECT projects_id FROM goit.customers_has_projects WHERE customers_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(connectionQuery)){
            preparedStatement.setLong(1, customer.getId());

            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {

                    Project project = this.projectDAO.read(resultSet.getLong("projects_id"));

                    projectList.add(project);

                }

                customer.setProjectList(projectList);
            }
        }
    }

    @Override
    public List<Customer> readAll() throws SQLException {
        String readAllQuery = "SELECT * FROM goit.customers";
        List<Customer> customerList = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection()){
            try (PreparedStatement preparedStatement = connection.prepareStatement(readAllQuery)){
                try (ResultSet resultSet = preparedStatement.executeQuery()){
                    while (resultSet.next()) {
                        Customer customer = new Customer();
                        customer.setId(resultSet.getLong("cust_id"));
                        customer.setName(resultSet.getString("cust_name"));
                        customer.setLastName(resultSet.getString("cust_lastname"));

                        setAndFillProjectList(connection, customer);
                        customerList.add(customer);
                    }
                }
            }
        }
        return customerList;
    }

    @Override
    public void update(Customer entity) throws SQLException {
        try (Connection connection = connectionManager.getConnection()){
            try {
                connection.setAutoCommit(false);

                String updateQuery = "UPDATE goit.customers SET cust_name = ?, cust_lastname = ? WHERE cust_id = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)){
                    preparedStatement.setString(1, entity.getName());
                    preparedStatement.setString(2, entity.getLastName());
                    preparedStatement.setLong(3, entity.getId());
                }

                String deleteQuery = "DELETE FROM goit.customers_has_projects WHERE customers_id= ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)){
                    preparedStatement.setLong(1, entity.getId());
                    preparedStatement.executeUpdate();

                    writeProjectList(connection, entity);
                }

                connection.commit();
                connection.setAutoCommit(true);
            }catch (Exception e) {
                e.printStackTrace();
                connection.rollback();
                connection.setAutoCommit(true);

            }
        }
    }

    @Override
    public void delete(Customer entity) throws SQLException {
        String deletCustomerFromConnQuery = "DELETE FROM goit.customers_has_projects WHERE customers_id = ?;";
        String deleteQuery = "DELETE FROM goit.customers WHERE cust_id = ?";

        try (Connection connection = this.connectionManager.getConnection()){
            try {
                connection.setAutoCommit(false);
                try (PreparedStatement statment = connection.prepareStatement(deletCustomerFromConnQuery)){
                    statment.setLong(1, entity.getId());
                    statment.executeUpdate();
                }

                try (PreparedStatement statement = connection.prepareStatement(deleteQuery)){
                    statement.setLong(1, entity.getId());
                    statement.executeUpdate();
                }


                connection.commit();
                connection.setAutoCommit(true);
            } catch (Exception e) {
                connection.rollback();
                connection.setAutoCommit(true);
            }

        }

    }

    @Override
    public void setProjectDAO(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }

    @Override
    public ProjectDAO getProjectDAO() {
        return this.projectDAO;
    }
}
