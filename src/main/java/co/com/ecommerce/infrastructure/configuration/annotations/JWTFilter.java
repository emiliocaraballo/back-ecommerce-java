package co.com.ecommerce.infrastructure.configuration.annotations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;
import reactor.core.publisher.Mono;

@Component
public class JWTFilter implements WebFilter {

    @Value("${application.secrets-token.value}") 
    private String secretKey;

    @SuppressWarnings("null")
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String token = extractTokenFromHeader(request);
        exchange.getAttributes().put("token", token != null ? token : "");
        // return chain.filter(exchange);
        return chain.filter(exchange).contextWrite(ctx -> ctx.put(ServerWebExchange.class, exchange));
    }

    private String extractTokenFromHeader(ServerHttpRequest request) {
        String bearerToken = "";
        try {
            bearerToken = request.getHeaders().getFirst("Authorization");
            return bearerToken;
        } catch (Exception e) {
            bearerToken = "";
        }
        return bearerToken;
    }
}
