package riyukita.openid.client.client;

import reactor.core.publisher.Mono;

public interface AuthClient {
    Mono<String> getAuthorizationCode();
}
