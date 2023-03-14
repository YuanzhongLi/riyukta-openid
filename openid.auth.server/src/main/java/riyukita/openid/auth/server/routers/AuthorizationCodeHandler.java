package riyukita.openid.auth.server.routers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthorizationCodeHandler {
    public Mono<ServerResponse> getAuthorizationCode(ServerRequest request) {
        Mono<String> authorizationCode = Mono.just("authorization code");
        return ServerResponse
                .ok()
                .body(authorizationCode, String.class);
    }
}
