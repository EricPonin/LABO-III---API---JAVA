package ar.edu.utn.frbb.tup.SistemaBancario.Controlador.handler;

import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Excepciones.ClienteAlreadyExistsException;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Excepciones.CuentaAlreadyExistsException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TupResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ClienteAlreadyExistsException.class})
    protected ResponseEntity<Object> handleClienteAlreadyExistsException(ClienteAlreadyExistsException ex, WebRequest request) {
        CustomApiError error = new CustomApiError();
        error.setErrorCode(1234); // Código de error para ClienteAlreadyExistsException
        error.setErrorMessage(ex.getMessage());
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    @ExceptionHandler(value = {CuentaAlreadyExistsException.class})
    protected ResponseEntity<Object> handleClienteAlreadyExistsException(CuentaAlreadyExistsException ex, WebRequest request) {
        CustomApiError error = new CustomApiError();
        error.setErrorCode(1234); // Código de error para ClienteAlreadyExistsException
        error.setErrorMessage(ex.getMessage());
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        CustomApiError error = new CustomApiError();
        error.setErrorCode(400); // Código de error para IllegalArgumentException
        error.setErrorMessage(ex.getMessage());
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {IllegalStateException.class})
    protected ResponseEntity<Object> handleIllegalStateException(IllegalStateException ex, WebRequest request) {
        CustomApiError error = new CustomApiError();
        error.setErrorCode(1234); // Código de error para IllegalStateException
        error.setErrorMessage(ex.getMessage());
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        if (body == null) {
            CustomApiError error = new CustomApiError();
            error.setErrorCode(status.value()); // Usa el código de estado HTTP como código de error
            error.setErrorMessage(ex.getMessage());
            body = error;
        }

        return new ResponseEntity<>(body, headers, status);
    }
}
