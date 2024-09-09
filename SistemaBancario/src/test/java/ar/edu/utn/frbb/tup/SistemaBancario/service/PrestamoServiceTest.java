package ar.edu.utn.frbb.tup.SistemaBancario.service;


import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.PagoCuotaPrestamoDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.PrestamoDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.PrestamosClienteResponseDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Cliente;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Prestamo;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Excepciones.ClienteAlreadyExistsException;
import ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.ClienteDao;
import ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.CuentaDao;
import ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.PrestamoDao;
import ar.edu.utn.frbb.tup.SistemaBancario.Servicio.CuentaService;
import ar.edu.utn.frbb.tup.SistemaBancario.Servicio.PrestamoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PrestamoServiceTest {

    @Mock
    private PrestamoDao prestamoDao;

    @Mock
    private CuentaDao cuentaDao;

    @Mock
    private ClienteDao clienteDao;

    @Mock
    private CuentaService cuentaService;

    @InjectMocks
    private PrestamoService prestamoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//---------------------------------------------------------------------------------------------------------------------  

    @Test
    public void testNuevoPrestamo_Fail_ClientNotFound() {
        PrestamoDto prestamoDto = new PrestamoDto(30200300, 0, 0, 10000.0, 12, 0, "PESOS", null);

        when(clienteDao.find(prestamoDto.getNumeroCliente())).thenReturn(null);

        assertThrows(ClienteAlreadyExistsException.class, () -> {
            prestamoService.nuevoPrestamo(prestamoDto);
        });
    }

 //---------------------------------------------------------------------------------------------------------------------

    @Test
    public void testPagarCuota_ClientNotFound() {
        PagoCuotaPrestamoDto pagoDto = new PagoCuotaPrestamoDto(30200300, 1, "PESOS");

        when(clienteDao.find(pagoDto.getNumeroCliente())).thenReturn(null);

        assertThrows(ClienteAlreadyExistsException.class, () -> {
            prestamoService.pagarCuota(pagoDto);
        });
    }

//---------------------------------------------------------------------------------------------------------------------

    @Test
    public void testConsultPrestamos_Success() throws ClienteAlreadyExistsException {
        PrestamoDto prestamoDto = new PrestamoDto(30200300, 0, 0, 10000.0, 12, 0, "PESOS", null);
        long numeroCliente = 30200300;
        Prestamo prestamo = new Prestamo(prestamoDto);
        prestamo.setMonto(10000.0);
        prestamo.setPlazo(12);
        prestamo.setPagosRealizados(0);
        prestamo.setMontoCuota(1000.0);

        ArrayList<Prestamo> prestamos = new ArrayList<>();
        prestamos.add(prestamo);

        when(clienteDao.find(numeroCliente)).thenReturn(new Cliente());
        when(prestamoDao.findAllCliente(numeroCliente)).thenReturn(prestamos);

        PrestamosClienteResponseDto response = prestamoService.consultarPrestamo(numeroCliente);

        assertNotNull(response);
        assertEquals(numeroCliente, response.getNumeroCliente());
        assertEquals(1, response.getConsultaPrestamos().size());
        assertEquals(10000.0, response.getConsultaPrestamos().get(0).getMonto());
        verify(prestamoDao, times(1)).findAllCliente(numeroCliente);
    }
    
//---------------------------------------------------------------------------------------------------------------------

    @Test
    public void testConsultPrestamos_ClientNotFound() {
        long numeroCliente = 30200300;

        when(clienteDao.find(numeroCliente)).thenReturn(null);

        assertThrows(ClienteAlreadyExistsException.class, () -> {
            prestamoService.consultarPrestamo(numeroCliente);
        });
    }
}
