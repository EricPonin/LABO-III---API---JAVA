package ar.edu.utn.frbb.tup.SistemaBancario.Controlador.validator;

import org.springframework.stereotype.Component;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.CuentaDto;


@Component
public class CuentaValidator {

    public void validate(CuentaDto cuentaDto) {
        // Validar que el objeto CuentaDto no sea nulo
        if (cuentaDto == null) {
            throw new IllegalArgumentException("El objeto CuentaDto no debe ser nulo");
        }
        // Verificar que el número de cuenta sea mayor a 0
        if (cuentaDto.getNroCuenta() <= 0) {
            throw new IllegalArgumentException("El número de cuenta no debe ser mayor a 0");
        }
        // Verificar que el numero de titular sea mayor a 0
        if (cuentaDto.getTitular() <= 0) {
            throw new IllegalArgumentException("El numero de titular debe ser un numero positivo valido");
        }
        // Verificar tipo de persona
        String tipoCta = cuentaDto.getTipoCuenta();
        tipoCta = tipoCta.toUpperCase();
        cuentaDto.setTipoCuenta(tipoCta);
        if (!"CC".equals(tipoCta) && !"CA".equals(tipoCta)) {
            throw new IllegalArgumentException("El tipo de cuenta debe ser(CC o CA)");
        }
        //Verificar tipo de moneda
        String tipoMoneda = cuentaDto.getTipoMoneda();
        tipoMoneda = tipoMoneda.toUpperCase();
        cuentaDto.setTipoMoneda(tipoMoneda);
        if(!"PESOS".equals(tipoMoneda) && !"DOLARES".equals(tipoMoneda)) {
            throw new IllegalArgumentException("El tipo de moneda debe ser(PESOS o DOLARES)");
        }
    }

    
}
