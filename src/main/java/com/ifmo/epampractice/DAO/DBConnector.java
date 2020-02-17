package com.ifmo.epampractice.DAO;

import java.util.Properties;
import org.postgresql.ds.PGSimpleDataSource;

public class DBConnector {
    Properties properties = new Properties();
    PGSimpleDataSource dataSource = null;

    public DBConnector() {
        try {
            properties.load(this.getClass().getResourceAsStream("/properties/db.properties"));
            dataSource = new PGSimpleDataSource();
            dataSource.setUrl(properties.getProperty("URL"));
            dataSource.setUser(properties.getProperty("USERNAME"));
            dataSource.setPassword(properties.getProperty("PASSWORD"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PGSimpleDataSource getDataSource() {
        return dataSource;
    }
}
