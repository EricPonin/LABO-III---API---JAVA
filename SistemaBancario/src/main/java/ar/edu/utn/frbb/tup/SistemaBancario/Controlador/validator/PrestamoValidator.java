package ar.edu.utn.frbb.tup.SistemaBancario.Controlador.validator;

import org.springframework.stereotype.Component;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.PrestamoDto;

@Component
public class PrestamoValidator {

     public void validate(PrestamoDto prestamoDto) {
     
        String moneda = prestamoDto.getMoneda();
        moneda = moneda.toUpperCase();
        prestamoDto.setMoneda(moneda);

        if (!"PESOS".equals(moneda) && !"DOLARES".equals(moneda)) {
            throw new IllegalArgumentException("El tipo de Moneda no es correcto (Pesos o Dolar)");
        }
        if (prestamoDto.getMonto() <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a 0");
        }
        if (prestamoDto.getPlazo() <= 0) {
             throw new IllegalArgumentException("El Plazo debe ser mayor a 0");
        }

    }
    
}
