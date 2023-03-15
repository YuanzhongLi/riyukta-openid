package riyukita.openid.client.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Log4j2
@Component
public class AuthClientImpl implements AuthClient {
    private final WebClient client;
    private static final String AUTHORIZATION_CODE_PATH = "/authorization-code";

    public AuthClientImpl(@Value("${auth.server.rooturl}") String authServerUrl, WebClient.Builder builder) {
        log.info("auth server url: {}", authServerUrl);
        this.client = builder.baseUrl(authServerUrl).build();
    }


    @Override
    public Mono<String> getAuthorizationCode() {
        return client.get().uri(AUTHORIZATION_CODE_PATH).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
    }
}
