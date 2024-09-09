package ar.edu.utn.frbb.tup.SistemaBancario.Controlador.validator;

import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.ClienteDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class ClienteValidator {


    public void validate(ClienteDto clienteDto) {
        // Validar que el objeto ClienteDto no sea nulo
        if (clienteDto == null) {
            throw new IllegalArgumentException("El objeto ClienteDto no debe ser nulo");
        }
    
        // Verificar tipo de persona
        String tPersona = clienteDto.getTipoPersona();
        if (tPersona == null || tPersona.isEmpty()) {
            throw new IllegalArgumentException("El tipo de persona no debe ser nulo o vacío");
        }
    
        tPersona = tPersona.toUpperCase();
        clienteDto.setTipoPersona(tPersona);
    
        if (!"F".equals(tPersona) && !"J".equals(tPersona)) {
            throw new IllegalArgumentException("El tipo de persona debe ser 'F' (Física) o 'J' (Jurídica)");
        }
    
        // Verificar formato de fecha
        String fechaNac = clienteDto.getFechaNac();
        if (fechaNac == null || fechaNac.isEmpty()) {
            throw new IllegalArgumentException("La fecha de nacimiento no debe ser nula o vacía");
        }
    
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Ajusta el patrón según el formato esperado
        try {
            LocalDate.parse(fechaNac, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Error en el formato de fecha, debe ser 'yyyy-MM-dd'", e);
        }
    }
}
