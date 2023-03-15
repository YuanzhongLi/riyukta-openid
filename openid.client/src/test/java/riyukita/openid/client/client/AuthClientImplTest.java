package riyukita.openid.client.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthClientImplTest {
    @Autowired
    AuthClient client;

    @Test
    void testGetAuthorizationCode() {
        final String EXPECTED_AUTHORIZATION_CODE = "authorization code";

        StepVerifier
                .create(client.getAuthorizationCode())
                .assertNext(code -> {
                    assertEquals(EXPECTED_AUTHORIZATION_CODE, code);
                })
                .verifyComplete();
    }
}