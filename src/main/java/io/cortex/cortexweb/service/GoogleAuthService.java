package io.cortex.cortexweb.service;

import io.cortex.cortexweb.model.User;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface GoogleAuthService {

    User authenticate(String security_token) throws GeneralSecurityException, IOException;

}
