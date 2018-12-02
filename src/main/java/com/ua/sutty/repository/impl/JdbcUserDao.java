package com.ua.sutty.repository.impl;

import com.ua.sutty.domain.User;
import com.ua.sutty.repository.AbstractJdbcDao;
import com.ua.sutty.repository.UserDao;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao extends AbstractJdbcDao implements UserDao {

    private final Logger LOGGER = LoggerFactory
        .getLogger(JdbcUserDao.class.getName());

    private static final String INSERT_USER = String.format("INSERT INTO user(%s, %s, %s, %s, %s, %s, %s)" +
        " VALUES (?, ?, ?, ?, ?, ?, ?);", User.LOGIN, User.PASSWORD, User.EMAIL, User.FIRST_NAME, User.LAST_NAME, User.BIRTHDAY, User.ROLE_ID);

    private static final String GET_ALL_USERS = "SELECT * FROM user";

    private static final String DELETE_ADMINISTRATOR_BY_ID = String.format("DELETE FROM user WHERE %s = ?;", User.ID);

    private static final String GET_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?";

    private static final String GET_USER_BY_ID = "SELECT * FROM user WHERE id = ?";

    private static final String GET_USER_BY_EMAIL = "SELECT * FROM user WHERE email = ?";

    private static final String UPDATE_USER = String.format("UPDATE user SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ? " +
        "WHERE id = ?", User.LOGIN, User.PASSWORD, User.EMAIL, User.FIRST_NAME, User.LAST_NAME, User.BIRTHDAY, User.ROLE_ID);

    public JdbcUserDao() {
        LOGGER.trace("Call constructor without param");
    }

    public JdbcUserDao(BasicDataSource basicDataSource) {
        super(basicDataSource);
        LOGGER.trace("Call constructor with basicDataSource");
    }

    @Override
    public void create(User user) {
        if (user == null) {
            LOGGER.error("User == null", new NullPointerException());
            throw new NullPointerException();
        }
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = createConnection();
            pst = connection.prepareStatement(INSERT_USER);
            pst.setString(1, user.getLogin());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getFirstName());
            pst.setString(5, user.getLastName());
            pst.setDate(6, user.getBirthday());
            pst.setLong(7, user.getRoleId());
            pst.execute();
            connection.commit();
            LOGGER.trace("Add user");
        } catch (SQLException e) {
            LOGGER.error("Error in time add user", e);
            rollBackTransactional(connection);
            throw new RuntimeException();
        } finally {
            closePreparedStatement(pst);
            closeConnection(connection);

        }
    }

    @Override
    public void update(User user) {
        if (user == null) {
            LOGGER.error("User == null", new NullPointerException());
            throw new NullPointerException();
        }
        Connection connection = null;
        PreparedStatement pst = null;
        try {
            connection = createConnection();
            pst = connection.prepareStatement(UPDATE_USER);
            pst.setString(1, user.getLogin());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getFirstName());
            pst.setString(5, user.getLastName());
            pst.setDate(6, user.getBirthday());
            pst.setLong(7, user.getRoleId());
            pst.setLong(8, user.getId());
            pst.execute();
            connection.commit();
            LOGGER.trace("Update user");
        } catch (SQLException e) {
            LOGGER.error("Error in time update user", e);
            rollBackTransactional(connection);
            throw new RuntimeException(e);
        } finally {
            closePreparedStatement(pst);
            closeConnection(connection);
        }
    }

    @Override
    public void remove(User user) {
        if (user == null) {
            LOGGER.error("User == null", new NullPointerException());
            throw new NullPointerException();
        }
        if (user.getId() == null) {
            LOGGER.error("User.id == null", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
        Connection connection =null;
        PreparedStatement pst = null;
        try {
            connection = createConnection();
            pst = connection.prepareStatement(DELETE_ADMINISTRATOR_BY_ID);
            pst.setLong(1, user.getId());
            int result = pst.executeUpdate();
            if (result == 0) {
                LOGGER.error("Request failed");
                connection.rollback();
                throw new IllegalArgumentException();
            }
            connection.commit();
            LOGGER.trace("Remove User");
        } catch (SQLException e) {
            LOGGER.error("Error in time execute query", e);
            rollBackTransactional(connection);
            throw new RuntimeException(e);
        } finally {
            closePreparedStatement(pst);
            closeConnection(connection);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection = createConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(GET_ALL_USERS);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong(User.ID));
                user.setLogin(rs.getString(User.LOGIN));
                user.setPassword(rs.getString(User.PASSWORD));
                user.setEmail(rs.getString(User.EMAIL));
                user.setFirstName(rs.getString(User.FIRST_NAME));
                user.setLastName(rs.getString(User.LAST_NAME));
                user.setBirthday(rs.getDate(User.BIRTHDAY));
                user.setRoleId(rs.getLong(User.ROLE_ID));
                users.add(user);
            }
            connection.commit();
            LOGGER.trace("Call method FindAll");
        } catch (SQLException e) {
            LOGGER.error("Error in time execute query", e);
            rollBackTransactional(connection);
            throw new RuntimeException(e);
        } finally {
            closeResultSet(rs);
            closeStatement(stmt);
            closeConnection(connection);
        }
        return users;
    }

    @Override
    public User findByLogin(String login) {
        if (login == null) {
            LOGGER.error("Login == null", new NullPointerException());
            throw new NullPointerException();
        }
        User user = new User();
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            connection = createConnection();
            pst = connection.prepareStatement(GET_USER_BY_LOGIN);
            pst.setString(1, login);
            rs = pst.executeQuery();
            while (rs.next()) {
                user.setId(rs.getLong(User.ID));
                user.setLogin(rs.getString(User.LOGIN));
                user.setPassword(rs.getString(User.PASSWORD));
                user.setEmail(rs.getString(User.EMAIL));
                user.setFirstName(rs.getString(User.FIRST_NAME));
                user.setLastName(rs.getString(User.LAST_NAME));
                user.setBirthday(rs.getDate(User.BIRTHDAY));
                user.setRoleId(rs.getLong(User.ROLE_ID));
            }
            connection.commit();
            LOGGER.trace("Find user by login");
        } catch (SQLException e) {
            LOGGER.error("Error in time execute query", e);
            rollBackTransactional(connection);
            throw new RuntimeException(e);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pst);
            closeConnection(connection);
        }
        return user;
    }

    @Override
    public User findById(Long id) {
        if (id == null) {
            LOGGER.error("Login == null", new NullPointerException());
            throw new NullPointerException();
        }
        User user = new User();
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            connection = createConnection();
            pst = connection.prepareStatement(GET_USER_BY_ID);
            pst.setLong(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                user.setId(rs.getLong(User.ID));
                user.setLogin(rs.getString(User.LOGIN));
                user.setPassword(rs.getString(User.PASSWORD));
                user.setEmail(rs.getString(User.EMAIL));
                user.setFirstName(rs.getString(User.FIRST_NAME));
                user.setLastName(rs.getString(User.LAST_NAME));
                user.setBirthday(rs.getDate(User.BIRTHDAY));
                user.setRoleId(rs.getLong(User.ROLE_ID));
            }
            connection.commit();
            LOGGER.trace("Find user by id");
        } catch (SQLException e) {
            LOGGER.error("Error in time execute query", e);
            rollBackTransactional(connection);
            throw new RuntimeException(e);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pst);
            closeConnection(connection);
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        if (email == null) {
            LOGGER.error("Email == null", new NullPointerException());
            throw new NullPointerException();
        }
        User user = new User();
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            connection = createConnection();
            pst = connection.prepareStatement(GET_USER_BY_EMAIL);
            pst.setString(1, email);
            rs = pst.executeQuery();
            while (rs.next()) {
                user.setId(rs.getLong(User.ID));
                user.setLogin(rs.getString(User.LOGIN));
                user.setPassword(rs.getString(User.PASSWORD));
                user.setEmail(rs.getString(User.EMAIL));
                user.setFirstName(rs.getString(User.FIRST_NAME));
                user.setLastName(rs.getString(User.LAST_NAME));
                user.setBirthday(rs.getDate(User.BIRTHDAY));
                user.setRoleId(rs.getLong(User.ROLE_ID));
            }
            connection.commit();
            LOGGER.trace("Find user by email");
        } catch (SQLException e) {
            LOGGER.error("Error in time execute query", e);
            rollBackTransactional(connection);
            throw new RuntimeException(e);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pst);
            closeConnection(connection);
        }
        return user;
    }

    private synchronized void rollBackTransactional(Connection connection) {
        try {
            LOGGER.trace("Roll back");
            connection.rollback();
        } catch (SQLException e1) {
            LOGGER.error("Error in time call rollback", e1);
            throw new RuntimeException(e1);
        }
    }

}
