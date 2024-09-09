package ar.edu.utn.frbb.tup.SistemaBancario.Persistence;

import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Cuenta;
import ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.GestionDeArchivos.LeerDeArchivoCuentas;
import ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.CuentaDao;
import ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.GestionDeArchivos.GuardarEnArchivoCuentas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CuentaDaoTest {

    private CuentaDao cuentaDao;
    private String rutaArchivoCuentas = "C:\\Users\\Eric\\Desktop\\SistemaBancario\\src\\main\\java\\ar\\edu\\utn\\frbb\\tup\\SistemaBancario\\Persistencia\\Archivos\\Cuentas.txt";

    @BeforeEach
    public void setUp() {
        cuentaDao = new CuentaDao();
    }

//---------------------------------------------------------------------------------------------------------------------
// Test para buscar una cuenta por su número y tipo de moneda
    @Test
    public void testFindCuentaExistente() {
        ArrayList<Cuenta> cuentasMock = new ArrayList<>();
        Cuenta cuenta = new Cuenta(31200300, 12345, 5000.0, "CC", "PESOS");
        cuentasMock.add(cuenta);

        try (MockedStatic<LeerDeArchivoCuentas> mockLeerDeArchivoCuentas = Mockito.mockStatic(LeerDeArchivoCuentas.class)) {
            mockLeerDeArchivoCuentas.when(() -> LeerDeArchivoCuentas.leerCuentasDeCSV(rutaArchivoCuentas)).thenReturn(cuentasMock);

            Cuenta cuentaEncontrada = cuentaDao.find(31200300, "PESOS");
            assertNotNull(cuentaEncontrada);
            assertEquals(31200300, cuentaEncontrada.getTitular());
        }
    }

//---------------------------------------------------------------------------------------------------------------------
// Test para buscar una cuenta por su número y tipo de moneda (caso no existente)
    @Test
    public void testFindCuentaNoExistente() {
        ArrayList<Cuenta> cuentasMock = new ArrayList<>();

        try (MockedStatic<LeerDeArchivoCuentas> mockLeerDeArchivoCuentas = Mockito.mockStatic(LeerDeArchivoCuentas.class)) {
            mockLeerDeArchivoCuentas.when(() -> LeerDeArchivoCuentas.leerCuentasDeCSV(rutaArchivoCuentas)).thenReturn(cuentasMock);

            Cuenta cuentaEncontrada = cuentaDao.find(87654321, "PESOS");
            assertNull(cuentaEncontrada);
        }
    }

//---------------------------------------------------------------------------------------------------------------------
// Test para guardar una cuenta
    @Test
    public void testSaveCuenta() {
        ArrayList<Cuenta> cuentasMock = new ArrayList<>();
        Cuenta cuenta = new Cuenta(31200300, 12345, 5000.0, "CC", "PESOS");

        try (MockedStatic<LeerDeArchivoCuentas> mockLeerDeArchivoCuentas = Mockito.mockStatic(LeerDeArchivoCuentas.class);
             MockedStatic<GuardarEnArchivoCuentas> mockGuardarEnArchivoCuentas = Mockito.mockStatic(GuardarEnArchivoCuentas.class)) {

            mockLeerDeArchivoCuentas.when(() -> LeerDeArchivoCuentas.leerCuentasDeCSV(rutaArchivoCuentas)).thenReturn(cuentasMock);
            cuentaDao.save(cuenta);

            cuentasMock.add(cuenta);  // Actualizamos el mock con la nueva cuenta

            mockGuardarEnArchivoCuentas.verify(() -> GuardarEnArchivoCuentas.guardarCuentaEnCSV(cuentasMock, rutaArchivoCuentas), times(1));
        }
    }

//---------------------------------------------------------------------------------------------------------------------
// Test para eliminar una cuenta existente
    @Test
    public void testDeleteCuentaExistente() {
        ArrayList<Cuenta> cuentasMock = new ArrayList<>();
        Cuenta cuenta = new Cuenta(31200300, 12345, 5000.0, "CC", "PESOS");
        cuentasMock.add(cuenta);

        try (MockedStatic<LeerDeArchivoCuentas> mockLeerDeArchivoCuentas = Mockito.mockStatic(LeerDeArchivoCuentas.class);
             MockedStatic<GuardarEnArchivoCuentas> mockGuardarEnArchivoCuentas = Mockito.mockStatic(GuardarEnArchivoCuentas.class)) {

            mockLeerDeArchivoCuentas.when(() -> LeerDeArchivoCuentas.leerCuentasDeCSV(rutaArchivoCuentas)).thenReturn(cuentasMock);

            cuentaDao.delete(12345);
            cuentasMock.remove(cuenta);  // Actualizamos el mock para reflejar la eliminación

            mockGuardarEnArchivoCuentas.verify(() -> GuardarEnArchivoCuentas.guardarCuentaEnCSV(cuentasMock, rutaArchivoCuentas), times(1));
        }
    }

//---------------------------------------------------------------------------------------------------------------------
// Test para eliminar una cuenta no existente
    @Test
    public void testFindAllCuentas() {
        ArrayList<Cuenta> cuentasMock = new ArrayList<>();
        cuentasMock.add(new Cuenta(31200300, 12345, 5000.0, "CC", "PESOS"));

        try (MockedStatic<LeerDeArchivoCuentas> mockLeerDeArchivoCuentas = Mockito.mockStatic(LeerDeArchivoCuentas.class)) {
            mockLeerDeArchivoCuentas.when(() -> LeerDeArchivoCuentas.leerCuentasDeCSV(rutaArchivoCuentas)).thenReturn(cuentasMock);

            ArrayList<Cuenta> cuentas = cuentaDao.findAll();
            assertEquals(1, cuentas.size());
            assertEquals(12345, cuentas.get(0).getNroCuenta());
        }
    }
}
