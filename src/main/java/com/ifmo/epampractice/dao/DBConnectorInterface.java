package com.ifmo.epampractice.dao;

import java.sql.Connection;

public interface DBConnectorInterface {
    Connection getConnection();
}
