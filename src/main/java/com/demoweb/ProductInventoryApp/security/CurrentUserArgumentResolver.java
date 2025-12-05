package com.demoweb.ProductInventoryApp.security;

import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.demoweb.ProductInventoryApp.annotations.CurrentUser;
import com.demoweb.ProductInventoryApp.enums.Role;
import com.demoweb.ProductInventoryApp.models.UserPrincipal;
import com.demoweb.ProductInventoryApp.models.Users;

@Component
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(@NonNull MethodParameter parameter) {
        return parameter.getParameterAnnotation(CurrentUser.class) != null
            && Users.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Users resolveArgument(@NonNull MethodParameter parameter,
        @Nullable ModelAndViewContainer mavContainer, @NonNull NativeWebRequest webRequest,
        @Nullable WebDataBinderFactory binderFactory) throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null
            || !(authentication.getPrincipal() instanceof UserPrincipal principal)) {
            throw new AccessDeniedException("User must be authenticated");
        }

        Users user = principal.getUser();

        CurrentUser annotation = parameter.getParameterAnnotation(CurrentUser.class);

        @SuppressWarnings("null")
        Role required = annotation.requiredRole();

        if (required != Role.ANY && user.getRole() != required) {
            throw new AccessDeniedException("User does not have enough privileges");
        }

        return user;
    }
}
