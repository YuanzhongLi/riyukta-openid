package riyukita.openid.client.client;

import reactor.core.publisher.Mono;

public interface AuthClient {
    String getAuthorizationCodeUri();
    Mono<String> getAuthorizationCode();
}
