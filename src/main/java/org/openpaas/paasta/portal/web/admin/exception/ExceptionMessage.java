package org.openpaas.paasta.portal.web.admin.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

/**
 * org.openpaas.paasta.portal.web.admin.exception
 *
 * @version 1.0
 * @since 2016.07.07
 */
class ExceptionMessage {

    private final Throwable throwable;

    private final HttpStatus status;

    private final MessageSource messageSource;

    ExceptionMessage(Throwable throwable, HttpStatus status, MessageSource messageSource) {
        this.throwable = throwable;
        this.status = status;
        this.messageSource = messageSource;
    }

    @JsonProperty("message")
    public String getMessage() {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status))
            return messageSource.getMessage("common.system.error.message", null, LocaleContextHolder.getLocale());

        if (HttpStatus.BAD_REQUEST.equals(status))
            return messageSource.getMessage("common.system.error.message", null, LocaleContextHolder.getLocale());

        if (HttpStatus.UNAUTHORIZED.equals(status))
            return messageSource.getMessage("common.system.error.message", null, LocaleContextHolder.getLocale());

        if (HttpStatus.PAYMENT_REQUIRED.equals(status))
            return messageSource.getMessage("common.system.error.message", null, LocaleContextHolder.getLocale());

        if (HttpStatus.FORBIDDEN.equals(status))
            return messageSource.getMessage("common.system.error.message", null, LocaleContextHolder.getLocale());

        if (HttpStatus.NOT_FOUND.equals(status))
            return messageSource.getMessage("common.system.error.message", null, LocaleContextHolder.getLocale());

        if (HttpStatus.METHOD_NOT_ALLOWED.equals(status))
            return messageSource.getMessage("common.system.error.message", null, LocaleContextHolder.getLocale());

        if (HttpStatus.NOT_ACCEPTABLE.equals(status))
            return messageSource.getMessage("common.system.error.message", null, LocaleContextHolder.getLocale());

        if (HttpStatus.PROXY_AUTHENTICATION_REQUIRED.equals(status))
            return messageSource.getMessage("common.system.error.message", null, LocaleContextHolder.getLocale());

        if (HttpStatus.REQUEST_TIMEOUT.equals(status))
            return messageSource.getMessage("common.system.error.message", null, LocaleContextHolder.getLocale());

        if (HttpStatus.CONFLICT.equals(status))
            return messageSource.getMessage("common.system.error.message", null, LocaleContextHolder.getLocale());

        return messageSource.getMessage("common.system.error.message", null, LocaleContextHolder.getLocale());
    }

    @JsonProperty("cause")
    public ExceptionMessage getCause() {
        return throwable.getCause() != null ? new ExceptionMessage(throwable.getCause(), status, messageSource) : null;
    }
}
