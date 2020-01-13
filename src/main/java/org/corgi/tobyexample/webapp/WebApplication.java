package org.corgi.tobyexample.webapp;

import org.corgi.tobyexample.dao.UserDao;
import org.corgi.tobyexample.domain.User;

import java.sql.SQLException;

public class WebApplication {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserDao userDao = new UserDao();

        User user1 = new User();
        user1.setId("testId");
        user1.setName("corgi");
        user1.setPassword("testPassword");

        userDao.add(user1);

        System.out.println(user1.getId() + " 등록 성공");

        User user2 = userDao.get(user1.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());
        System.out.println(user2.getId() + " 조회 성공");
    }
}
