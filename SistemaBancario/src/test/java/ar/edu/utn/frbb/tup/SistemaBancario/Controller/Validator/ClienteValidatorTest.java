package ar.edu.utn.frbb.tup.SistemaBancario.Controller.Validator;

import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.ClienteDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.validator.ClienteValidator;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.*;

public class ClienteValidatorTest {
    @Mock
    private ClienteValidator clienteValidator;

    @BeforeEach
    public void setUp() {
        clienteValidator = new ClienteValidator();
    }

    @Test
    public void testValidate_ValidClienteDto() {
        Cliente clienteMock = new Cliente("F", "Juan", "Perez", "Calle Falsa 123", "juan.perez@example.com", LocalDate.of(1985, 05, 15), 31200300);
        ClienteDto clienteDto = new ClienteDto(clienteMock);
        clienteDto.setTipoPersona("F");
        clienteDto.setFechaNac("2000-01-01");

        assertDoesNotThrow(() -> clienteValidator.validate(clienteDto));
    }

    @Test
    public void testValidate_NullClienteDto() {
        assertThrows(IllegalArgumentException.class, () -> clienteValidator.validate(null),
            "El objeto ClienteDto no debe ser nulo");
    }

    @Test
    public void testValidate_EmptyTipoPersona() {
        Cliente clienteMock = new Cliente("F", "Juan", "Perez", "Calle Falsa 123", "juan.perez@example.com", LocalDate.of(1985, 05, 15), 31200300);
        ClienteDto clienteDto = new ClienteDto(clienteMock);
        clienteDto.setTipoPersona("");
        clienteDto.setFechaNac("2000-01-01");

        assertThrows(IllegalArgumentException.class, () -> clienteValidator.validate(clienteDto),
            "El tipo de persona no debe ser nulo o vacío");
    }

    @Test
    public void testValidate_InvalidTipoPersona() {
        Cliente clienteMock = new Cliente("F", "Juan", "Perez", "Calle Falsa 123", "juan.perez@example.com", LocalDate.of(1985, 05, 15), 31200300);
        ClienteDto clienteDto = new ClienteDto(clienteMock);
        clienteDto.setTipoPersona("X");
        clienteDto.setFechaNac("2000-01-01");

        assertThrows(IllegalArgumentException.class, () -> clienteValidator.validate(clienteDto),
            "El tipo de persona debe ser 'F' (Física) o 'J' (Jurídica)");
    }

    @Test
    public void testValidate_NullFechaNac() {
        Cliente clienteMock = new Cliente("F", "Juan", "Perez", "Calle Falsa 123", "juan.perez@example.com", LocalDate.of(1985, 05, 15), 31200300);
        ClienteDto clienteDto = new ClienteDto(clienteMock);
        clienteDto.setTipoPersona("F");
        clienteDto.setFechaNac(null);

        assertThrows(IllegalArgumentException.class, () -> clienteValidator.validate(clienteDto),
            "La fecha de nacimiento no debe ser nula o vacía");
    }

    @Test
    public void testValidate_EmptyFechaNac() {
        Cliente clienteMock = new Cliente("F", "Juan", "Perez", "Calle Falsa 123", "juan.perez@example.com", LocalDate.of(1985, 05, 15), 31200300);
        ClienteDto clienteDto = new ClienteDto(clienteMock);
        clienteDto.setTipoPersona("F");
        clienteDto.setFechaNac("");

        assertThrows(IllegalArgumentException.class, () -> clienteValidator.validate(clienteDto),
            "La fecha de nacimiento no debe ser nula o vacía");
    }

    @Test
    public void testValidate_InvalidFechaNacFormat() {
        Cliente clienteMock = new Cliente("F", "Juan", "Perez", "Calle Falsa 123", "juan.perez@example.com", LocalDate.of(1985, 05, 15), 31200300);
        ClienteDto clienteDto = new ClienteDto(clienteMock);
        clienteDto.setTipoPersona("F");
        clienteDto.setFechaNac("01-01-2000");

        assertThrows(IllegalArgumentException.class, () -> clienteValidator.validate(clienteDto),
            "Error en el formato de fecha, debe ser 'yyyy-MM-dd'");
    }
}
