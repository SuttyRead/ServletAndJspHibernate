package com.ua.sutty.repository.impl;

import com.ua.sutty.domain.Role;
import com.ua.sutty.domain.User;
import com.ua.sutty.repository.AbstractJdbcDao;
import com.ua.sutty.repository.RoleDao;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcRoleDao extends AbstractJdbcDao implements RoleDao {

    private final Logger LOGGER = LoggerFactory
        .getLogger(JdbcRoleDao.class.getName());

    private static final String INSERT_ROLE = String.format("INSERT INTO role(%s)" +
        " VALUES (?);", Role.NAME);

    private static final String DELETE_ROLE = String.format("DELETE FROM role WHERE %s = ?;", Role.ID);

    private static final String DELETE_USER_WITH_ROLE = String.format("DELETE FROM user WHERE %s = ?;", User.ROLE_ID);

    private static final String GET_ROLE_BY_NAME = "SELECT * FROM role WHERE name = ?";


    private static final String UPDATE_ROLE = String.format("UPDATE role SET %s = ?" +
        "WHERE id = ?", Role.NAME);

    public JdbcRoleDao() {
        LOGGER.trace("Call constructor without param");
    }

    public JdbcRoleDao(BasicDataSource basicDataSource) {
        super(basicDataSource);
        LOGGER.trace("Call constructor with basicDataSource");
    }

    @Override
    public void create(Role role) {
        if (role == null) {
            LOGGER.error("Role == null", new NullPointerException());
            throw new NullPointerException();
        }
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = createConnection();
            pst = connection.prepareStatement(INSERT_ROLE);
            pst.setString(1, role.getName());
            pst.execute();
            connection.commit();
            LOGGER.trace("Add role");
        } catch (SQLException e) {
//            System.out.println("Error");
            LOGGER.error("Error in time add user", e);
            rollBackTransactional(connection);
            throw new RuntimeException(e);
        } finally {
            closePreparedStatement(pst);
            closeConnection(connection);
        }
    }

    @Override
    public void update(Role role) {
        if (role == null) {
            LOGGER.error("Role == null", new NullPointerException());
            throw new NullPointerException();
        }
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = super.createConnection();
            pst = connection.prepareStatement(UPDATE_ROLE);
            pst.setString(1, role.getName());
            pst.setLong(2, role.getId());
            pst.execute();
            connection.commit();
            LOGGER.trace("Update user");
        } catch (SQLException e) {
            LOGGER.error("Error in time update role", e);
            rollBackTransactional(connection);
            throw new RuntimeException(e);
        } finally {
            closePreparedStatement(pst);
            closeConnection(connection);
        }
    }

    @Override
    public void remove(Role role) {
        if (role == null) {
            LOGGER.error("Role == null", new NullPointerException());
            throw new NullPointerException();
        }
        if (role.getId() == null) {
            LOGGER.error("Role.id == null", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = super.createConnection();
            pst = connection.prepareStatement(DELETE_USER_WITH_ROLE);
            pst.setLong(1, role.getId());
            pst.execute();
            pst = connection.prepareStatement(DELETE_ROLE);

            pst.setLong(1, role.getId());
            int result = pst.executeUpdate();
            if (result == 0) {
                LOGGER.error("Request failed");
                connection.rollback();
                throw new IllegalArgumentException();
            }
            connection.commit();
            LOGGER.trace("Remove Role");
        } catch (SQLException e) {
            LOGGER.error("Error in time execute query");
            rollBackTransactional(connection);
            throw new RuntimeException(e);
        } finally {
            closePreparedStatement(pst);
            closeConnection(connection);
        }
    }

    @Override
    public Role findByName(String name) {
        if (name == null) {
            LOGGER.error("Name == null", new NullPointerException());
            throw new NullPointerException();
        }
        Role role = new Role();
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            connection = super.createConnection();
            pst = connection.prepareStatement(GET_ROLE_BY_NAME);
            pst.setString(1, name);
            rs = pst.executeQuery();
            while (rs.next()) {
                role.setId(rs.getLong(Role.ID));
                role.setName(rs.getString(Role.NAME));
            }
            connection.commit();
            LOGGER.trace("Find role by name");
        } catch (SQLException e) {
            LOGGER.error("Error in time execute query", e);
            rollBackTransactional(connection);
            throw new RuntimeException(e);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pst);
            closeConnection(connection);
        }
        return role;
    }

    private synchronized void rollBackTransactional(Connection connection) {
        try {
            LOGGER.trace("Roll back");
            connection.rollback();
        } catch (SQLException e1) {
            LOGGER.error("Error in time call rollback", e1);
            e1.printStackTrace();
        }
    }

}
