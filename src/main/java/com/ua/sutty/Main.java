package com.ua.sutty;

import com.ua.sutty.domain.Role;
import com.ua.sutty.repository.impl.JdbcRoleDao;
import com.ua.sutty.repository.impl.JdbcUserDao;
import com.ua.sutty.utils.DataSource;
import com.ua.sutty.utils.LoadProperties;

public class Main {

    public static void main(String[] args) {
        LoadProperties loadProperties = new LoadProperties();
        loadProperties.loadProperties();
        Role role = new Role("MANAGER");
        JdbcRoleDao jdbcRoleDao = new JdbcRoleDao(new DataSource().getBasicDataSourceTest());
        jdbcRoleDao.create(role);
    }

}
