package dao.jdbcdaoimpl;

import dao.CompanyDAO;
import dao.DeveloperDAO;
import dao.ProjectDAO;
import domain.Company;
import domain.Developer;
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
 * Created by User on 25.07.2017.
 */
public class CompanyDAOImpl implements CompanyDAO {
    DeveloperDAO developerDAO = new DeveloperDAOImpl();
    ProjectDAO projectDAO = new ProjectDAOImpl();
    ConnectionManager connectionManager = new  DBCPConnectionManager ();

    @Override
    public void create(Company entity) throws SQLException {
        String createQuery = "INSERT INTO goit.companies (company_name, company_address) VALUES (?, ?);";
        try (Connection connection = this.connectionManager.getConnection()){
            try {
                connection.setAutoCommit(false);
                try (PreparedStatement preparedStatement = connection.prepareStatement(createQuery)){
                    preparedStatement.setString(1, entity.getName());
                    preparedStatement.setString(2, entity.getAddress());
                    preparedStatement.executeUpdate();
                }


                long lastId;
                String idQuery = "SELECT company_id FROM goit.companies ORDER BY company_id DESC LIMIT 1;";
                try (PreparedStatement preparedStatement = connection.prepareStatement(idQuery)){
                    ResultSet resultSet = preparedStatement.executeQuery();
                    resultSet.next();
                    lastId = resultSet.getLong("company_id");
                    entity.setId(lastId);
                }

                List<Project> projectList = entity.getProjectList();
                if (projectList != null) {
                    if (!projectList.isEmpty()) {
                        writeProjectList (connection, entity);
                    }
                }

                List<Developer> developerList = entity.getDeveloperList();
                if (developerList != null) {
                    if (!developerList.isEmpty()) {
                        writeDeveloperList (connection, entity);
                    }
                }

                connection.commit();
                connection.setAutoCommit(true);
            } catch (Exception e) {
                e.printStackTrace();
                connection.rollback();
                connection.setAutoCommit(true);
            }

        }
    }

