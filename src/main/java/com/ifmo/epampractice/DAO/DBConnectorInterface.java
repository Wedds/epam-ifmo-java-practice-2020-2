package com.ifmo.epampractice.DAO;

import javax.sql.DataSource;
import java.io.IOException;

public interface DBConnectorInterface {
    void setProperties(String url) throws IOException;
    DataSource getDataSource();
}
