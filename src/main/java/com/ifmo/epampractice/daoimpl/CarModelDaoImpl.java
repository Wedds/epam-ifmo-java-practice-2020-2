package com.ifmo.epampractice.daoimpl;

import com.ifmo.epampractice.dao.CarModelDao;
import com.ifmo.epampractice.dao.DBConnectorPostgres;
import com.ifmo.epampractice.entity.CarModelEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarModelDaoImpl implements CarModelDao {
    private static final String GET_ALL_QUERY = "SELECT * FROM car_model";
    private static final String GET_QUERY = "SELECT * FROM car_model WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE car_model SET brand_name = ?, model_name = ?, " +
            "price_per_hour = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM car_model WHERE id = ?";
    private static final String SAVE_QUERY = "INSERT INTO car_model (brand_name, model_name, price_per_hour) " +
            "VALUES (?, ?, ?)";

    private static final Logger LOGGER = LogManager.getLogger(CarModelDaoImpl.class);

    private DBConnectorPostgres dbConnector = DBConnectorPostgres.getInstance();

    @Override
    public List<CarModelEntity> getAll() {
        List<CarModelEntity> carModelEntityList = new ArrayList<>();

        try (Connection connection = dbConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_QUERY)
        ) {
            while (resultSet.next()) {
                carModelEntityList.add(entityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return carModelEntityList;
    }

    @Override
    public CarModelEntity get(int id) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_QUERY)
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
    public void update(CarModelEntity carModelEntity) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)
        ) {
            statement.setString(1, carModelEntity.getBrandName());
            statement.setString(2, carModelEntity.getModelName());
            statement.setBigDecimal(3, carModelEntity.getPricePerHour());
            statement.setInt(4, carModelEntity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(CarModelEntity carModelEntity) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)
        ) {
            statement.setInt(1, carModelEntity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void save(CarModelEntity carModelEntity) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_QUERY)
        ) {
            statement.setString(1, carModelEntity.getBrandName());
            statement.setString(2, carModelEntity.getModelName());
            statement.setBigDecimal(3, carModelEntity.getPricePerHour());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    private CarModelEntity entityFromResultSet(ResultSet resultSet) throws SQLException {
        return new CarModelEntity(
                resultSet.getInt("id"),
                resultSet.getString("brand_name"),
                resultSet.getString("model_name"),
                resultSet.getBigDecimal("price_per_hour")
        );
    }
}
