package riyukita.openid.client.routers;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class ClientRouter {
    public static final String AUTHORIZATION_REQUEST_PATH = "/authorization-request";

    private final ClientHandler handler;

    @Bean
    public RouterFunction<ServerResponse> clientRoutes() {
        return route(RequestPredicates.GET(AUTHORIZATION_REQUEST_PATH), handler::authorizationRequestHandler);
    }
}
