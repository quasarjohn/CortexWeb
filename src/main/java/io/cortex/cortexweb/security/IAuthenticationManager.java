package io.cortex.cortexweb.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

public interface IAuthenticationManager {
    SecurityContext getSecurityContext();
    String getCurrentUser();
}
