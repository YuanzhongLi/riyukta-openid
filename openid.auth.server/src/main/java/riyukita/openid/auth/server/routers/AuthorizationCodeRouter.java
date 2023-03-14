package riyukita.openid.auth.server.routers;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class AuthorizationCodeRouter {
    public static final String AUTHORIZATION_CODE_PATH = "/authorization-code";

    private final AuthorizationCodeHandler handler;

    @Bean
    public RouterFunction<ServerResponse> authorizationCodeRoutes() {
        return route()
                .GET(AUTHORIZATION_CODE_PATH, accept(MediaType.APPLICATION_JSON), handler::getAuthorizationCode)
                .build();
    }
}
