package io.cortex.cortexweb.security;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationManager implements IAuthenticationManager {

    @Override
    public SecurityContext getSecurityContext() {
        return SecurityContextHolder.getContext();
    }

    @Override
    public String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