    private void writeDeveloperList (Connection connection, Company entity) throws SQLException {
        List<Developer> developerList = entity.getDeveloperList();

        List<Developer> generalDevList = this.developerDAO.readAll();

        String projDevConnQuery = "INSERT INTO goit.companies_has_developers VALUES (? , ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(projDevConnQuery)){
            for (Developer developer : developerList) {
                int index = generalDevList.indexOf(developer);
                if (index != -1) {
                    preparedStatement.setLong(1, entity.getId());
                    preparedStatement.setLong(2, generalDevList.get(index).getId());
                    preparedStatement.addBatch();
                }
            }
            preparedStatement.executeBatch();
        }
    }

    private void writeProjectList (Connection connection, Company company) throws SQLException {
        List<Project> projectList = company.getProjectList();
        List<Project> generalProjectList = this.projectDAO.readAll();

        String connectionQuery = "INSERT INTO goit.companies_has_projects (companies_id, projects_id) VALUES (?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(connectionQuery)){
            for (Project project : projectList) {
                int index = generalProjectList.indexOf(project);
                if (index != -1) {
                    preparedStatement.setLong(1, company.getId());
                    preparedStatement.setLong(2, generalProjectList.get(index).getId());
                    preparedStatement.addBatch();
                }
            }

            preparedStatement.executeBatch();
        }
    }

    @Override
    public Company read(Long key) throws SQLException {
        Company company= null;
        String readQuery = "SELECT * FROM goit.companies WHERE company_id = ?;";
        try (Connection connection = connectionManager.getConnection()){
            try (PreparedStatement preparedStatement = connection.prepareStatement(readQuery)){
                preparedStatement.setLong(1, key);

                try (ResultSet resultSet = preparedStatement.executeQuery()){
                    if (resultSet.next()) {
                        company = new Company();
                        company.setId(key);
                        company.setName(resultSet.getString("company_name"));
                        company.setAddress(resultSet.getString("company_address"));

                        setAndFillProjectList (connection, company);
                        setAndFillDevList(connection, company);
                    }
                }
            }
        }
        return company;
    }

    private void setAndFillProjectList(Connection connection, Company company) throws SQLException {
        List<Project> projectList = new ArrayList<>();

        String connectionQuery = "SELECT projects_id FROM goit.companies_has_projects WHERE companies_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(connectionQuery)){
            preparedStatement.setLong(1, company.getId());

            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {

                    Project project = this.projectDAO.read(resultSet.getLong("projects_id"));

                    projectList.add(project);

                }

                company.setProjectList(projectList);
            }
        }
    }

    private void setAndFillDevList (Connection connection, Company company) throws SQLException {
        List<Developer> developerList = new ArrayList<>();

        String projDevConnQuery = "SELECT developers_id FROM goit.companies_has_developers WHERE companies_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(projDevConnQuery)){
            preparedStatement.setLong(1, company.getId());

            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {

                    Developer developer = this.developerDAO.read(resultSet.getLong(1));

                    developerList.add(developer);
                }

                company.setDeveloperList(developerList);
            }
        }
    }

    @Override
    public List<Company> readAll() throws SQLException {
        List<Company> companyList = new ArrayList<>();
        String readAllQuery = "SELECT * FROM goit.companies";
        try (Connection connection = connectionManager.getConnection()){
            try (PreparedStatement preparedStatement = connection.prepareStatement(readAllQuery)){
                try (ResultSet resultSet = preparedStatement.executeQuery()){
                    while (resultSet.next()) {
                        Company company = new Company();
                        company.setId(resultSet.getLong("company_id"));
                        company.setName(resultSet.getString("company_name"));
                        company.setAddress(resultSet.getString("company_address"));
                        setAndFillDevList(connection, company);
                        setAndFillProjectList(connection, company);

                        companyList.add(company);
                    }

                }
            }
        }

        return companyList;
    }

    @Override
    public void update(Company entity) throws SQLException {
        try (Connection connection = connectionManager.getConnection()){
            try {
                connection.setAutoCommit(false);

               String updateQuery = "UPDATE goit.companies SET company_name = ?, company_address = ? WHERE company_id = ?";
               try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)){
                    preparedStatement.setString(1, entity.getName());
                    preparedStatement.setString(2, entity.getAddress());
                    preparedStatement.setLong(3, entity.getId());
                    preparedStatement.executeUpdate();
               }

               String deleteConnQuery = "DELETE FROM goit.companies_has_developers WHERE companies_id = ?;";
                try (PreparedStatement preparedStatement = connection.prepareStatement(deleteConnQuery)){
                    preparedStatement.setLong(1, entity.getId());
                    preparedStatement.executeUpdate();

                    writeDeveloperList(connection, entity);
                }

                deleteConnQuery = "DELETE FROM goit.companies_has_projects WHERE companies_id = ?;";
                try (PreparedStatement preparedStatement = connection.prepareStatement(deleteConnQuery)){
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
    public void delete(Company entity) throws SQLException {
        String deleteQuery = "DELETE FROM goit.companies WHERE company_id = ?;";

        String deleteFromDevConnTableQuery = "DELETE FROM goit.companies_has_developers WHERE companies_id = ?;";
        String deleteFromProjConnTableQuery = "DELETE FROM goit.companies_has_projects WHERE companies_id = ?;";

        try (Connection connection = this.connectionManager.getConnection()){
            try {
                connection.setAutoCommit(false);

                try (PreparedStatement preparedStatement = connection.prepareStatement(deleteFromDevConnTableQuery)){
                    preparedStatement.setLong(1, entity.getId());
                    preparedStatement.executeUpdate();
                }

                try (PreparedStatement preparedStatement = connection.prepareStatement(deleteFromProjConnTableQuery)){
                    preparedStatement.setLong(1, entity.getId());
                    preparedStatement.executeUpdate();
                }

                try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)){
                    preparedStatement.setLong(1, entity.getId());
                    preparedStatement.executeUpdate();
                }


                connection.commit();
                connection.setAutoCommit(true);
            } catch (Exception e) {
                e.printStackTrace();
                connection.rollback();
                connection.setAutoCommit(true);
            }

        }

    }

    @Override
    public void setDeveloperDAO(DeveloperDAO developerDAO) {
        this.developerDAO = developerDAO;
    }

    @Override
    public DeveloperDAO getDeveloperDAO() {
        return this.developerDAO;
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
