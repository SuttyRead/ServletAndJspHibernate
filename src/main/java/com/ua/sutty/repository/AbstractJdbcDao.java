package com.ua.sutty.repository;


import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public abstract class AbstractJdbcDao {

    private BasicDataSource basicDataSource;

    private final Logger LOGGER = LoggerFactory
        .getLogger(AbstractJdbcDao.class.getName());

    public AbstractJdbcDao() {
        LOGGER.trace("Call constructor without param");
    }

    public AbstractJdbcDao(BasicDataSource basicDataSource) {
        LOGGER.trace("Call constructor basicDataSource");
        this.basicDataSource = basicDataSource;
    }

    protected synchronized Connection createConnection() {

            Connection connection;
            try {
                connection = basicDataSource.getConnection();
                connection.setAutoCommit(false);
                LOGGER.trace("Get connection");
                return connection;
            } catch (SQLException e) {
                LOGGER.error("Error in time get connection, ", e);
                throw new RuntimeException();
            }

    }

    protected void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                LOGGER.trace("Close connection");
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Error in time close connection", e);
                throw new RuntimeException(e);
            }
        }
    }

    protected void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                LOGGER.trace("Close prepare statement");
                preparedStatement.close();
            } catch (SQLException e) {
                LOGGER.error("Error in time close prepare statement", e);
                throw new RuntimeException(e);
            }
        }
    }

    protected void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                LOGGER.trace("Close statement");
                statement.close();
            } catch (SQLException e) {
                LOGGER.error("Error in time close statement", e);
                throw new RuntimeException(e);
            }
        }
    }

    protected void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                LOGGER.trace("Close result set");
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.error("Error in time close result set", e);
                throw new RuntimeException(e);
            }
        }
    }

}
