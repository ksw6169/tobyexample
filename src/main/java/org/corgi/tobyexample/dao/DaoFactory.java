package org.corgi.tobyexample.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration Annotation은 ApplicationContext가 특정 객체를 생성, 관리 제어할 때
 * 필요한 설정 정보를 제공할 때 사용하는 Annotation이다.
 */
@Configuration
public class DaoFactory {

    /**
     * Bean Annotation은 Spring이 생성, 관리, 제어할 객체에 사용한다.
     * Configuration Annotation이 붙은 class의 내부에서 사용할 수 있다.
     */
    @Bean
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }



}
