package riyukita.openid.auth.server.routers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
public class AuthEndpointTest {
    @Autowired
    WebTestClient webTestClient;

    private static final String AUTHENTICATION_URI = "http://localhost:9090/authentication";
    private static final String CLIENT_REDIRECT_URI = "http://localhost:8080";

    @Test
    void testGetAuthorizationCode() {
        webTestClient
                .get()
                .uri(AuthorizationCodeRouter.AUTHORIZATION_CODE_PATH
                        + "?response_type=code"
                        + "&redirect_uri={clientRedirectUri}", CLIENT_REDIRECT_URI)
                .exchange()
                .expectStatus().isFound()
                .expectHeader().location(AUTHENTICATION_URI + "?redirect_uri=" + CLIENT_REDIRECT_URI);
    }

    @Test
    void testGetAuthorizationCodeReturnBadRequest() {
        webTestClient
                .get()
                .uri(AuthorizationCodeRouter.AUTHORIZATION_CODE_PATH
                        + "?response_type=code")
                .exchange()
                .expectStatus().isBadRequest();
    }
}
