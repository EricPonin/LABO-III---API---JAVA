package ar.edu.utn.frbb.tup.SistemaBancario.service;

import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.ClienteDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Cliente;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Excepciones.ClienteAlreadyExistsException;
import ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.ClienteDao;
import ar.edu.utn.frbb.tup.SistemaBancario.Servicio.ClienteService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteDao clienteDao;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//-------------------------------------------------------------------------------------------------------------------------------------------------------
// Test para buscar un cliente por su DNI
    @Test
    public void testBuscarCliente() {
        Cliente clienteMock = new Cliente("Fisica", "Juan", "Perez", "Calle Falsa 123", "juan.perez@example.com", LocalDate.of(1985, 5, 15), 31200300);
        when(clienteDao.find(31200300)).thenReturn(clienteMock);

        Cliente cliente = clienteService.buscarCliente(31200300);
        assertNotNull(cliente);
        assertEquals("Juan", cliente.getNombre());
        assertEquals("Perez", cliente.getApellido());
        assertEquals(31200300, cliente.getDni());
    }

//-------------------------------------------------------------------------------------------------------------------------------------------------------
// Test para dar de alta un cliente exitosamente
    @Test
    public void testDarDeAltaCliente_Success() throws ClienteAlreadyExistsException {
        ClienteDto clienteDto = new ClienteDto("F", "Juan", "Perez", "Calle Falsa 123", "juan.perez@example.com", "1985-05-15", 31200300);

        when(clienteDao.find(31200300)).thenReturn(null);

        Cliente cliente = clienteService.darDeAltaCliente(clienteDto);
        assertNotNull(cliente);
        assertEquals("Juan", cliente.getNombre());
        assertEquals("Perez", cliente.getApellido());
        verify(clienteDao, times(1)).save(cliente);
    }

//-------------------------------------------------------------------------------------------------------------------------------------------------------
// Test para dar de alta un cliente que ya existe
    @Test
    public void testDarDeAltaCliente_AlreadyExists() {
        ClienteDto clienteDto = new ClienteDto("F", "Juan", "Perez", "Calle Falsa 123", "juan.perez@example.com", "1985-05-15", 31200300);
        Cliente clienteMock = new Cliente(clienteDto);

        when(clienteDao.find(31200300)).thenReturn(clienteMock);

        assertThrows(ClienteAlreadyExistsException.class, () -> {
            clienteService.darDeAltaCliente(clienteDto);
        });
    }

//-------------------------------------------------------------------------------------------------------------------------------------------------------
// Test para listar todos los clientes
    @Test
    public void testListarClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("F", "Juan", "Perez", "Calle Falsa 123", "juan.perez@example.com", LocalDate.of(1985, 5, 15), 12345678L));
        clientes.add(new Cliente("F", "Maria", "Lopez", "Calle Verdadera 456", "maria.lopez@example.com", LocalDate.of(1990, 7, 10), 87654321L));

        when(clienteDao.findAll()).thenReturn(clientes);

        ArrayList<ClienteDto> result = clienteService.ListarClientes();
        assertNotNull(result);
        assertTrue(result.stream().anyMatch(c -> c.getNombre().equals("Juan")));
        assertTrue(result.stream().anyMatch(c -> c.getNombre().equals("Maria")));
    }

//-------------------------------------------------------------------------------------------------------------------------------------------------------
// Test para dar de baja un cliente exitosamente
    @Test
    public void testDarDeBajaCliente_Success() throws ClienteAlreadyExistsException {
        Cliente clienteMock = new Cliente("F", "Juan", "Perez", "Calle Falsa 123", "juan.perez@example.com", LocalDate.of(1985, 5, 15), 31200300);

        when(clienteDao.find(31200300)).thenReturn(clienteMock);

        ClienteDto clienteBaja = clienteService.darDeBajaCliente(31200300);
        verify(clienteDao, times(1)).delete(31200300);
        assertEquals(clienteMock.getDni(), clienteBaja.getDni());
    }

//-------------------------------------------------------------------------------------------------------------------------------------------------------
// Test para dar de baja un cliente que no existe
    @Test
    public void testDarDeBajaCliente_NotFound() {
        when(clienteDao.find(31200300)).thenReturn(null);

        assertThrows(ClienteAlreadyExistsException.class, () -> {
            clienteService.darDeBajaCliente(31200300);
        });
    }

//-------------------------------------------------------------------------------------------------------------------------------------------------------
// Test para buscar un cliente por su DNI exitosamente
    @Test
    public void testBuscarClientePorDni_Success() throws ClienteAlreadyExistsException {
        Cliente clienteMock = new Cliente("Fisica", "Juan", "Perez", "Calle Falsa 123", "juan.perez@example.com", LocalDate.of(1985, 5, 15), 31200300);

        when(clienteDao.find(31200300)).thenReturn(clienteMock);

        ClienteDto clienteDto = clienteService.buscarClientePorDni(31200300);
        assertNotNull(clienteDto);
        assertEquals(31200300, clienteDto.getDni());
    }

//-------------------------------------------------------------------------------------------------------------------------------------------------------
// Test para buscar un cliente por su DNI que no existe
    @Test
    public void testBuscarClientePorDni_NotFound() {
        when(clienteDao.find(31200300)).thenReturn(null);

        assertThrows(ClienteAlreadyExistsException.class, () -> {
            clienteService.buscarClientePorDni(31200300);
        });
    }
}
