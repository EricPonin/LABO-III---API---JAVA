package ar.edu.utn.frbb.tup.SistemaBancario.service;

import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.CuentaDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Cuenta;
import ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.CuentaDao;
import ar.edu.utn.frbb.tup.SistemaBancario.Servicio.CuentaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class CuentaServiceTest {

    @Mock
    private CuentaDao cuentaDao;

    @InjectMocks
    private CuentaService cuentaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
//---------------------------------------------------------------------------------------------------------------------

    @Test
    public void testAgregarCuenta() {
        // Preparar datos de prueba
        CuentaDto cuentaDto = new CuentaDto(30200300, 1001, 1000.0, "Ahorro", "PESOS");
        Cuenta cuenta = new Cuenta(cuentaDto);

        // Mockear el comportamiento del método save
        doNothing().when(cuentaDao).save(any(Cuenta.class));

        Cuenta result = cuentaService.agregarCuenta(cuentaDto);

        assertNotNull(result);
        assertEquals(cuenta.getNroCuenta(), result.getNroCuenta());
        assertEquals(cuenta.getSaldo(), result.getSaldo());
        assertEquals(cuenta.getTipoCuenta(), result.getTipoCuenta());
        assertEquals(cuenta.getTipoMoneda(), result.getTipoMoneda());

        verify(cuentaDao, times(1)).save(any(Cuenta.class));
    }
//---------------------------------------------------------------------------------------------------------------------
    @Test
    public void testListarCuentas() {
        // Crear cuentas simuladas
        Cuenta cuenta1 = new Cuenta(new CuentaDto(30200300, 1001, 1000.0, "CA", "PESOS"));
        Cuenta cuenta2 = new Cuenta(new CuentaDto(40500600, 1002, 1500.0, "CC",  "DOLARES"));
        
        // Agregar cuentas a una lista simulada
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        cuentas.add(cuenta1);
        cuentas.add(cuenta2);
        
        // Simular el comportamiento de cuentaDao.findAll()
        when(cuentaDao.findAll()).thenReturn(cuentas);
        
        // Llamar al método a testear
        ArrayList<CuentaDto> result = cuentaService.ListarCuentasDto();
        
        // Verificar que la lista no sea nula
        assertNotNull(result);
        
        // Verificar el tamaño de la lista resultante
        assertEquals(2, result.size());
        
        // Verificar que los datos en la lista resultante son los esperados
        assertEquals(1001, result.get(0).getNroCuenta());
        assertEquals("CA", result.get(0).getTipoCuenta());
        assertEquals(1002, result.get(1).getNroCuenta());
        assertEquals("CC", result.get(1).getTipoCuenta());
}


//---------------------------------------------------------------------------------------------------------------------

    @Test
    public void testDeposito() {
        doNothing().when(cuentaDao).depositarEnCuenta(anyLong(), anyString(), anyDouble());

        cuentaService.deposito(30200300, "PESOS", 500.0);

        verify(cuentaDao, times(1)).depositarEnCuenta(30200300, "PESOS", 500.0);
    }

//---------------------------------------------------------------------------------------------------------------------

    @Test
    public void testDebitoCuota() {
        doNothing().when(cuentaDao).retirarDeCuenta(anyLong(), anyString(), anyDouble());

        cuentaService.debitoCuota(30200300, "PESOS", 200.0);

        verify(cuentaDao, times(1)).retirarDeCuenta(30200300, "PESOS", 200.0);
    }
}
