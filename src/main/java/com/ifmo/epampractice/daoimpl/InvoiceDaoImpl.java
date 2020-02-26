package com.ifmo.epampractice.daoimpl;

import com.ifmo.epampractice.dao.DBConnectorInterface;
import com.ifmo.epampractice.dao.DBConnectorPostgres;
import com.ifmo.epampractice.dao.InvoiceDao;
import com.ifmo.epampractice.entity.InvoiceEntity;
import com.ifmo.epampractice.enums.InvoiceStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class InvoiceDaoImpl implements InvoiceDao {
    private static final String GET_ALL_QUERY = "SELECT * FROM INVOICE";
    private static final String GET_QUERY = "SELECT * FROM INVOICE WHERE ID = ?";
    private static final String SAVE_QUERY = "INSERT INTO INVOICE (ORDER_ID, ISSUE_DATE, " +
            "PAYMENT_DATE, TOTAL_PRICE, STATUS) VALUES ( ?, ?, ?, ?, ?::e_status_invoice)";
    private static final String UPDATE_QUERY = "UPDATE INVOICE SET (ORDER_ID, ISSUE_DATE, " +
            "PAYMENT_DATE, TOTAL_PRICE, STATUS) = (?, ?, ?, ?, ?::e_status_invoice) WHERE ID = ?";
    private static final String DELETE_QUERY = "DELETE FROM INVOICE WHERE ID = ?";

    private static final Logger LOG = LogManager.getLogger(InvoiceDaoImpl.class);
    private DBConnectorInterface dbConnector = DBConnectorPostgres.getInstance();

    @Override
    public List<InvoiceEntity> getAll() {
        List<InvoiceEntity> invoices = new ArrayList<>();
        try (Connection connection = dbConnector.getConnection();
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                     ResultSet.CONCUR_READ_ONLY);
             ResultSet resultSet = statement.executeQuery(GET_ALL_QUERY)) {
            while (resultSet.next()) {
                InvoiceEntity currentInvoice = parseRow(resultSet);
                invoices.add(currentInvoice);
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return invoices;
    }

    @Override
    public InvoiceEntity get(int id) {
        InvoiceEntity invoice = null;
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_QUERY,
                     ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet resultSet = statement.executeQuery()) {
            statement.setInt(1, id);
            resultSet.first();
            invoice = parseRow(resultSet);
        } catch (SQLException e) {
            LOG.error(e);
        }
        return invoice;
    }

    @Override
    public void update(InvoiceEntity invoice) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY,
                     ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            setInvoiceFieldsToStatement(invoice, statement);
            statement.setInt(6, invoice.getId());
            statement.execute();
        } catch (SQLException e) {
            LOG.error(e);
        }
    }

    @Override
    public void delete(InvoiceEntity invoice) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY,
                     ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setInt(1, invoice.getId());
            statement.execute();
        } catch (SQLException e) {
            LOG.error(e);
        }
    }

    @Override
    public void save(InvoiceEntity invoice) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_QUERY,
                     ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            setInvoiceFieldsToStatement(invoice, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e);
        }
    }

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
}
