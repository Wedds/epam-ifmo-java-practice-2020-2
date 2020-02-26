package com.ifmo.epampractice.daoimpl;

import com.ifmo.epampractice.dao.CarDao;
import com.ifmo.epampractice.dao.DBConnectorPostgres;
import com.ifmo.epampractice.entity.CarEntity;
import com.ifmo.epampractice.enums.CarStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements CarDao {
    private static final String GET_ALL_QUERY = "SELECT * FROM car";
    private static final String GET_QUERY = "SELECT * FROM car WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE car SET model_id = ?, color = ?, " +
            "reg_number = ?, vin_number = ?, status = ?::e_status_car WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM car WHERE id = ?";
    private static final String SAVE_QUERY = "INSERT INTO car (model_id, color, reg_number, " +
            "vin_number, status) VALUES (?, ?, ?, ?, ?::e_status_car)";

    private static final Logger LOGGER = LogManager.getLogger(CarDaoImpl.class);

    private DBConnectorPostgres dbConnector = DBConnectorPostgres.getInstance();

    @Override
    public List<CarEntity> getAll() {
        List<CarEntity> carEntityList = new ArrayList<>();

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                carEntityList.add(entityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return carEntityList;
    }

    @Override
    public CarEntity get(int id) {
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
    public void update(CarEntity carEntity) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)
        ) {
            setStatementFields(statement, carEntity);
            statement.setInt(6, carEntity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(CarEntity carEntity) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)
        ) {
            statement.setInt(1, carEntity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void save(CarEntity carEntity) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_QUERY)
        ) {
            setStatementFields(statement, carEntity);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    private CarEntity entityFromResultSet(ResultSet resultSet) throws SQLException {
        return new CarEntity(
                resultSet.getInt("id"),
                resultSet.getInt("model_id"),
                resultSet.getString("color"),
                resultSet.getString("reg_number"),
                resultSet.getString("vin_number"),
                CarStatus.valueOf(resultSet.getString("status").toUpperCase())
        );
    }

    private void setStatementFields(PreparedStatement statement, CarEntity carEntity)
            throws SQLException {
        statement.setInt(1, carEntity.getModelId());
        statement.setString(2, carEntity.getColor());
        statement.setString(3, carEntity.getRegNumber());
        statement.setString(4, carEntity.getVin());
        statement.setString(5, carEntity.getStatus().name().toLowerCase());
    }
}

