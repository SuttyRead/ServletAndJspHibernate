package com.ua.sutty.utils;

import org.h2.tools.RunScript;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class LoadProperties {

    public void loadProperties(){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("test");
//        Properties properties = new Properties();
//        try {
////            properties.load(new FileInputStream("test.properties"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        String driver = resourceBundle.getString("jdbc.driver");
        String url = resourceBundle.getString("jdbc.url");
        System.out.println(url);
        String user = resourceBundle.getString("jdbc.username");
        String password = resourceBundle.getString("jdbc.password");
        String schema = resourceBundle.getString("sql.schema");
        try {
            RunScript.execute(url, user, password, schema, Charset.forName("UTF-8"), false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
