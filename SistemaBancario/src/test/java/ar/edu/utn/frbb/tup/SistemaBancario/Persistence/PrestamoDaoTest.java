package ar.edu.utn.frbb.tup.SistemaBancario.Persistence;

import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Prestamo;
import ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.GestionDeArchivos.LeerDeArchivoPrestamos;
import ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.GestionDeArchivos.GuardarEnArchivoPrestamos;
import ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.PrestamoDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PrestamoDaoTest {

    private PrestamoDao prestamoDao;
    private String rutaArchivoPrestamos = "C:\\Users\\Eric\\Desktop\\SistemaBancario\\src\\main\\java\\ar\\edu\\utn\\frbb\\tup\\SistemaBancario\\Persistencia\\Archivos\\Prestamos.txt";;

    @BeforeEach
    public void setUp() {
        prestamoDao = new PrestamoDao();
    }

    //------------------------------------------------------------------------------------------------------------------
    // Test para guardar un préstamo
    @Test
    public void testSavePrestamo() {
        ArrayList<Prestamo> prestamosMock = new ArrayList<>();
        Prestamo prestamo = new Prestamo(1, 12345678, 12, 10000.0, 500, 833.33, "Aprobado", "PESOS");

        try (MockedStatic<LeerDeArchivoPrestamos> mockLeerDeArchivoPrestamos = Mockito.mockStatic(LeerDeArchivoPrestamos.class);
             MockedStatic<GuardarEnArchivoPrestamos> mockGuardarEnArchivoPrestamos = Mockito.mockStatic(GuardarEnArchivoPrestamos.class)) {

            mockLeerDeArchivoPrestamos.when(() -> LeerDeArchivoPrestamos.leerPrestamosDeCSV(rutaArchivoPrestamos)).thenReturn(prestamosMock);
            prestamoDao.save(prestamo);

            prestamosMock.add(prestamo);  // Actualizamos el mock con el nuevo préstamo

            mockGuardarEnArchivoPrestamos.verify(() -> GuardarEnArchivoPrestamos.guardarPrestamosEnCSV(prestamosMock, rutaArchivoPrestamos), times(1));
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    // Test para buscar un préstamo por su número
    @Test
    public void testFindPrestamoExistente() {
        ArrayList<Prestamo> prestamosMock = new ArrayList<>();
        Prestamo prestamo = new Prestamo(1, 12345678, 12, 10000.0, 500, 833.33, "Aprobado", "PESOS");
        prestamosMock.add(prestamo);

        try (MockedStatic<LeerDeArchivoPrestamos> mockLeerDeArchivoPrestamos = Mockito.mockStatic(LeerDeArchivoPrestamos.class)) {
            mockLeerDeArchivoPrestamos.when(() -> LeerDeArchivoPrestamos.leerPrestamosDeCSV(rutaArchivoPrestamos)).thenReturn(prestamosMock);

            Prestamo prestamoEncontrado = prestamoDao.find(1);
            assertNotNull(prestamoEncontrado);
            assertEquals(1, prestamoEncontrado.getNumeroPrestamo());
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    // Test para buscar un préstamo no existente
    @Test
    public void testFindPrestamoNoExistente() {
        ArrayList<Prestamo> prestamosMock = new ArrayList<>();

        try (MockedStatic<LeerDeArchivoPrestamos> mockLeerDeArchivoPrestamos = Mockito.mockStatic(LeerDeArchivoPrestamos.class)) {
            mockLeerDeArchivoPrestamos.when(() -> LeerDeArchivoPrestamos.leerPrestamosDeCSV(rutaArchivoPrestamos)).thenReturn(prestamosMock);

            Prestamo prestamoEncontrado = prestamoDao.find(999);
            assertNull(prestamoEncontrado);
        }
    }


    //------------------------------------------------------------------------------------------------------------------
    // Test para encontrar todos los préstamos de un cliente
    @Test
    public void testFindAllPrestamosCliente() {
        ArrayList<Prestamo> prestamosMock = new ArrayList<>();
        Prestamo prestamo1 = new Prestamo(1, 12345678, 12, 10000.0, 500, 833.33, "Aprobado", "PESOS");
        Prestamo prestamo2 = new Prestamo(2, 12345678, 36, 50000.0, 1500, 833.33, "Aprobado", "DOLARES");
        prestamosMock.add(prestamo1);
        prestamosMock.add(prestamo2);

        try (MockedStatic<LeerDeArchivoPrestamos> mockLeerDeArchivoPrestamos = Mockito.mockStatic(LeerDeArchivoPrestamos.class)) {
            mockLeerDeArchivoPrestamos.when(() -> LeerDeArchivoPrestamos.leerPrestamosDeCSV(rutaArchivoPrestamos)).thenReturn(prestamosMock);

            ArrayList<Prestamo> prestamosCliente = prestamoDao.findAllCliente(12345678);
            assertEquals(2, prestamosCliente.size());
            assertTrue(prestamosCliente.contains(prestamo1));
            assertTrue(prestamosCliente.contains(prestamo2));
        }
    }
}
