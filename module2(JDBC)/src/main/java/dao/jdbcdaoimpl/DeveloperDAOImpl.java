package dao.jdbcdaoimpl;

import dao.DeveloperDAO;
import dao.SkillDAO;
import domain.Developer;
import domain.Skill;
import tools.ConnectionManager;
import tools.DBCPConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 17.06.2017.
 */
public class DeveloperDAOImpl implements DeveloperDAO {
    private SkillDAO skillDAO = new SkillDAOImpl();
    private ConnectionManager connectionManager = new DBCPConnectionManager();

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void create(Developer entity) throws SQLException {
        String createQuery = "INSERT INTO goit.developers (dev_name, dev_lastname, dev_email, salary) VALUES (?, ?, ?, ?);";
        try (Connection connection = this.connectionManager.getConnection()) {
            try { //если выпадет исключение, мы откатываем все назад и возвращаем автокомит
                connection.setAutoCommit(false);

                //Добавляем Developer в таблицу developers
                try (PreparedStatement preparedStatement = connection.prepareStatement(createQuery)){
                    preparedStatement.setString(1, entity.getName());
                    preparedStatement.setString(2, entity.getLastName());
                    preparedStatement.setString(3, entity.getEmail());
                    preparedStatement.setDouble(4, entity.getSalary());
                    preparedStatement.executeUpdate();
                }

                //Проверяем есть ли у девелопера лист скилов
                List<Skill> skillList = entity.getSkillList();
                if (skillList != null) {
                    if ( !skillList.isEmpty() ) {

                        //Если скилы есть, читаем id записи последнего девелопера в ДБ
                        long lastId;
                        String lastIdQuery = "SELECT dev_id FROM goit.developers ORDER BY dev_id DESC LIMIT 1;";
                        try (PreparedStatement preparedStatement = connection.prepareStatement(lastIdQuery)){
                            ResultSet resultSet = preparedStatement.executeQuery();
                            resultSet.next();
                            lastId = resultSet.getLong(1);
                            entity.setId(lastId);
                        }

                        writeSkillList (connection, entity);
                    }
                }

                //Если все прошло по феншую, то сохраняем все изменения в БД
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

    private void writeSkillList (Connection connection, Developer developer) throws SQLException {
        //Соединяем id разраба и id навыка в таблице связи developers_has_skills
        List<Skill> skillList = developer.getSkillList();

        //Читаем все навыки, которыми могут обладать девелоперы
        List<Skill> generalSkillList = skillDAO.readAll();
        String connectQuery = "INSERT INTO goit.developers_has_skills VALUES (?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(connectQuery)){
            for (Skill skill : skillList) {
                int index = generalSkillList.indexOf(skill);
                if (index != -1) {
                    preparedStatement.setLong(1, developer.getId());
                    preparedStatement.setLong(2, generalSkillList.get(index).getId());
                    preparedStatement.addBatch();
                }
            }
            preparedStatement.executeBatch();
        }
    }

    @Override
    public Developer read(Long key) throws SQLException {
        String readQuery = "SELECT * FROM goit.developers WHERE dev_id=?";
        Developer developer = new Developer();

        try (Connection connection = this.connectionManager.getConnection()){
            try (PreparedStatement preparedStatement = connection.prepareStatement(readQuery)){
                preparedStatement.setString(1, String.valueOf(key));
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next() == false) return null;

                    developer.setId(resultSet.getLong("dev_id"));
                    developer.setName(resultSet.getString("dev_name"));
                    developer.setLastName(resultSet.getString("dev_lastname"));
                    developer.setEmail(resultSet.getString("dev_email"));
                    developer.setSalary(resultSet.getDouble("salary"));

                    setAndFillSkillList(connection, developer);
                }
            }

        }
        return developer;
    }

    private void setAndFillSkillList(Connection connection, Developer developer) throws SQLException {
//        long begine = System.currentTimeMillis();
        List<Skill> skillList = new ArrayList<>();
        String skillsSelectQuery = "select skills_id from goit.developers_has_skills where developers_id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(skillsSelectQuery)){
            preparedStatement.setLong(1, developer.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {

//                    long begin = System.currentTimeMillis();
                    Skill skill = this.skillDAO.read(resultSet.getLong(1));

//                    System.out.println("Skill Time = " + (System.currentTimeMillis()- begin));
                    skillList.add(skill);
                }
//                System.out.println(begine - System.currentTimeMillis());
                developer.setSkillList(skillList);
            }
        }
    }

    @Override
    public List<Developer> readAll() throws SQLException {
        List<Developer> developerList = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection()){
            String readAllQuery = "SELECT * FROM goit.developers;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(readAllQuery)){
                try (ResultSet resultSet = preparedStatement.executeQuery()){
                    while (resultSet.next()){
                        Developer developer = new Developer();
                        developer.setId(resultSet.getLong("dev_id"));
                        developer.setName(resultSet.getString("dev_name"));
                        developer.setLastName(resultSet.getString("dev_lastname"));
                        developer.setEmail(resultSet.getString("dev_email"));
                        developer.setSalary(resultSet.getDouble("salary"));

                        setAndFillSkillList(connection, developer);
                        developerList.add(developer);
                    }

                }
            }

        }

        return developerList;
    }

    @Override
    public void update(Developer entity) throws SQLException {
        String updDevQuery = "UPDATE goit.developers SET dev_name = ?, dev_lastname = ?, dev_email = ?, salary = ? " +
                "WHERE dev_id = ?";
        try (Connection connection = this.connectionManager.getConnection()){
            try {
                connection.setAutoCommit(false);
                try (PreparedStatement preparedStatement = connection.prepareStatement(updDevQuery)){
                    preparedStatement.setString(1, entity.getName());
                    preparedStatement.setString(2, entity.getLastName());
                    preparedStatement.setString(3, entity.getEmail());
                    preparedStatement.setDouble(4, entity.getSalary());
                    preparedStatement.setLong(5, entity.getId());
                    preparedStatement.executeUpdate();
                }

                String deleteConn = "DELETE FROM goit.developers_has_skills WHERE developers_id = ?";
//                deleteConn = "1" + deleteConn;
                try (PreparedStatement preparedStatement = connection.prepareStatement(deleteConn)){
                    preparedStatement.setLong(1, entity.getId());
                    preparedStatement.executeUpdate();

                    writeSkillList(connection, entity);
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
    public void delete(Developer entity) throws SQLException {
        try (Connection connection = this.connectionManager.getConnection()){
            try {
                connection.setAutoCommit(false);

                String deleteDevFromConnTableQuery = "DELETE FROM goit.developers_has_skills WHERE developers_id = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(deleteDevFromConnTableQuery)){
                    preparedStatement.setLong(1, entity.getId());
                    preparedStatement.executeUpdate();
                }

                String deleteDeveloperQuery = "DELETE FROM goit.developers WHERE dev_id = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(deleteDeveloperQuery)){
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
    public void setSkillDAO(SkillDAO skillDAO) {
        this.skillDAO = skillDAO;
    }

    @Override
    public SkillDAO getSkillDAO() {
        return this.skillDAO;
    }
}
