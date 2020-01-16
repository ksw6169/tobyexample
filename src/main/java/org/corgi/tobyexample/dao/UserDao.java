package org.corgi.tobyexample.dao;

import org.corgi.tobyexample.domain.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserDao {

    private DataSource dataSource;


    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public void add(User user) throws SQLException {

        Connection c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) values(?, ?, ?)");

        ps.setString(1, user.getId());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getName());

        ps.executeUpdate();

        ps.close();
        c.close();
    }


    public User get(String id) throws SQLException {

        Connection c = dataSource.getConnection();

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


    public int getCount() throws SQLException {

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = dataSource.getConnection();

            ps = c.prepareStatement(
                    "select count(*) from users");

            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }

            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                }
            }
        }

        return rs.getInt(1);
    }


    public void deleteAll() throws SQLException {

        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = dataSource.getConnection();

            StatementStrategy strategy = new DeleteAllStatement();
            ps = strategy.makePreparedStatement(c);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {

                }
            }

            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    protected abstract PreparedStatement makeStatement(Connection c) throws SQLException;
}
