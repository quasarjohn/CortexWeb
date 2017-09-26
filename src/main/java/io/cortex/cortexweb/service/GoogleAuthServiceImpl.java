package io.cortex.cortexweb.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import io.cortex.cortexweb.model.User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.UUID;


@Service
public class GoogleAuthServiceImpl implements GoogleAuthService {

    private static final String CLIENT_ID = "36486410372-vl7067jt59p6j46tu712vvi93b91ha0t.apps.googleusercontent.com";

    @Override
    public User authenticate(String security_token) throws GeneralSecurityException, IOException {

        User user = new User();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.
                Builder(new NetHttpTransport(), new JacksonFactory())
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();

        GoogleIdToken googleIdToken = verifier.verify(security_token);
        if (googleIdToken != null) {
            GoogleIdToken.Payload payload = googleIdToken.getPayload();

            String email = payload.getEmail();
            String username = email.split("@")[0];

            String first_name = (String) payload.get("given_name");
            String last_name = (String) payload.get("family_name");
            String img_url = (String) payload.get("picture");


            user.setEmail(payload.getEmail());
            user.setLastName(last_name);
            user.setFirstName(first_name);
            user.setEnabled(true);
            user.setUsername(username);
            user.setBio(null);
            user.setImg_url(img_url);
            //wala nang password
            user.setPassword("default_password");
            user.setReputationScore(0);
            user.setApi_key(UUID.randomUUID().toString().substring(1, 10));

        } else {
            System.out.println("INVALID ID TOKEN");
        }

        return user;
    }
}
