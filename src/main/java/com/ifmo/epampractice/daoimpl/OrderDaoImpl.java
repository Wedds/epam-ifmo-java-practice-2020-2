package com.ifmo.epampractice.daoimpl;

import com.ifmo.epampractice.dao.DBConnectorInterface;
import com.ifmo.epampractice.dao.DBConnectorPostgres;
import com.ifmo.epampractice.dao.OrderDao;
import com.ifmo.epampractice.entity.OrderEntity;
import com.ifmo.epampractice.enums.OrderStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private static final String GET_ALL_QUERY = "SELECT * FROM ORDERS";
    private static final String GET_QUERY = "SELECT * FROM ORDERS WHERE ID = ?";
    private static final String SAVE_QUERY = "INSERT INTO ORDERS (car_id, client_id, admin_id, " +
            "status, rent_start_date, rent_end_date, discount) VALUES (?, ?, ?, ?::e_status_order, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE ORDERS SET (car_id, client_id, admin_id, status, " +
            "rent_start_date, rent_end_date, discount) = (?, ?, ?, ?::e_status_order, ?, ?, ?) WHERE ID = ?";
    private static final String DELETE_QUERY = "DELETE FROM ORDERS WHERE ID = ?";

    private static final Logger log = LogManager.getLogger(OrderDaoImpl.class);
    private DBConnectorInterface dbConnector = DBConnectorPostgres.getInstance();

    @Override
    public List<OrderEntity> getAll() {
        List<OrderEntity> orders = new ArrayList<>();
        try (Connection connection = dbConnector.getConnection();
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                     ResultSet.CONCUR_READ_ONLY);
             ResultSet resultSet = statement.executeQuery(GET_ALL_QUERY)) {
            while (resultSet.next()) {
                OrderEntity currentInvoice = parseRow(resultSet);
                orders.add(currentInvoice);
            }
            return orders;
        } catch (SQLException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrderEntity get(int id) {
        OrderEntity order;
        DBConnectorInterface dbConnector = DBConnectorPostgres.getInstance();
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_QUERY,
                     ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet resultSet = statement.executeQuery()) {
            statement.setInt(1, id);
            resultSet.first();
            order = parseRow(resultSet);
            return order;
        } catch (SQLException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(OrderEntity order) {
        DBConnectorInterface dbConnector = DBConnectorPostgres.getInstance();
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY,
                     ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            setOrderFieldsToStatement(order, statement);
            statement.setInt(8, order.getId());
            System.out.println(statement);
            statement.execute();
        } catch (SQLException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(OrderEntity order) {
        DBConnectorInterface dbConnector = DBConnectorPostgres.getInstance();
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY,
                     ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setInt(1, order.getId());
            statement.execute();
        } catch (SQLException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(OrderEntity order) {
        DBConnectorInterface dbConnector = DBConnectorPostgres.getInstance();
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_QUERY,
                     ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            setOrderFieldsToStatement(order, statement);
            statement.execute();
        } catch (SQLException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    private OrderEntity parseRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int carId = resultSet.getInt("car_id");
        int clientId = resultSet.getInt("client_id");
        int adminId = resultSet.getInt("admin_id");
        String statusString = resultSet.getString("status").toUpperCase().replace(' ', '_');
        OrderStatus status = OrderStatus.valueOf(statusString);
        Date rentStartDate = resultSet.getDate("rent_start_date");
        Date rentEndDate = resultSet.getDate("rent_end_date");
        BigDecimal discount = resultSet.getBigDecimal("discount");

        return new OrderEntity(id, carId, clientId, adminId, status, rentStartDate, rentEndDate, discount);
    }

    private void setOrderFieldsToStatement(OrderEntity order, PreparedStatement statement) throws SQLException {
        statement.setInt(1, order.getCarId());
        statement.setInt(2, order.getClientId());
        statement.setInt(3, order.getAdminId());
        statement.setString(4, order.getStatus().name().toLowerCase());
        statement.setDate(5, new java.sql.Date(order.getRentStartDate().getTime()));
        statement.setDate(6, new java.sql.Date(order.getRentEndDate().getTime()));
        statement.setBigDecimal(7, order.getDiscount());
    }
}
