package riyukita.openid.client.routers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import riyukita.openid.client.client.AuthClient;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Component
@RequiredArgsConstructor
public class ClientHandler {

    private final AuthClient authClient;
    public Mono<ServerResponse> authorizationRequestHandler(ServerRequest request) {
        Map<String, Object> models = new HashMap<>();
        models.put("authorizationCodeUri", authClient.getAuthorizationCodeUri());
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_HTML)
                .render("authorization_request", models);
    }
}
