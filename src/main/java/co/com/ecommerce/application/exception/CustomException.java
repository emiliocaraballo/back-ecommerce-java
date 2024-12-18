package co.com.ecommerce.application.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
  private final String code;
  private final String title;
  private final String description;
  private final int statusCode;
  private final String path;

  public CustomException(String code, int statusCode) {
    super(code);
    this.code = code;
    this.title = getCodeTitle(code);
    this.description = getCodeDescription(code);
    this.statusCode = statusCode;
    this.path = obtainPath();
  }

  public CustomException(String code) {
    this(code, 400);
  }

  @SuppressWarnings("null")
  private String obtainPath() {
    try {
      HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
          .getRequest();
      return request != null ? request.getRequestURI() : "unknown";
    } catch (Exception e) {
      return "unknown";
    }
  }

  private String getCodeTitle(String code) {
    return getCode(code, "title");

  }

  private String getCodeDescription(String code) {
    return getCode(code, "description");
  }

  private String getCode(String code,String type) {
    if (!ERROR_MESSAGES.containsKey(code)) {
      return type.equals("title") ? "Título no disponible" : "Descripción no disponible";
    }
    Map<String, String> messages = ERROR_MESSAGES.get(code);
    return messages.getOrDefault(type, "Valor no disponible");
  }

  // Almacén de títulos y descripciones
  private static final Map<String, Map<String, String>> ERROR_MESSAGES = new HashMap<>();

  static {
    // Inicialización del almacén de mensajes
    Map<String, String> badRequest = new HashMap<>();
    badRequest.put("title", "Solicitud incorrecta");
    badRequest.put("description", "La solicitud no pudo ser procesada.");
    ERROR_MESSAGES.put("BAD_REQUEST", badRequest);

    // Agrega otros códigos de error que necesites
    Map<String, String> internalServerError = new HashMap<>();
    internalServerError.put("title", "Error interno");
    internalServerError.put("description", "Se produjo un error inesperado en el servidor.");
    ERROR_MESSAGES.put("INTERNAL_SERVER_ERROR", internalServerError);
  }
}