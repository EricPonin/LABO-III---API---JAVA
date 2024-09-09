package ar.edu.utn.frbb.tup.SistemaBancario.Controller;

import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Controller.PrestamoController;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.PagoCuotaPrestamoDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.PrestamoDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.PrestamoResponseDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.PrestamosClienteResponseDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.validator.CuotaPrestamoValidator;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.validator.PrestamoValidator;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Excepciones.ClienteAlreadyExistsException;
import ar.edu.utn.frbb.tup.SistemaBancario.Servicio.PrestamoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class PrestamoControllerTest {

    @InjectMocks
    private PrestamoController prestamoController;

    @Mock
    private PrestamoService prestamoService;

    @Mock
    private PrestamoValidator prestamoValidator;

    @Mock
    private CuotaPrestamoValidator cuotaPrestamoValidator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
//---------------------------------------------------------------------------------------------------------------------
    @Test
    public void testSolicitarPrestamo_Valido() throws ClienteAlreadyExistsException {
        // Crear un PrestamoDto válido y su respuesta
        PrestamoDto prestamoDto = new PrestamoDto();
        PrestamoResponseDto prestamoResponseDto = new PrestamoResponseDto();

        // Configurar el mock del validador y servicio
        doNothing().when(prestamoValidator).validate(prestamoDto);
        when(prestamoService.nuevoPrestamo(prestamoDto)).thenReturn(prestamoResponseDto);

        // Llamar al endpoint
        PrestamoResponseDto response = prestamoController.SolicitarPrestamo(prestamoDto);

        // Verificar resultados
        assertNotNull(response);
        verify(prestamoValidator, times(1)).validate(prestamoDto);
        verify(prestamoService, times(1)).nuevoPrestamo(prestamoDto);
    }
//---------------------------------------------------------------------------------------------------------------------

    @Test
    public void testSolicitarPrestamo_ClienteAlreadyExistsException() throws ClienteAlreadyExistsException {
        // Crear un PrestamoDto y lanzar excepción
        PrestamoDto prestamoDto = new PrestamoDto();
        when(prestamoService.nuevoPrestamo(prestamoDto)).thenThrow(new ClienteAlreadyExistsException("Cliente ya existe"));

        // Verificar que se lance la excepción adecuada
        Exception exception = assertThrows(ClienteAlreadyExistsException.class, () -> {
            prestamoController.SolicitarPrestamo(prestamoDto);
        });
        assertEquals("Cliente ya existe", exception.getMessage());
    }

//---------------------------------------------------------------------------------------------------------------------

    @Test
    public void testConsultaPrestamo() {
        PrestamosClienteResponseDto prestamosClienteResponseDto = new PrestamosClienteResponseDto();
        long clienteId = 12345;

        // Configurar el mock del servicio
        when(prestamoService.consultarPrestamo(clienteId)).thenReturn(prestamosClienteResponseDto);

        // Llamar al endpoint
        PrestamosClienteResponseDto response = prestamoController.ConsultaPrestamo(clienteId);

        // Verificar resultados
        assertNotNull(response);
        verify(prestamoService, times(1)).consultarPrestamo(clienteId);
    }

//---------------------------------------------------------------------------------------------------------------------

    @Test
    public void testDebitarCuota_Valido() {
        // Crear PagoCuotaPrestamoDto y su respuesta
        PagoCuotaPrestamoDto pagoCuotaPrestamoDto = new PagoCuotaPrestamoDto(30400500,35545,"Pesos");
        PrestamoResponseDto prestamoResponseDto = new PrestamoResponseDto();

        // Configurar el mock del validador y servicio
        doNothing().when(cuotaPrestamoValidator).validate(pagoCuotaPrestamoDto);
        when(prestamoService.pagarCuota(pagoCuotaPrestamoDto)).thenReturn(prestamoResponseDto);

        // Llamar al endpoint
        PrestamoResponseDto response = prestamoController.DebitarCuota(pagoCuotaPrestamoDto);

        // Verificar resultados
        assertNotNull(response);
        verify(cuotaPrestamoValidator, times(1)).validate(pagoCuotaPrestamoDto);
        verify(prestamoService, times(1)).pagarCuota(pagoCuotaPrestamoDto);
    }
}
