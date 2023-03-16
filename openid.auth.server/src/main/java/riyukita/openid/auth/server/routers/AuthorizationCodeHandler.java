package riyukita.openid.auth.server.routers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

@Log4j2
@Component
@RequiredArgsConstructor
public class AuthorizationCodeHandler {
    private static final String AUTHENTICATION_URI = "http://localhost:9090" + AuthorizationCodeRouter.AUTHENTICATION_URL;

    public Mono<ServerResponse> getAuthorizationCode(ServerRequest request) {
        if (request.queryParam("response_type").isEmpty()) {
            return ServerResponse
                    .badRequest()
                    .body(Mono.just("query param \"response_type\" is necessary"), String.class);
        }

        if (request.queryParam("redirect_uri").isEmpty()) {
            return ServerResponse
                    .badRequest()
                    .body(Mono.just("query param \"redirect_uri\" is necessary"), String.class);
        }

        final String responseType = request.queryParam("response_type").get();
        final String redirectUri = request.queryParam("redirect_uri").get();
        log.info("response type: {}", responseType);
        log.info("redirect uri: {}", redirectUri);

        // redirect to authentication page and set "client" redirect uri as query param
        return ServerResponse
                .status(302)
                .location(UriComponentsBuilder
                        .fromUri(URI.create(AUTHENTICATION_URI))
                        .queryParam("redirect_uri", redirectUri)
                        .build()
                        .toUri())
                .build();
    }

    public Mono<ServerResponse> authentication(ServerRequest request) {
        if (request.queryParam("redirect_uri").isEmpty()) {
            return ServerResponse
                    .badRequest()
                    .body(Mono.just("query param \"redirect_uri\" is necessary"), String.class);
        }

        Mono<String> redirect = Mono.just("redirect to authentication page");
        return ServerResponse
                .ok()
                .body(redirect, String.class);
    }
}
