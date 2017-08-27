package dao.jdbcdaoimpl;

import dao.SkillDAO;
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
 * Created by User on 12.06.2017.
 */
public class SkillDAOImpl implements SkillDAO {
    ConnectionManager connectionManager = new DBCPConnectionManager();

    @Override
    public void create(Skill entity) throws SQLException {
        String createQuery = "insert into goit.skills (skill_skill) values (?)";

        try(Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(createQuery)){
                preparedStatement.setString(1, entity.getSkill());
                preparedStatement.executeUpdate();
            }
        }
    }

    @Override
    public Skill read(Long key) throws SQLException {
        Skill skill = null;
        String readQuery = "select * from goit.skills where skill_id=?";
        try (Connection connection = this.connectionManager.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(readQuery)){
                preparedStatement.setString(1, String.valueOf(key));
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next() == false) return null;

                    skill = new Skill();
                    skill.setId(resultSet.getLong(1));
                    skill.setSkill(resultSet.getString(2));
                }
            }
        }
        return skill;
    }

    @Override
    public List<Skill> readAll() throws SQLException {
        List<Skill> skillList = new ArrayList<>();
        String readAllQuery = "select * from goit.skills";
        try (Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(readAllQuery)){
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Skill skill = new Skill();
                        skill.setId(resultSet.getLong(1));
                        skill.setSkill(resultSet.getString(2));
                        skillList.add(skill);
                    }
                }
            }
        }
        return skillList;
    }

    @Override
    public void update(Skill entity) throws SQLException {
        String updateQuery = "update goit.skills set skill_skill=? where skill_id=?";
        try(Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)){
                preparedStatement.setString(1, entity.getSkill());
                preparedStatement.setLong(2, entity.getId());
                preparedStatement.executeUpdate();
            }
        }
    }

    @Override
    public void delete(Skill entity) throws SQLException {
        String deleteQuery = "delete from goit.skills where skill_id=?";
        try(Connection connection = connectionManager.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setLong(1, entity.getId());
                preparedStatement.executeUpdate();
            }
        }

    }

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }
}
