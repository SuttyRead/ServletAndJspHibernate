package com.ua.sutty.jdbc.repository.impl;

import com.ua.sutty.domain.Role;
import com.ua.sutty.repository.impl.JdbcRoleDao;
import com.ua.sutty.utils.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.tools.RunScript;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.ResourceBundle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JdbcRoleDaoTest {

    private static IDatabaseTester databaseTester = null;
    private JdbcRoleDao jdbcRoleDao;

    @Before
    public void importDataSet() throws Exception {
        jdbcRoleDao = new JdbcRoleDao(dataSource());
        IDataSet dataSet = readDataSet();
        beforeStart(dataSet);
    }

    private IDataSet readDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(Thread.currentThread().getContextClassLoader()
            .getResourceAsStream("dataset.xml"));
    }

    private void beforeStart(IDataSet dataSet) throws Exception {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("test");
        String driver = resourceBundle.getString("jdbc.driver");
        String url = resourceBundle.getString("jdbc.url");
        String user = resourceBundle.getString("jdbc.username");
        String password = resourceBundle.getString("jdbc.password");
        String schema = resourceBundle.getString("sql.schema");
        RunScript.execute(url, user, password, schema, Charset.forName("UTF-8"), false);
        databaseTester = new JdbcDatabaseTester(driver, url, user, password);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    @Test(expected = NullPointerException.class)
    public void testCreateNull() {
        jdbcRoleDao.create(null);
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateNull() {
        jdbcRoleDao.update(null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveNull() {
        jdbcRoleDao.remove(null);
    }

    @Test(expected = NullPointerException.class)
    public void testFindByNameNull() {
        jdbcRoleDao.findByName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveWhereIdNull() {
        Role role = new Role();
        role.setId(null);
        role.setName("role");
        jdbcRoleDao.remove(role);
    }

    @Test
    public void testCreate() throws Exception {
        Role someRole = new Role(4L, "createRole");
        jdbcRoleDao.create(someRole);
        assertEquals(4, databaseTester.getConnection().createDataSet()
            .getTable("role").getRowCount());
        assertEquals(someRole.getName(), databaseTester.getConnection()
            .createDataSet().getTable("role").getValue(3, "Name"));
    }

    @Test
    public void testUpdate() throws Exception {
        Role role = new Role(3L, "updatedRole");
        jdbcRoleDao.update(role);
        assertEquals(3, databaseTester.getConnection().createDataSet()
            .getTable("role").getRowCount());
        assertEquals("Test update user ", databaseTester.getConnection()
            .createDataSet().getTable("role")
            .getValue(2, "name"), role.getName());
    }

    @Test
    public void testRemove() throws Exception {
        Role role = new Role(1L, "removeRole");
        jdbcRoleDao.remove(role);
        assertEquals("Size should be 2", 2, databaseTester.getConnection()
            .createDataSet().getTable("role").getRowCount());
    }

    @Test
    public void testFindByName() throws Exception {
        String roleName = String.valueOf(databaseTester.getConnection().createDataSet().
            getTable("role").getValue(2, "name"));
        Role role = jdbcRoleDao.findByName(roleName);
        assertNotNull(role);
    }

    private BasicDataSource dataSource() {
        return new DataSource().getBasicDataSourceTest();
    }

}
