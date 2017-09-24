package io.cortex.cortexweb.security;

import org.springframework.security.core.context.SecurityContext;

public interface AuthenticationManager {
    SecurityContext getSecurityContext();
    String getCurrentUser();
}
