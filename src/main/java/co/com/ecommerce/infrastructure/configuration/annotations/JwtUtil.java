package co.com.ecommerce.infrastructure.configuration.annotations;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.ecommerce.infrastructure.configuration.exception.CustomInfrastructureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class JwtUtil {

    @Value("${application.secrets-token.value}")
    private String secretsToken;

    public Mono<TokenPayloadDto>  decodeJWT(String token) {
        return decodeJWT(token, true, "");
    }

    public Mono<TokenPayloadDto>  decodeJWT(String token, Boolean requiredToken) {
        return decodeJWT(token, requiredToken, "");
    }

    @SuppressWarnings("unchecked")
    public Mono<TokenPayloadDto> decodeJWT(String tokenFinal, Boolean requiredToken, String... roles) {
        return Mono.fromCallable(() -> {
            String token = tokenFinal;
            if (requiredToken == false && token == null) {
                TokenPayloadDto tokenPayloadDto = new TokenPayloadDto();
                return tokenPayloadDto;
            }
            if (token == null) {
                throw new CustomInfrastructureException("UNAUTHORIZED", 401);
            }

            token = token.replace("Bearer ", "");
            // split into 3 parts with . delimiter
            String[] parts = token.split("\\.");
            if (parts.length != 3) {
                throw new CustomInfrastructureException("UNAUTHORIZED", 401);
            }
            String header = parts[0];
            String payload = parts[1];
            String signature = parts[2];
            String body = decodeBase64(parts[1]);
            try {
                // en estre proceso se valida la firma de jwt y el tiempo de duracion de token.
                Mac mac = Mac.getInstance("HmacSHA256");
                log.info("Secreto: " + secretsToken);
                SecretKeySpec secretKey = new SecretKeySpec(secretsToken.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
                mac.init(secretKey);
    
                byte[] data = (header + "." + payload).getBytes(StandardCharsets.UTF_8);
                byte[] calculatedSignature = mac.doFinal(data);
    
                byte[] decodedSignature = Base64.getUrlDecoder().decode(signature);
    
                // Comparar las firmas
                if (!MessageDigest.isEqual(calculatedSignature, decodedSignature)) {
                    throw new CustomInfrastructureException("UNAUTHORIZED", 401);
                }
    
                // Decodificar el payload JSON y extraer la fecha de expiración
                Map<String, Object> mapping = new ObjectMapper().readValue(body, HashMap.class);
                long expirationTime = (int) mapping.get("exp");
    
                // Verificar si la fecha de expiración ha pasado
                long currentTimeMillis = System.currentTimeMillis() / 1000; // Convertir a segundos
                if (expirationTime <= currentTimeMillis) {
                    throw new CustomInfrastructureException("SESSION_EXPIRED", 401);
                }
                if (roles[0] != "" && roles != null && roles.length > 0) {
                    boolean status = false;
                    for (String rol : roles) {
                        if (rol.equals((String) mapping.get("rol"))) {
                            status = true;
                        }
                    }
                    if (status == false) {
                        throw new CustomInfrastructureException("FORBIDDEN", 403);
                    }
                }
    
                TokenPayloadDto tokenPayloadDto = new TokenPayloadDto();
    
                tokenPayloadDto.setRol((String) mapping.get("rol"));
                tokenPayloadDto.setUsername((String) mapping.get("username"));
                tokenPayloadDto.setUserId(Long.parseLong(mapping.get("userId").toString()));
                tokenPayloadDto.setDistributorId((int) mapping.get("distributorId"));
    
                Object customerIdValue = mapping.get("customerId");
                Long customerId = customerIdValue != null ? Long.parseLong(customerIdValue.toString()) : 0L;
                tokenPayloadDto.setCustomerId(customerId);
    
                return tokenPayloadDto;
            } catch (CustomInfrastructureException e) {
                throw new CustomInfrastructureException(e.getCode(), e.getStatusCode());
            } catch (JsonMappingException e) {
                //
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                //
                e.printStackTrace();
            } catch (java.security.InvalidKeyException e) {
                //
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                //
                e.printStackTrace();
            }
            throw new CustomInfrastructureException("UNAUTHORIZED", 401);

        });
    }

    private String decodeBase64(String base64String) {
        byte[] decodedBytes = Base64.getUrlDecoder().decode(base64String);
        return new String(decodedBytes);
    }
}