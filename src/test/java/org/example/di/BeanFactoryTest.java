package org.example.di;

import javassist.tools.reflect.Reflection;
import org.example.Controller.UserController;
import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BeanFactoryTest {
    private Reflections reflections;
    private BeanFactory beanFactory;

    @BeforeEach // 테스트 메소드가 실행되기전에 호출 (초기화 작업)
    void setUp() {
        reflections = new Reflections("org.example");
        // UserController, UserService
        Set<Class<?>> preInstantiatedClazz = getTypesAnnotatedWith(Controller.class, Service.class);
        beanFactory = new BeanFactory(preInstantiatedClazz);
    }

    private Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation>... annotations) {  // 애노테이스 타입의 클래스 객체가 여러개 들어올 수 있음
        Set<Class<?>> beans = new HashSet<>();
        for (Class<? extends Annotation> annotation : annotations) { // reflections("org.example)" 밑에 있는 클래스 중에 해당 애노테이션을 사용하는 클래스를 조회해서 beans 에 저장
            beans.addAll(reflections.getTypesAnnotatedWith(annotation));
        }
        return beans;
    }

    @Test
    void diTest() {
        UserController userController = beanFactory.getBean(UserController.class);

        assertThat(userController).isNotNull();
        assertThat(userController.getUserService()).isNotNull();
    }
}
