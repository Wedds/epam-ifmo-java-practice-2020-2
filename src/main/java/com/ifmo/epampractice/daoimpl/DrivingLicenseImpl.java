package com.ifmo.epampractice.daoimpl;

import com.ifmo.epampractice.dao.DBConnectorInterface;
import com.ifmo.epampractice.dao.DBConnectorPostgres;
import com.ifmo.epampractice.dao.DrivingLicenseDAO;
import com.ifmo.epampractice.entity.DrivingLicenseEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DrivingLicenseImpl implements DrivingLicenseDAO {

    private static final String GET_ALL_QUERY = "SELECT * FROM driving_license";
    private static final String GET_QUERY = "SELECT * FROM driving_license WHERE ID = ?";
    private static final String UPDATE_QUERY = "UPDATE driving_license SET (Issue_date, Expiration_date, Serial_number) = (?, ?, ?) WHERE ID = ?";
    private static final String DELETE_QUERY = "DELETE FROM driving_license WHERE ID = ?";
    private static final String SAVE_QUERY = "INSERT INTO driving_license (Issue_date, Expiration_date, Serial_number) VALUES (?, ?, ?)";

    private static final Logger LOG = LogManager.getLogger(DrivingLicenseImpl.class);
    private DBConnectorInterface dbConnector = DBConnectorPostgres.getInstance();

    @Override
    public List<DrivingLicenseEntity> getAll() {
        List<DrivingLicenseEntity> drivingLicenseEntityArrayList = new ArrayList<>();

        try (Connection connection = dbConnector.getConnection();
             Statement statement = connection.createStatement(
                     ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
             ResultSet resultSet = statement.executeQuery(GET_ALL_QUERY)) {
            while (resultSet.next()) {
                drivingLicenseEntityArrayList.add(entityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return drivingLicenseEntityArrayList;
    }

    @Override
    public DrivingLicenseEntity get(int id) {
        DBConnectorInterface dbConnector = DBConnectorPostgres.getInstance();
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_QUERY,
                     ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return entityFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return null;
    }

    @Override
    public void update(DrivingLicenseEntity entity) {
        DBConnectorInterface dbConnector = DBConnectorPostgres.getInstance();
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            setStatementFields(statement, entity);
            statement.setInt(4, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e);
        }

    }

    @Override
    public void delete(DrivingLicenseEntity entity) {
        DBConnectorInterface dbConnector = DBConnectorPostgres.getInstance();
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, entity.getId());
            statement.execute();
        } catch (SQLException e) {
            LOG.error(e);
        }
    }

    @Override
    public void save(DrivingLicenseEntity entity) {
        DBConnectorInterface dbConnector = DBConnectorPostgres.getInstance();
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_QUERY)) {
            setStatementFields(statement, entity);
            statement.execute();
        } catch (SQLException e) {
            LOG.error(e);
        }
    }

    private DrivingLicenseEntity entityFromResultSet(ResultSet resultSet) throws SQLException {
        return new DrivingLicenseEntity(
                resultSet.getInt("Id"),
                resultSet.getDate("Issue_date").toLocalDate(),
                resultSet.getDate("Expiration_date").toLocalDate(),
                resultSet.getString("Serial_number")
        );
    }

    private void setStatementFields(PreparedStatement statement, DrivingLicenseEntity entity) throws SQLException {
        statement.setDate(1, Date.valueOf(entity.getIssueDate()));
        statement.setDate(2, Date.valueOf(entity.getExpirationDate()));
        statement.setString(3, entity.getSerialNumber());
    }

}
