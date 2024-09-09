package ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Excepciones;

public class CuentaAlreadyExistsException extends RuntimeException {
    public CuentaAlreadyExistsException(String mensaje){
        super(mensaje);
    }
    
}
