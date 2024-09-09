package ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Excepciones;


public class TipoCuentaAlreadyExistsException extends RuntimeException {
    public TipoCuentaAlreadyExistsException(String message) {
        super(message);
    }
}
