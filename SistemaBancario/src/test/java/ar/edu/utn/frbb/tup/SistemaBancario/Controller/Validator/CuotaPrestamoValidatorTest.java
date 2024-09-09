package ar.edu.utn.frbb.tup.SistemaBancario.Controller.Validator;

import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.PagoCuotaPrestamoDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.validator.CuotaPrestamoValidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CuotaPrestamoValidatorTest {

    private CuotaPrestamoValidator cuotaPrestamoValidator;

    @BeforeEach
    public void setUp() {
        cuotaPrestamoValidator = new CuotaPrestamoValidator();
    }

    @Test
    public void testValidarPagoCuotaPrestamoDtoValido() {
        // Crear un PagoCuotaPrestamoDto válido
        PagoCuotaPrestamoDto cuotaPrestamoDto = new PagoCuotaPrestamoDto(30400500,20000,"Pesos");
        // Validar que no se lance ninguna excepción
        assertDoesNotThrow(() -> cuotaPrestamoValidator.validate(cuotaPrestamoDto));
        // Validar que la moneda haya sido convertida a mayúsculas
        assertEquals("PESOS", cuotaPrestamoDto.getMoneda());
    }

    @Test
    public void testValidarMonedaIncorrecta() {
        // Crear un PagoCuotaPrestamoDto con moneda incorrecta
        PagoCuotaPrestamoDto cuotaPrestamoDto = new PagoCuotaPrestamoDto(30400500,20000,"Euros");
        // Validar que se lance una excepción con el mensaje correcto
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cuotaPrestamoValidator.validate(cuotaPrestamoDto);
        });
        assertEquals("El tipo de Moneda no es correcto (Pesos o Dolar)", exception.getMessage());
    }

    @Test
    public void testValidarNumeroClienteIncorrecto() {
        // Crear un PagoCuotaPrestamoDto con un número de cliente incorrecto
        PagoCuotaPrestamoDto cuotaPrestamoDto = new PagoCuotaPrestamoDto(-30400500,20000,"Pesos");
       
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cuotaPrestamoValidator.validate(cuotaPrestamoDto);
        });
        assertEquals("El numero de Cliente debe ser un numero positivo valido", exception.getMessage());
    }

    @Test
    public void testValidarNumeroPrestamoIncorrecto() {
        // Crear un PagoCuotaPrestamoDto con un número de préstamo incorrecto
        PagoCuotaPrestamoDto cuotaPrestamoDto = new PagoCuotaPrestamoDto(30400500,-20000,"Pesos");
        // Validar que se lance una excepción con el mensaje correcto
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cuotaPrestamoValidator.validate(cuotaPrestamoDto);
        });
        assertTrue(exception.getMessage().contains("El numero de Prestamo no es correcto"));
    }
}
