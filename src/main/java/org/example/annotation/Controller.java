package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})  // ElementType.TYPE: 클래스, 인터페이스 등에 붙을 수 있는 annotation
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
}
