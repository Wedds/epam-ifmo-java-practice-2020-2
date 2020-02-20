package com.ifmo.epampractice.daoImpl;

import com.ifmo.epampractice.dao.DBConnectorInterface;
import com.ifmo.epampractice.dao.DBConnectorPostgres;
import com.ifmo.epampractice.dao.InvoiceDao;
import com.ifmo.epampractice.entity.InvoiceEntity;
import com.ifmo.epampractice.enums.InvoiceStatus;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvoiceDaoImpl implements InvoiceDao {
    private static final String GET_ALL_QUERY = "SELECT * FROM INVOICE";
    private static final String GET_QUERY = "SELECT * FROM INVOICE WHERE ID = ?";
    private static final String SAVE_QUERY = "INSERT INTO INVOICE (ORDER_ID, ISSUE_DATE, " +
            "PAYMENT_DATE, TOTAL_PRICE, STATUS) VALUES ( ?, ?, ?, ?, ?::e_status_invoice)";
    private static final String UPDATE_QUERY = "UPDATE INVOICE SET (ORDER_ID, ISSUE_DATE, " +
            "PAYMENT_DATE, TOTAL_PRICE, STATUS) = (?, ?, ?, ?, ?::e_status_invoice) WHERE ID = ?";
    private static final String DELETE_QUERY = "DELETE FROM INVOICE WHERE ID = ?";

    private InvoiceEntity parseRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int orderId = resultSet.getInt("order_id");
        Date issueDate = resultSet.getDate("issue_date");
        Date paymentDate = resultSet.getDate("payment_date");
        BigDecimal totalPrice = resultSet.getBigDecimal("total_price");
        String statusString = resultSet.getString("status").toUpperCase();
        InvoiceStatus status = InvoiceStatus.valueOf(statusString);

        return new InvoiceEntity(id, orderId, issueDate, paymentDate, totalPrice, status);
    }

    private void setInvoiceFieldsToStatement(InvoiceEntity invoice, PreparedStatement statement)
            throws SQLException {
        statement.setInt(1, invoice.getOrderId());
        statement.setDate(2, new java.sql.Date(invoice.getIssueDate().getTime()));
        statement.setDate(3, new java.sql.Date(invoice.getPaymentDate().getTime()));
        statement.setBigDecimal(4, invoice.getTotalPrice());
        statement.setString(5, invoice.getStatus().name().toLowerCase());
    }

    @Override
    public List<InvoiceEntity> getAll() {
        List<InvoiceEntity> invoices = new ArrayList<>();
        DBConnectorInterface dbConnector = DBConnectorPostgres.getInstance();
        try (Connection connection = dbConnector.getConnection()) {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(GET_ALL_QUERY);
            while (resultSet.next()) {
                InvoiceEntity currentInvoice = parseRow(resultSet);
                invoices.add(currentInvoice);
            }
            return invoices;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public InvoiceEntity get(int id) {
        InvoiceEntity invoice;
        DBConnectorInterface dbConnector = DBConnectorPostgres.getInstance();
        try (Connection connection = dbConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_QUERY,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.first();
            invoice = parseRow(resultSet);
            return invoice;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(InvoiceEntity invoice) {
        DBConnectorInterface dbConnector = DBConnectorPostgres.getInstance();
        try (Connection connection = dbConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            setInvoiceFieldsToStatement(invoice, statement);
            statement.setInt(6, invoice.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(InvoiceEntity invoice) {
        DBConnectorInterface dbConnector = DBConnectorPostgres.getInstance();
        try (Connection connection = dbConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.setInt(1, invoice.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(InvoiceEntity invoice) {
        DBConnectorInterface dbConnector = DBConnectorPostgres.getInstance();
        try (Connection connection = dbConnector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SAVE_QUERY,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            setInvoiceFieldsToStatement(invoice, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
