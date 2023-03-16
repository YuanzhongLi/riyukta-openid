package riyukita.openid.client.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Log4j2
@Component
public class AuthClientImpl implements AuthClient {
    private final WebClient client;
    private static final String AUTHORIZATION_CODE_PATH = "/authorization-code";
    private static final String RESPONSE_TYPE = "code";
    private static final String CLIENT_ID = "client";
    private static final String REDIRECT_URI = "http://localhost:8080";
    private static final String SCOPE = "scope";
    private static final String STATE = "state";

    public AuthClientImpl(@Value("${auth.server.rooturl}") String authServerUrl, WebClient.Builder builder) {
        log.info("auth server url: {}", authServerUrl);
        this.client = builder
                .baseUrl(authServerUrl)
                .clientConnector(new ReactorClientHttpConnector(HttpClient
                        .create()
                        .followRedirect(true)))
                .build();
    }


    @Override
    public Mono<String> getAuthorizationCode() {

        return client
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(AUTHORIZATION_CODE_PATH)
                        .queryParam("response_type", RESPONSE_TYPE)
                        .queryParam("client_id", CLIENT_ID)
                        .queryParam("redirect_uri", REDIRECT_URI)
                        .queryParam("scope", SCOPE)
                        .queryParam("state", STATE)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
    }
}
