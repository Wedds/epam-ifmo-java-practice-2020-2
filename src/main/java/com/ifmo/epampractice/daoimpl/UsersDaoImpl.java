package com.ifmo.epampractice.daoimpl;

import com.ifmo.epampractice.dao.DBConnectorInterface;
import com.ifmo.epampractice.dao.DBConnectorPostgres;
import com.ifmo.epampractice.dao.UsersDao;
import com.ifmo.epampractice.entity.UsersEntity;
import com.ifmo.epampractice.enums.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoImpl implements UsersDao {

    private static final String GET_ALL_QUERY = "SELECT * FROM Users";
    private static final String GET_QUERY = "SELECT * FROM Users WHERE ID = ?";
    private static final String UPDATE_QUERY = "UPDATE Users SET (Email, Password_hash, Role, Name, Birth_date, " +
            "Signup_date, Pass_id, Driving_license_id, Contact_phone, Address, Is_blocked, " +
            "Reputation) = (?, ?, ?::e_role_users, ?, ?, ?, ?, ?, ?, ?, ?, ?) WHERE ID = ?";
    private static final String DELETE_QUERY = "DELETE FROM Users WHERE ID = ?";
    private static final String INSERT_QUERY = "INSERT INTO Users (Email, Password_hash, Role, Name, Birth_date, " +
            "Signup_date, Pass_id, Driving_license_id, Contact_phone, Address, Is_blocked, " +
            "Reputation) VALUES (?, ?, ?::e_role_users, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final Logger LOGGER = LogManager.getLogger(UsersDaoImpl.class);

    private DBConnectorInterface dbConnector = DBConnectorPostgres.getInstance();

    @Override
    public List<UsersEntity> getAll() {
        List<UsersEntity> users = new ArrayList<>();

        try (Connection connection = dbConnector.getConnection();
             Statement statement = connection.createStatement(
                     ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
             ResultSet resultSet = statement.executeQuery(GET_ALL_QUERY)
        ) {
            while (resultSet.next()) {
                users.add(entityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return users;
    }

    @Override
    public UsersEntity get(int id) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_QUERY,
                     ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return entityFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return null;
    }

    @Override
    public void update(UsersEntity entity) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)
        ) {
            setStatementFields(statement, entity);
            statement.setInt(13, entity.getId());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(UsersEntity entity) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)
        ) {
            statement.setInt(1, entity.getId());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void save(UsersEntity entity) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)
        ) {
            setStatementFields(statement, entity);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    private UsersEntity entityFromResultSet(ResultSet resultSet) throws SQLException {
        return new UsersEntity(
                resultSet.getInt("Id"),
                resultSet.getString("Email"),
                resultSet.getString("Password_hash"),
                UserRole.valueOf(resultSet.getString("Role").toUpperCase()),
                resultSet.getString("Name"),
                resultSet.getDate("Birth_date").toLocalDate(),
                resultSet.getDate("Signup_date").toLocalDate(),
                resultSet.getInt("Pass_id"),
                resultSet.getInt("Driving_license_id"),
                resultSet.getString("Contact_phone"),
                resultSet.getString("Address"),
                resultSet.getBoolean("Is_blocked"),
                resultSet.getBigDecimal("Reputation")
        );
    }

    private void setStatementFields(
            PreparedStatement statement, UsersEntity entity) throws SQLException {
        statement.setString(1, entity.getEmail());
        statement.setString(2, entity.getPasswordHash());
        statement.setString(3, entity.getRole().name().toLowerCase());
        statement.setString(4, entity.getName());
        statement.setDate(5, Date.valueOf(entity.getBirthDate()));
        statement.setDate(6, Date.valueOf(entity.getSignUpDate()));
        statement.setInt(7, entity.getPassId());
        statement.setInt(8, entity.getDrivingLicenseId());
        statement.setString(9, entity.getContactPhone());
        statement.setString(10, entity.getAddress());
        statement.setBoolean(11, entity.isBlocked());
        statement.setBigDecimal(12, entity.getReputation());
    }

}
