package dao.jdbcdaoimpl;

import dao.DeveloperDAO;
import dao.ProjectDAO;
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
 * Created by User on 24.06.2017.
 */
public class ProjectDAOImpl implements ProjectDAO {
    DeveloperDAO developerDAO = new DeveloperDAOImpl();
    ConnectionManager connectionManager =  new DBCPConnectionManager();

    @Override
    public void create(Project entity) throws SQLException {
        String createQuery = "INSERT INTO goit.projects (proj_name, cost) VALUES (?, ?);";
        try (Connection connection = this.connectionManager.getConnection()){
            try {
                connection.setAutoCommit(false);
                try (PreparedStatement preparedStatement = connection.prepareStatement(createQuery)){
                    preparedStatement.setString(1, entity.getProjectName());
                    preparedStatement.setDouble(2, entity.getCost());
                    preparedStatement.executeUpdate();
                }

                List<Developer> developerList = entity.getDeveloperList();
                if (developerList != null) {
                    if (!developerList.isEmpty()) {

                        long lastId;
                        String idQuery = "SELECT proj_id FROM goit.projects ORDER BY proj_id DESC LIMIT 1";

                        try (PreparedStatement preparedStatement = connection.prepareStatement(idQuery)){
                            ResultSet resultSet = preparedStatement.executeQuery();
                            resultSet.next();
                            lastId = resultSet.getLong("proj_id");
                            entity.setId(lastId);
                        }

                        writeDeveloperList (connection, entity);

                    }
                }

                connection.commit();
                connection.setAutoCommit(true);
            } catch (Exception e) {
                //Если произошла ошибка на любом из этапов откатываем все добавления
                e.printStackTrace();
                connection.rollback();
                connection.setAutoCommit(true);
            }

        }

    }

    private void writeDeveloperList (Connection connection, Project project) throws SQLException {
        List<Developer> developerList = project.getDeveloperList();

        List<Developer> generalDevList = this.developerDAO.readAll();

        String projDevConnQuery = "INSERT INTO goit.projects_has_developers VALUES (? , ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(projDevConnQuery)){
            for (Developer developer : developerList) {
                int index = generalDevList.indexOf(developer);
                if (index != -1) {
                    preparedStatement.setLong(1, project.getId());
                    preparedStatement.setLong(2, generalDevList.get(index).getId());
                    preparedStatement.addBatch();
                }
            }
            preparedStatement.executeBatch();
        }
    }

    @Override
    public Project read(Long key) throws SQLException {
        Project project = null;
        String readQuery = "SELECT * FROM goit.projects WHERE proj_id = ?";

        try (Connection connection = connectionManager.getConnection()){
            try (PreparedStatement preparedStatement = connection.prepareStatement(readQuery)){
                preparedStatement.setLong(1, key);

                try (ResultSet resultSet = preparedStatement.executeQuery()){
                    if (resultSet.next()) {
                        project = new Project();
                        project.setId(key);
                        project.setProjectName(resultSet.getString(2));
                        project.setCost(resultSet.getDouble(3));

                        setAndFillDevList (connection, project);
                    }
                }

            }
        }

        return project;
    }

    private void setAndFillDevList (Connection connection, Project project) throws SQLException {
        List<Developer> developerList = new ArrayList<>();

        String projDevConnQuery = "SELECT developers_id FROM goit.projects_has_developers WHERE projects_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(projDevConnQuery)){
            preparedStatement.setLong(1, project.getId());

            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {

                    Developer developer = this.developerDAO.read(resultSet.getLong(1));

                    developerList.add(developer);
                }

                project.setDeveloperList(developerList);
            }
        }
    }

    @Override
    public List<Project> readAll() throws SQLException {
        List<Project> projectList = new ArrayList<>();
        String readAllQuery = "SELECT * FROM goit.projects;";

        try (Connection connection = this.connectionManager.getConnection()){
            try (PreparedStatement preparedStatement = connection.prepareStatement(readAllQuery)){
                try (ResultSet resultSet = preparedStatement.executeQuery()){
                    while (resultSet.next()) {
//                        long begin = System.currentTimeMillis();

                        Project project = new Project();
                        project.setId(resultSet.getLong(1));
                        project.setProjectName(resultSet.getString(2));
                        project.setCost(resultSet.getDouble(3));

                        setAndFillDevList(connection, project);
//                        System.out.println("Time to create project: " + (System.currentTimeMillis() - begin));
                        projectList.add(project);
                    }
                }
            }

        }

        return projectList;
    }

    @Override
    public void update(Project entity) throws SQLException {

        try (Connection connection = connectionManager.getConnection()){
            try {
                connection.setAutoCommit(false);

                String updateQuery = "UPDATE goit.projects SET proj_name = ?, cost = ? WHERE proj_id = ?;";
                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)){
                    preparedStatement.setString(1, entity.getProjectName());
                    preparedStatement.setDouble(2, entity.getCost());
                    preparedStatement.setLong(3, entity.getId());
                    preparedStatement.executeUpdate();
                }

                String deleteConnQuery = "DELETE FROM goit.projects_has_developers WHERE projects_id = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(deleteConnQuery)){
                    preparedStatement.setLong(1, entity.getId());
                    preparedStatement.executeUpdate();

                    writeDeveloperList(connection, entity);
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
    public void delete(Project entity) throws SQLException {
        String deleteProjectQuery = "DELETE FROM goit.projects WHERE proj_id = ?;";
        String deletProjectFromConnTableQuery = "DELETE FROM goit.projects_has_developers WHERE projects_id = ?;";

        try (Connection connection = this.connectionManager.getConnection()){
            try {
                connection.setAutoCommit(false);

                try (PreparedStatement preparedStatement = connection.prepareStatement(deletProjectFromConnTableQuery)){
                    preparedStatement.setLong(1, entity.getId());
                    preparedStatement.executeUpdate();
                }

                try (PreparedStatement preparedStatement = connection.prepareStatement(deleteProjectQuery)){
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

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void setDeveloperDAO(DeveloperDAO developerDAO) {
        this.developerDAO = developerDAO;
    }

    @Override
    public DeveloperDAO getDeveloperDAO() {
        return this.developerDAO;
    }
}
