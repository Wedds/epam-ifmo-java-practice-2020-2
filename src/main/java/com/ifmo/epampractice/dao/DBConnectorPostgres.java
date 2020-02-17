package com.ifmo.epampractice.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.postgresql.ds.PGSimpleDataSource;

public class DBConnectorPostgres implements DBConnectorInterface {
    private static DBConnectorPostgres instance;

    private final String PROPERTIES_URL = "/properties/dbPostgres.properties";
    private Properties properties = new Properties();
    private PGSimpleDataSource dataSource = new PGSimpleDataSource();

    private DBConnectorPostgres() {
        setProperties();
    }

    private void setProperties() {
        try {
            properties.load(this.getClass().getResourceAsStream(PROPERTIES_URL));
            dataSource.setUrl(properties.getProperty("URL"));
            dataSource.setUser(properties.getProperty("USERNAME"));
            dataSource.setPassword(properties.getProperty("PASSWORD"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static synchronized DBConnectorPostgres getInstance() {
        if (instance == null) {
            instance = new DBConnectorPostgres();
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
