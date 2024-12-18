package co.com.ecommerce.infrastructure.configuration.annotations;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import co.com.ecommerce.application.exception.CustomException;
import reactor.core.publisher.Mono;

@Aspect
@Component
public class JWTValidationAspect {

    private final JwtUtil jwtUtil;

    public JWTValidationAspect(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @SuppressWarnings("unchecked")
    @Around("@annotation(validateJWT)")
    public Object validateJWT(ProceedingJoinPoint pjp, ValidateJWT validateJWT) {
        return Mono.deferContextual(context -> {
            ServerWebExchange exchange = context.get(ServerWebExchange.class);
            if (exchange == null) {
                return Mono.error(new CustomException("EXCHANGE_NOT_FOUND", 500));
            }

            String token = extractTokenFromHeader(exchange);

           Mono<TokenPayloadDto> tokenPayloadDtoMono = jwtUtil.decodeJWT(token, validateJWT.optional(), validateJWT.role(), validateJWT.roleV2(), validateJWT.roleV3());
           return tokenPayloadDtoMono.flatMap(tokenPayloadDto -> {
            Boolean requiredToken = validateJWT.optional();
            if (token != null && !token.isEmpty()) {
                requiredToken = true;
            }
            if (tokenPayloadDto.getUserId() == null && requiredToken) {
                return Mono.error(new CustomException("UNAUTHORIZED", 401));
            }
            exchange.getAttributes().put("user", tokenPayloadDto);
            try {
                return (Mono<? extends Object>) pjp.proceed();
            } catch (Throwable e) {
                e.printStackTrace();
            }
                        return null;
        });

            // return Mono.fromCallable(() -> {
            //     try {
            //         return pjp.proceed();
            //     } catch (Throwable e) {
            //         e.printStackTrace();
            //     }
            //                     return context;
            // })
            //            .flatMap(result -> {
            //                if (result == null) {
            //                    return Mono.empty();
            //                }
            //                if (result instanceof Mono) {
            //                    return (Mono<?>) result;
            //                }
            //                return Mono.just(result);
            //            })
            //            .onErrorResume(throwable -> {
            //                return Mono.error(new CustomException("Error durante la ejecución del método original", 500));
            //            });
        });
    }

    private String extractTokenFromHeader(ServerWebExchange exchange) {
        return exchange.getRequest().getHeaders().getFirst("Authorization");
    }
}
