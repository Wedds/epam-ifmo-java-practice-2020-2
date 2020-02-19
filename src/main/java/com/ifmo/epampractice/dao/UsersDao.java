package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.UsersEntity;
import com.ifmo.epampractice.enums.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDao implements BasicDaoInterface<UsersEntity> {

    @Override
    public List<UsersEntity> getAll() {
        String QUERY = "SELECT * FROM Users";

        List<UsersEntity> users = new ArrayList<>();

        DBConnectorInterface dbConnector = DBConnectorPostgres.getInstance();
        try (Connection connection = dbConnector.getConnection()) {
            Statement statement = connection.createStatement(
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                users.add(entityFromResultSet(resultSet));
            }
            return users;
        }
        catch (SQLException e) {
            e.printStackTrace();
            //TODO: use logger when it will be set up.
            //TODO: maybe throw exception instead? (need to update interface for this)
            return users;
        }
    }

    @Override
    public UsersEntity get(int id) {
        String QUERY = "SELECT * FROM Users WHERE ID = ?";

        DBConnectorInterface dbConnector = DBConnectorPostgres.getInstance();
        try (Connection connection = dbConnector.getConnection()) {
            Statement statement = connection.createStatement(
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(QUERY);
            return entityFromResultSet(resultSet);
        }
        catch (SQLException e) {
            e.printStackTrace();
            //TODO: use logger when it will be set up.
            //TODO: maybe throw exception instead? (need to update interface for this)
            return null;
        }
    }

    @Override
    public void update(UsersEntity entity) {
        String QUERY = "UPDATE Users SET (Email, Password_hash, Role, Name, Birth_date, " +
                "Signup_date, Pass_id, Driving_license_id, Contact_phone, Address, Is_blocked, " +
                "Reputation) = (?, ?, ?::e_role_users, ?, ?, ?, ?, ?, ?, ?, ?, ?) WHERE ID = ?";

        DBConnectorInterface dbConnector = DBConnectorPostgres.getInstance();
        try (Connection connection = dbConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(QUERY);
            setStatementFields(statement, entity);
            statement.setInt(13, entity.getId());
            statement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
            //TODO: use logger when it will be set up.
            //TODO: maybe throw exception? (need to update interface for this)
        }
    }

    @Override
    public void delete(UsersEntity entity) {
        String QUERY = "DELETE FROM Users WHERE ID = ?";

        DBConnectorInterface dbConnector = DBConnectorPostgres.getInstance();
        try (Connection connection = dbConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(QUERY);
            statement.setInt(1, entity.getId());
            statement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
            //TODO: use logger when it will be set up.
            //TODO: maybe throw exception? (need to update interface for this)
        }
    }

    @Override
    public void save(UsersEntity entity) {
        String QUERY = "INSERT INTO Users (Email, Password_hash, Role, Name, Birth_date, " +
                "Signup_date, Pass_id, Driving_license_id, Contact_phone, Address, Is_blocked, " +
                "Reputation) VALUES (?, ?, ?::e_role_users, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        DBConnectorInterface dbConnector = DBConnectorPostgres.getInstance();
        try (Connection connection = dbConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(QUERY);
            setStatementFields(statement, entity);
            statement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
            //TODO: use logger when it will be set up.
            //TODO: maybe throw exception? (need to update interface for this)
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
