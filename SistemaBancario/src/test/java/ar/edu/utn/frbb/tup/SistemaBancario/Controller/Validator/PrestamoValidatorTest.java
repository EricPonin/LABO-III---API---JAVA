package ar.edu.utn.frbb.tup.SistemaBancario.Controller.Validator;

import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.PrestamoDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.validator.PrestamoValidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrestamoValidatorTest {

    private PrestamoValidator prestamoValidator;

    @BeforeEach
    public void setUp() {
        prestamoValidator = new PrestamoValidator();
    }
//------------------------------------------------------------------------------------------------------------

    @Test
    public void testPrestamoValido() {
        // Crear un PrestamoDto válido
        PrestamoDto prestamoDto = new PrestamoDto();
        prestamoDto.setMoneda("pesos");
        prestamoDto.setMonto(10000);
        prestamoDto.setPlazo(12);

        // Validar que no se lance ninguna excepción
        assertDoesNotThrow(() -> prestamoValidator.validate(prestamoDto));

        // Validar que la moneda se haya convertido a mayúsculas
        assertEquals("PESOS", prestamoDto.getMoneda());
    }
//------------------------------------------------------------------------------------------------------------

    @Test
    public void testMonedaInvalida() {
        // Crear un PrestamoDto con una moneda inválida
        PrestamoDto prestamoDto = new PrestamoDto();
        prestamoDto.setMoneda("euros");
        prestamoDto.setMonto(10000);
        prestamoDto.setPlazo(12);

        // Validar que se lance una excepción con el mensaje adecuado
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            prestamoValidator.validate(prestamoDto);
        });
        assertEquals("El tipo de Moneda no es correcto (Pesos o Dolar)", exception.getMessage());
    }
//------------------------------------------------------------------------------------------------------------

    @Test
    public void testMontoInvalido() {
        // Crear un PrestamoDto con un monto inválido
        PrestamoDto prestamoDto = new PrestamoDto();
        prestamoDto.setMoneda("pesos");
        prestamoDto.setMonto(0);  // Monto inválido
        prestamoDto.setPlazo(12);

        // Validar que se lance una excepción con el mensaje adecuado
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            prestamoValidator.validate(prestamoDto);
        });
        assertEquals("El monto debe ser mayor a 0", exception.getMessage());
    }
//------------------------------------------------------------------------------------------------------------
    @Test
    public void testPlazoInvalido() {
        // Crear un PrestamoDto con un plazo inválido
        PrestamoDto prestamoDto = new PrestamoDto();
        prestamoDto.setMoneda("pesos");
        prestamoDto.setMonto(10000);
        prestamoDto.setPlazo(0);

        // Validar que se lance una excepción con el mensaje adecuado
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            prestamoValidator.validate(prestamoDto);
        });
        assertEquals("El Plazo debe ser mayor a 0", exception.getMessage());
    }
}
