package ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Excepciones;

public class PrestamoAlreadyExistsException extends RuntimeException {
    public PrestamoAlreadyExistsException(String mensaje){
        super(mensaje);
    }
    
}
