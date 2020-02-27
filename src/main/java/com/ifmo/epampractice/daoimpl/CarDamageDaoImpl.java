package com.ifmo.epampractice.daoimpl;

import com.ifmo.epampractice.dao.CarDamageDao;
import com.ifmo.epampractice.dao.DBConnectorPostgres;
import com.ifmo.epampractice.entity.CarDamageEntity;
import com.ifmo.epampractice.enums.DamageStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDamageDaoImpl implements CarDamageDao {
    private static final String GET_ALL_QUERY = "SELECT * FROM car_damage";
    private static final String GET_QUERY = "SELECT * FROM car_damage WHERE id = ?";
    private static final String GET_BY_ORDER_ID_QUERY = "SELECT * FROM car_damage WHERE order_id = ?";
    private static final String UPDATE_QUERY = "UPDATE car_damage SET order_id = ?, description = ?, " +
            "accident_date = ?, severity = ?, repair_cost = ?, status = ?::e_status_damage" +
            " WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM car_damage WHERE id = ?";
    private static final String SAVE_QUERY = "INSERT INTO car_damage (order_id, description, accident_date, " +
            "severity, repair_cost, status) VALUES (?, ?, ?, ?, ?, ?::e_status_damage)";

    private static final Logger LOGGER = LogManager.getLogger(CarDamageDaoImpl.class);

    private DBConnectorPostgres dbConnector = DBConnectorPostgres.getInstance();

    @Override
    public List<CarDamageEntity> getAll() {
        List<CarDamageEntity> carDamageEntityList = new ArrayList<>();

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                carDamageEntityList.add(entityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return carDamageEntityList;
    }

    @Override
    public CarDamageEntity get(int id) {
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
    public CarDamageEntity getByOrderId(int orderId) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ORDER_ID_QUERY)
        ) {
            statement.setInt(1, orderId);
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
    public void update(CarDamageEntity entity) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)
        ) {
            setStatementFields(statement, entity);
            statement.setInt(7, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(CarDamageEntity entity) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)
        ) {
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void save(CarDamageEntity entity) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_QUERY)
        ) {
            setStatementFields(statement, entity);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    private CarDamageEntity entityFromResultSet(ResultSet resultSet) throws SQLException {
        return new CarDamageEntity(
                resultSet.getInt("id"),
                resultSet.getInt("order_id"),
                resultSet.getString("description"),
                resultSet.getDate("accident_date").toLocalDate(),
                resultSet.getInt("severity"),
                resultSet.getBigDecimal("repair_cost"),
                DamageStatus.valueOf(resultSet.getString("status")
                        .toUpperCase().replace(' ', '_'))
        );
    }

    private void setStatementFields(PreparedStatement statement, CarDamageEntity entity)
            throws SQLException {
        statement.setInt(1, entity.getOrderId());
        statement.setString(2, entity.getDescription());
        statement.setDate(3, Date.valueOf(entity.getAccidentDate()));
        statement.setInt(4, entity.getSeverity());
        statement.setBigDecimal(5, entity.getRepairCost());
        statement.setString(6, entity.getStatus().name()
                .toLowerCase().replace('_', ' '));
    }
}
