package ar.edu.utn.frbb.tup.SistemaBancario.Controller.Validator;

import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.CuentaDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.validator.CuentaValidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuentaValidatorTest {

    private CuentaValidator cuentaValidator;

    @BeforeEach
    public void setUp() {
        cuentaValidator = new CuentaValidator();
    }

    @Test
    public void testCuentaValida() {
        CuentaDto cuentaDto = new CuentaDto(123, 456789, 1000.0, "CC", "PESOS");
        assertDoesNotThrow(() -> cuentaValidator.validate(cuentaDto));
    }

    @Test
    public void testCuentaDtoNulo() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            cuentaValidator.validate(null);
        });
        assertEquals("El objeto CuentaDto no debe ser nulo", exception.getMessage());
    }

    @Test
    public void testNumeroCuentaInvalido() {
        CuentaDto cuentaDto = new CuentaDto(123, -456789, 1000.0, "CC", "PESOS");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            cuentaValidator.validate(cuentaDto);
        });
        assertEquals("El número de cuenta no debe ser mayor a 0", exception.getMessage());
    }

    @Test
    public void testTitularInvalido() {
        CuentaDto cuentaDto = new CuentaDto(-123, 456789, 1000.0, "CC", "PESOS");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            cuentaValidator.validate(cuentaDto);
        });
        assertEquals("El numero de titular debe ser un numero positivo valido", exception.getMessage());
    }

    @Test
    public void testTipoCuentaInvalido() {
        CuentaDto cuentaDto = new CuentaDto(123, 456789, 1000.0, "XYZ", "PESOS");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            cuentaValidator.validate(cuentaDto);
        });
        assertEquals("El tipo de cuenta debe ser(CC o CA)", exception.getMessage());
    }

    @Test
    public void testTipoMonedaInvalido() {
        CuentaDto cuentaDto = new CuentaDto(123, 456789, 1000.0, "CC", "EUROS");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            cuentaValidator.validate(cuentaDto);
        });
        assertEquals("El tipo de moneda debe ser(PESOS o DOLARES)", exception.getMessage());
    }
}
