package ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Excepciones;

public class ClienteAlreadyExistsException extends RuntimeException {
    public ClienteAlreadyExistsException(String mensaje) {
        super(mensaje);
    }
}
