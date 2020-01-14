package org.corgi.tobyexample.bean;

import org.corgi.tobyexample.domain.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanScope {

    @Scope(value="prototype")
    @Bean
    public User prototypeUser() {
        return new User();
    }

    // Bean의 기본 Scope는 singleton이므로 별도로 지정할 필요가 없다.
    @Scope(value="singleton")
    @Bean
    public User singletonUser() {
        return new User();
    }

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(BeanScope.class);

        User prototypeUser1 = context.getBean("prototypeUser", User.class);
        User prototypeUser2 = context.getBean("prototypeUser", User.class);

        User singletonUser1 = context.getBean("singletonUser", User.class);
        User singletonUser2 = context.getBean("singletonUser", User.class);

        // 매번 새 객체를 생성, 반환
        System.out.println("prototypeUser1 : "+prototypeUser1);
        System.out.println("prototypeUser2 : "+prototypeUser2);

        // 하나의 동일 객체를 반환
        System.out.println("singletonUser1 : "+singletonUser1);
        System.out.println("singletonUser2 : "+singletonUser2);
    }
}
