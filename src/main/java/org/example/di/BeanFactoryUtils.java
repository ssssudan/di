package org.example.di;

import org.example.annotation.Inject;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.util.Set;

public class BeanFactoryUtils {
    public static Constructor<?> getInjectedConstructor(Class<?> clazz) {
        Set<Constructor> injectConstructor = ReflectionUtils.getAllConstructors(clazz, ReflectionUtils.withAnnotation(Inject.class)); // Inject 애노테이션이 붙은 생성자만 가져옴
        if (injectConstructor.isEmpty()) {
            return null;
        }
        return injectConstructor.iterator().next();
    }
}
