package com.ifmo.epampractice.daoimpl;

import com.ifmo.epampractice.dao.DBConnectorPostgres;
import com.ifmo.epampractice.dao.PassportDao;
import com.ifmo.epampractice.entity.PassportEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassportDaoImpl implements PassportDao {
    private static final String GET_ALL_QUERY = "SELECT * FROM passport";
    private static final String GET_QUERY = "SELECT * FROM passport WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE passport SET issue_country = ?, issuer = ?, issue_date = ?, expiration_date = ?, serial_number = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM passport WHERE id = ?";
    private static final String SAVE_QUERY = "INSERT INTO passport (issue_country, issuer, issue_date, expiration_date, serial_number) VALUES (?, ?, ?, ?, ?)";

    private static final Logger LOGGER = LogManager.getLogger(CarDaoImpl.class);

    private DBConnectorPostgres dbConnector = DBConnectorPostgres.getInstance();


    @Override
    public List<PassportEntity> getAll() {
        List<PassportEntity> passportEntityList = new ArrayList<>();

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                passportEntityList.add(entityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return passportEntityList;
    }

    @Override
    public PassportEntity get(int id) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_QUERY);
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
    public void update(PassportEntity passportEntity) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)
        ) {
            setStatementFields(statement, passportEntity);
            statement.setInt(6, passportEntity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(PassportEntity passportEntity) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)
        ) {
            statement.setInt(1, passportEntity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }

    }

    @Override
    public void save(PassportEntity passportEntity) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_QUERY)
        ) {
            setStatementFields(statement, passportEntity);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }

    }

    private PassportEntity entityFromResultSet(ResultSet resultSet) throws SQLException {
        return new PassportEntity(
                resultSet.getInt("id"),
                resultSet.getString("issue_country"),
                resultSet.getString("issuer"),
                resultSet.getDate("issue_date").toLocalDate(),
                resultSet.getDate("expiration_date").toLocalDate(),
                resultSet.getString("serial_number")
        );
    }

    private void setStatementFields(PreparedStatement statement, PassportEntity passportEntity)
            throws SQLException {
        statement.setString(1, passportEntity.getIssueCountry());
        statement.setString(2, passportEntity.getIssuer());
        statement.setDate(3, Date.valueOf(passportEntity.getIssueDate()));
        statement.setDate(4, Date.valueOf(passportEntity.getExpirationDate()));
        statement.setString(5, passportEntity.getSerialNumber());
    }
}
