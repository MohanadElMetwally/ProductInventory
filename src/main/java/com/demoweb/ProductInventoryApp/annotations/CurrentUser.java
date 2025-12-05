package com.demoweb.ProductInventoryApp.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.demoweb.ProductInventoryApp.enums.Role;

import io.swagger.v3.oas.annotations.Parameter;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Parameter(hidden = true)
public @interface CurrentUser {
    Role requiredRole() default Role.ANY;
}