package org.corgi.tobyexample.webapp;

import org.corgi.tobyexample.dao.DaoFactory;
import org.corgi.tobyexample.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class WebApplication {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        /**
         * DaoFactory를 설정 정보로 사용하는 Application Context 생성
         * ApplicationContext를 구현한 클래스는 여러 가지가 있는데, DaoFactory처럼
         * Configuration annotation이 붙은 자바 코드를 설정 정보로 사용하려면
         * ApplicationConfigApplicationContext를 이용하면 된다.
         */
        ApplicationContext context =
                new AnnotationConfigApplicationContext(DaoFactory.class);

        /**
         * 이렇게 준비된 ApplicationContext의 getBean()을 이용해 userDao 객체를 가져올 수 있다.
         * getBean()은 ApplicationContext가 생성해놓은 Bean 목록에서 특정 Bean을 가져올 때 사용하는 메소드다.
         */
        UserDao userDao = context.getBean("userDao", UserDao.class);

    }
}
