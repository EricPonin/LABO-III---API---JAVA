package ar.edu.utn.frbb.tup.SistemaBancario.Controller;

import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Controller.ClienteController;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.ClienteDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.validator.ClienteValidator;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Cliente;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Excepciones.ClienteAlreadyExistsException;
import ar.edu.utn.frbb.tup.SistemaBancario.Servicio.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @Mock
    private ClienteValidator clienteValidator;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    
    @Test
    void testGetClientes() {
        List<ClienteDto> mockClientes = new ArrayList<>();
        Cliente clienteMock1 = new Cliente("J", "Juan", "Perez", "Calle Falsa 123", "juan.perez@example.com", LocalDate.of(1985, 05, 15), 31200300);
        Cliente clienteMock2 = new Cliente("F", "Roberto", "Alvarez", "Calle Verdadera 456", "roberto.alvarez@example.com", LocalDate.of(1995, 05, 15), 42300400);
        mockClientes.add(new ClienteDto(clienteMock1));
        mockClientes.add(new ClienteDto(clienteMock2));

        when(clienteService.ListarClientes()).thenReturn((ArrayList<ClienteDto>) mockClientes);

        ArrayList<ClienteDto> result = clienteController.getClientes();

        assertEquals(2, result.size());
        verify(clienteService, times(1)).ListarClientes();
    }
   
    @Test
    void testCrearCliente() throws ClienteAlreadyExistsException {
        
        Cliente clienteMock = new Cliente("F", "Juan", "Perez", "Calle Falsa 123", "juan.perez@example.com", LocalDate.of(1985, 05, 15), 31200300);
        ClienteDto clienteDto = new ClienteDto(clienteMock);
        doNothing().when(clienteValidator).validate(clienteDto);
        when(clienteService.darDeAltaCliente(any(ClienteDto.class))).thenReturn(clienteMock);

        Cliente result = clienteController.crearCliente(clienteDto);

        assertEquals(clienteMock, result);
        verify(clienteValidator, times(1)).validate(clienteDto);
        verify(clienteService, times(1)).darDeAltaCliente(clienteDto);
    }

    @Test
    void testBorrarCliente() {
        long id = 31200300;
        Cliente clienteMock = new Cliente("F", "Juan", "Perez", "Calle Falsa 123", "juan.perez@example.com", LocalDate.of(1985, 05, 15), 31200300);
        ClienteDto clienteDto = new ClienteDto(clienteMock);
        when(clienteService.darDeBajaCliente(id)).thenReturn(clienteDto);

        ClienteDto result = clienteController.borrarCliente(id);

        assertEquals(clienteDto, result);
        verify(clienteService, times(1)).darDeBajaCliente(id);
    }

    @Test
    void testVerClientePorIdEncontrado() {
        
        long id = 31200300;
        Cliente clienteMock = new Cliente("F", "Juan", "Perez", "Calle Falsa 123", "juan.perez@example.com", LocalDate.of(1985, 05, 15), 31200300);
        ClienteDto clienteDto = new ClienteDto(clienteMock);
        when(clienteService.buscarClientePorDni(id)).thenReturn(clienteDto);

        ClienteDto result = clienteController.verClientePorId(id);

        assertEquals(clienteDto, result);
    }
}
