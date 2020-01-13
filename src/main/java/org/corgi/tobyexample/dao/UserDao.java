package org.corgi.tobyexample.dao;

import org.corgi.tobyexample.domain.User;

import java.sql.*;

public class UserDao {

    private final String DB_USER = "root";
    private final String DB_PASSWORD = "root";


    public void add(User user) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost/tobyexample", "root", "root");

        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) values(?, ?, ?)");

        ps.setString(1, user.getId());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getName());

        ps.executeUpdate();

        ps.close();
        c.close();
    }


    public User get(String id) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");

        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost/tobyexample", "root", "root");

        PreparedStatement ps = c.prepareStatement(
                "select * from users where id = ?");

        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }

}