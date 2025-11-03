package com.example.ioc.anotaciones;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Qualifier;

@Retention(RUNTIME)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Qualifier("local")
public @interface Local {

}
