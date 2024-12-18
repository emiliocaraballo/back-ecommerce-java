package co.com.ecommerce.infrastructure.configuration.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.context.i18n.LocaleContextHolder;

@Component
public class MessageProvider {

    private final MessageSource messageSource;

    @Autowired
    public MessageProvider(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String code, String type) {
        return messageSource.getMessage(code + "." + type, null, LocaleContextHolder.getLocale());
    }
}