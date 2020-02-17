package com.ifmo.epampractice.DAO;

import java.io.IOException;
import java.util.Properties;

import org.postgresql.ds.PGSimpleDataSource;

public class DBConnectorPostgres implements DBConnectorInterface {
    Properties properties = new Properties();
    PGSimpleDataSource dataSource = null;

    public DBConnectorPostgres() {
        try {
            dataSource = new PGSimpleDataSource();
            setProperties("/properties/dbPostgres.properties");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setProperties(String url) throws IOException {
        properties.load(this.getClass().getResourceAsStream(url));
        dataSource.setUrl(properties.getProperty("URL"));
        dataSource.setUser(properties.getProperty("USERNAME"));
        dataSource.setPassword(properties.getProperty("PASSWORD"));

    }

    @Override
    public PGSimpleDataSource getDataSource() {
        return dataSource;
    }
}
