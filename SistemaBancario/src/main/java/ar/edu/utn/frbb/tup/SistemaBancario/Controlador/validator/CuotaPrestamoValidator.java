package ar.edu.utn.frbb.tup.SistemaBancario.Controlador.validator;

import org.springframework.stereotype.Component;

import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.PagoCuotaPrestamoDto;

@Component
public class CuotaPrestamoValidator {

    public void validate(PagoCuotaPrestamoDto cuotaPrestamoDto) {
        // Convertir la moneda a mayúsculas
        String moneda = cuotaPrestamoDto.getMoneda().toUpperCase();
        cuotaPrestamoDto.setMoneda(moneda);

        // Validar la moneda
        if (!"PESOS".equals(moneda) && !"DOLARES".equals(moneda)) {
            throw new IllegalArgumentException("El tipo de Moneda no es correcto (Pesos o Dolares)");
        }

        // Validar el número de cliente
        if(cuotaPrestamoDto.getNumeroCliente() <= 0) {
            throw new IllegalArgumentException("El numero de Cliente debe ser un numero positivo valido");
        }

        // Validar el número de préstamo
        if (cuotaPrestamoDto.getNumeroPrestamo() <= 0) {
            throw new IllegalArgumentException("El numero de Prestamo no es correcto");
        }
    }
    
}
