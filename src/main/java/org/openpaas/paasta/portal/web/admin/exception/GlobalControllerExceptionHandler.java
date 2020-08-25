package org.openpaas.paasta.portal.web.admin.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * org.openpaas.paasta.portal.web.admin.exception
 *
 * @version 1.0
 * @since 2016.07.07
 */
@ControllerAdvice
class GlobalControllerExceptionHandler implements UncaughtExceptionHandler {

    @Autowired
    public MessageSource messageSource;

    private final Logger logger;
    private final Logger loggerDebugOnly;

    public GlobalControllerExceptionHandler() {
        logger = LoggerFactory.getLogger(getClass());
        loggerDebugOnly = LoggerFactory.getLogger( "Debugonly" );
    }

    @ResponseBody
    public ResponseEntity<?> handleUnauthenticationException(Exception e) {
        return errorResponse(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DataIntegrityViolationException.class, SQLIntegrityConstraintViolationException.class})
    @ResponseBody
    public ResponseEntity<?> handleConflictException(Exception e) {
        return errorResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({SQLException.class, DataAccessException.class, RuntimeException.class})
    @ResponseBody
    public ResponseEntity<?> handleSQLException(Exception e) {
        return errorResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({IOException.class, ParseException.class, JsonParseException.class, JsonMappingException.class})
    @ResponseBody
    public ResponseEntity<?> handleParseException(Exception e) {
        return errorResponse(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidKeyException.class, NoSuchAlgorithmException.class})
    @ResponseBody
    public ResponseEntity<?> handleHashException(Exception e) {
        return errorResponse(new Exception("Encrypt/Decrypt key is requested"), HttpStatus.LOCKED);
    }

    @ExceptionHandler({HttpClientErrorException.class})
    @ResponseBody
    public ResponseEntity<?> handleHttpClientErrorException(HttpClientErrorException e) throws JSONException {
        return response(e.getResponseBodyAsString(), e.getStatusCode());
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseEntity<?> handleAnyException(Exception e) {
        return errorResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ExceptionMessage> errorResponse(Throwable throwable, HttpStatus status) {
        if (null != throwable) {
            logger.error("### EXCEPTION status :: {}", status);
            logger.error("### EXCEPTION getMessage() :: {}", throwable.getMessage());
            // DEBUG ONLY
            loggerDebugOnly.error("### EXCEPTION detail :: ", throwable);

            return response(new ExceptionMessage(throwable, status, messageSource), status);
        } else {
            return response(null, status);
        }
    }

    protected <T> ResponseEntity<T> response(T body, HttpStatus status) {
        return new ResponseEntity<T>(body, new HttpHeaders(), status);
    }

    @Override
    public void uncaughtException( Thread t, Throwable e ) {
        handleAnyException(new RuntimeException("Unexpected exception occurs in [" + t.getName() + '-' + t.getId() + ']', e));
    }
}