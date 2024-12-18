package co.com.ecommerce.infrastructure.configuration.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación personalizada para validar JWT en los controladores.
 */
@Target(ElementType.METHOD) // Se puede usar en métodos
@Retention(RetentionPolicy.RUNTIME) // Se conserva en tiempo de ejecución
public @interface ValidateJWT {
    boolean optional() default true; // Define si la validación de JWT es opcional
    String role() default ""; // El rol requerido para acceder al endpoint
    String roleV2() default ""; // El rol requerido para acceder al endpoint
    String roleV3() default ""; // El rol requerido para acceder al endpoint
}