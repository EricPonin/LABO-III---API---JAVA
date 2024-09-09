package ar.edu.utn.frbb.tup.SistemaBancario.service;

import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.PrestamoDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Servicio.ScoreCrediticioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreCrediticioServiceTest {

    private ScoreCrediticioService scoreCrediticioService;

    @BeforeEach
    public void setUp() {
        scoreCrediticioService = new ScoreCrediticioService();
    }

    @Test
    public void testCalcularScoreClienteNoAprobado() {
        PrestamoDto prestamoDto = new PrestamoDto();
        prestamoDto.setNumeroCliente(10200200);
        prestamoDto.setMonto(500000);
        prestamoDto.setPlazo(12);

        boolean resultado = scoreCrediticioService.calcularScoreCrediticio(prestamoDto);

        assertFalse(resultado, "El préstamo no debe ser aprobado para el cliente 10200200");
    }

    @Test
    public void testCalcularScoreMontoMenorParaClienteEspecifico() {
        PrestamoDto prestamoDto = new PrestamoDto();
        prestamoDto.setNumeroCliente(20300300);
        prestamoDto.setMonto(900000);
        prestamoDto.setPlazo(12);

        boolean resultado = scoreCrediticioService.calcularScoreCrediticio(prestamoDto);

        assertTrue(resultado, "El préstamo debe ser aprobado para el cliente 20300300 con un monto menor a 1000000");
    }

    @Test
    public void testCalcularScoreMontoMayorParaClienteEspecifico() {
        PrestamoDto prestamoDto = new PrestamoDto();
        prestamoDto.setNumeroCliente(20300300);
        prestamoDto.setMonto(1500000);
        prestamoDto.setPlazo(12);

        boolean resultado = scoreCrediticioService.calcularScoreCrediticio(prestamoDto);

        assertTrue(resultado, "El préstamo debe ser aprobado para el cliente 20300300 aunque el monto sea mayor a 1000000");
    }

    @Test
    public void testCalcularScorePlazoMayorParaClienteEspecifico() {
        PrestamoDto prestamoDto = new PrestamoDto();
        prestamoDto.setNumeroCliente(30400400);
        prestamoDto.setMonto(2000000);
        prestamoDto.setPlazo(24);

        boolean resultado = scoreCrediticioService.calcularScoreCrediticio(prestamoDto);

        assertTrue(resultado, "El préstamo debe ser aprobado para el cliente 30400400 sin importar el monto");
    }

    @Test
    public void testCalcularScoreClienteGenerico() {
        PrestamoDto prestamoDto = new PrestamoDto();
        prestamoDto.setNumeroCliente(40500500);
        prestamoDto.setMonto(500000);
        prestamoDto.setPlazo(12);

        boolean resultado = scoreCrediticioService.calcularScoreCrediticio(prestamoDto);

        assertTrue(resultado, "El préstamo debe ser aprobado para otros clientes genéricos");
    }
}
