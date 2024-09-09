package ar.edu.utn.frbb.tup.SistemaBancario.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Controller.CuentaController;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.CuentaDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.validator.CuentaValidator;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Cuenta;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Excepciones.CuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.SistemaBancario.Servicio.CuentaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

class CuentaControllerTest {

    @Mock
    private CuentaService cuentaService;

    @Mock
    private CuentaValidator cuentaValidator;

    @InjectMocks
    private CuentaController cuentaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCuentas() {
        ArrayList<CuentaDto> cuentasMock = new ArrayList<>();
        cuentasMock.add(new CuentaDto(12345678,1,0, "CC", "PESOS"));

        when(cuentaService.ListarCuentasDto()).thenReturn(cuentasMock);

        ArrayList<CuentaDto> cuentas = cuentaController.getCuentas();

        assertNotNull(cuentas);
        assertEquals(1, cuentas.size());
        assertEquals(12345678, cuentas.get(0).getTitular());
    }

    @Test
    public void testAltaCuenta() throws CuentaAlreadyExistsException {
        CuentaDto cuentaDto = new CuentaDto(12345678,1,0, "CC", "PESOS");
        Cuenta cuenta = new Cuenta(cuentaDto);

        doNothing().when(cuentaValidator).validate(cuentaDto);
        when(cuentaService.agregarCuenta(cuentaDto)).thenReturn(cuenta);

        Cuenta nuevaCuenta = cuentaController.altaCuenta(cuentaDto);

        assertNotNull(nuevaCuenta);
        assertEquals(12345678, nuevaCuenta.getTitular());
    }

    @Test
    public void testGetCuenta() {
        CuentaDto cuentaDto = new CuentaDto(12345678,1,0, "CC", "PESOS");

        when(cuentaService.verCuenta(1)).thenReturn(cuentaDto);

        CuentaDto cuenta = cuentaController.getCuenta(1);

        assertNotNull(cuenta);
        assertEquals(12345678, cuenta.getTitular());
    }

    @Test
    public void testBorrarCuenta() {
        CuentaDto cuentaDto = new CuentaDto(12345678,1,0, "CC", "PESOS");

        when(cuentaService.eliminarCuenta(1)).thenReturn(cuentaDto);

        CuentaDto cuentaEliminada = cuentaController.borrarCuenta(1);

        assertNotNull(cuentaEliminada);
        assertEquals(12345678, cuentaEliminada.getTitular());
    }
}
