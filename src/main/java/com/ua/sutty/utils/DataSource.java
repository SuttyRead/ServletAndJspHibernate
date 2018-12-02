package com.ua.sutty.utils;

import org.apache.commons.dbcp2.BasicDataSource;

import java.util.ResourceBundle;

public class DataSource {
    private BasicDataSource basicDataSource;
    private BasicDataSource basicDataSourceTest;

    {
        basicDataSource = new BasicDataSource();
        basicDataSourceTest = new BasicDataSource();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("h2");
        ResourceBundle resourceBundleTest = ResourceBundle.getBundle("test");
        try {

            basicDataSource.setUrl(resourceBundle.getString("jdbc.url"));
            basicDataSourceTest.setUrl(resourceBundleTest.getString("jdbc.url"));
            basicDataSource.setUsername(resourceBundle.getString("jdbc.username"));
            basicDataSourceTest.setUsername(resourceBundleTest.getString("jdbc.username"));
            basicDataSource.setPassword(resourceBundle.getString("jdbc.password"));
            basicDataSourceTest.setPassword(resourceBundleTest.getString("jdbc.password"));
            Class.forName(resourceBundle.getString("jdbc.driver")).newInstance();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public DataSource() {
    }

    public DataSource(BasicDataSource basicDataSource, BasicDataSource basicDataSourceTest) {
        this.basicDataSource = basicDataSource;
        this.basicDataSourceTest = basicDataSourceTest;
    }

    public BasicDataSource getBasicDataSource() {
        return basicDataSource;
    }

    public void setBasicDataSource(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }

    public BasicDataSource getBasicDataSourceTest() {
        return basicDataSourceTest;
    }

    public void setBasicDataSourceTest(BasicDataSource basicDataSourceTest) {
        this.basicDataSourceTest = basicDataSourceTest;
    }
}
