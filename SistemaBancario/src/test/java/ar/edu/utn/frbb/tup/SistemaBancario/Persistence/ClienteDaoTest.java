package ar.edu.utn.frbb.tup.SistemaBancario.Persistence;

import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Cliente;
import ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.GestionDeArchivos.LeerDeArchivoClientes;
import ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.ClienteDao;
import ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.GestionDeArchivos.GuardarEnArchivoClientes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClienteDaoTest {

    private ClienteDao clienteDao;
    private String rutaArchivoClientes = "C:\\Users\\Eric\\Desktop\\SistemaBancario\\src\\main\\java\\ar\\edu\\utn\\frbb\\tup\\SistemaBancario\\Persistencia\\Archivos\\Clientes.txt";

    @BeforeEach
    public void setUp() {
        clienteDao = new ClienteDao();
    }

    @Test
    public void testFindClienteExistente() {
        ArrayList<Cliente> clientesMock = new ArrayList<>();
        Cliente cliente = new Cliente("F", "Juan", "Pérez", "Calle Falsa 123", "juan.perez@example.com", LocalDate.of(1985, 5, 20), 12345678);
        clientesMock.add(cliente);

        try (MockedStatic<LeerDeArchivoClientes> mockLeerDeArchivoClientes = Mockito.mockStatic(LeerDeArchivoClientes.class)) {
            mockLeerDeArchivoClientes.when(() -> LeerDeArchivoClientes.leerClientesDeCSV(rutaArchivoClientes)).thenReturn(clientesMock);

            Cliente clienteEncontrado = clienteDao.find(12345678);
            assertNotNull(clienteEncontrado);
            assertEquals(12345678, clienteEncontrado.getDni());
        }
    }

    @Test
    public void testFindClienteNoExistente() {
        ArrayList<Cliente> clientesMock = new ArrayList<>();

        try (MockedStatic<LeerDeArchivoClientes> mockedLeerDeArchivoClientes = Mockito.mockStatic(LeerDeArchivoClientes.class)) {
            mockedLeerDeArchivoClientes.when(() -> LeerDeArchivoClientes.leerClientesDeCSV(rutaArchivoClientes)).thenReturn(clientesMock);

            Cliente clienteEncontrado = clienteDao.find(87654321);
            assertNull(clienteEncontrado);
        }
    }

    @Test
    public void testSaveCliente() {
        ArrayList<Cliente> clientesMock = new ArrayList<>();
        Cliente cliente = new Cliente("F", "María", "Gómez", "Calle Verdadera 456", "maria.gomez@example.com", LocalDate.of(1990, 8, 15), 23456789);

        try (MockedStatic<LeerDeArchivoClientes> mockedLeerDeArchivoClientes = Mockito.mockStatic(LeerDeArchivoClientes.class);
             MockedStatic<GuardarEnArchivoClientes> mockedGuardarEnArchivoClientes = Mockito.mockStatic(GuardarEnArchivoClientes.class)) {

            mockedLeerDeArchivoClientes.when(() -> LeerDeArchivoClientes.leerClientesDeCSV(rutaArchivoClientes)).thenReturn(clientesMock);
            clienteDao.save(cliente);

            clientesMock.add(cliente);  // Actualizamos el mock con el nuevo cliente

            mockedGuardarEnArchivoClientes.verify(() -> GuardarEnArchivoClientes.guardarClientesEnCSV(clientesMock, rutaArchivoClientes), times(1));
        }
    }

    @Test
    public void testDeleteClienteExistente() {
        ArrayList<Cliente> clientesMock = new ArrayList<>();
        Cliente cliente = new Cliente("F", "Luis", "Rodríguez", "Calle Azul 789", "luis.rodriguez@example.com", LocalDate.of(1980, 3, 10), 34567890);
        clientesMock.add(cliente);

        try (MockedStatic<LeerDeArchivoClientes> mockedLeerDeArchivoClientes = Mockito.mockStatic(LeerDeArchivoClientes.class);
             MockedStatic<GuardarEnArchivoClientes> mockedGuardarEnArchivoClientes = Mockito.mockStatic(GuardarEnArchivoClientes.class)) {

            mockedLeerDeArchivoClientes.when(() -> LeerDeArchivoClientes.leerClientesDeCSV(rutaArchivoClientes)).thenReturn(clientesMock);

            clienteDao.delete(34567890);
            clientesMock.remove(cliente);  // Actualizamos el mock para reflejar la eliminación

            mockedGuardarEnArchivoClientes.verify(() -> GuardarEnArchivoClientes.guardarClientesEnCSV(clientesMock, rutaArchivoClientes), times(1));
        }
    }

    @Test
    public void testFindAllClientes() {
        ArrayList<Cliente> clientesMock = new ArrayList<>();
        clientesMock.add(new Cliente("F", "Pedro", "Ramírez", "Calle Roja 321", "pedro.ramirez@example.com", LocalDate.of(1982, 2, 25), 45678901));

        try (MockedStatic<LeerDeArchivoClientes> mockedLeerDeArchivoClientes = Mockito.mockStatic(LeerDeArchivoClientes.class)) {
            mockedLeerDeArchivoClientes.when(() -> LeerDeArchivoClientes.leerClientesDeCSV(rutaArchivoClientes)).thenReturn(clientesMock);

            ArrayList<Cliente> clientes = clienteDao.findAll();
            assertEquals(1, clientes.size());
            assertEquals(45678901, clientes.get(0).getDni());
        }
    }
}
