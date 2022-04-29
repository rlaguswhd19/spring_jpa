package com.hj.spring_jpa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class jdbc {

    static private final String url = "jdbc:mysql://localhost:3306/jpa?serverTimezone=UTC&characterEncoding=UTF-8";
    static private final String username = "root";
    static private final String password = "rlaguswhd21";

    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connection create : " + connection);
            String create = "CREATE TABLE ACCOUNT (id int, username varchar(255), password varchar(255))";
            String insert = "INSERT INTO ACCOUNT VALUES (1, 'erwin', 'pass');";
            try (PreparedStatement statement = connection.prepareStatement(insert)){
                statement.execute();
            }
        }
    }
}
