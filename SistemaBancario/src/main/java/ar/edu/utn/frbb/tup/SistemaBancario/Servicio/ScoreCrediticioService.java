package ar.edu.utn.frbb.tup.SistemaBancario.Servicio;

import org.springframework.stereotype.Service;

import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.PrestamoDto;

@Service
public class ScoreCrediticioService {

    //En este sencillo metodo se calcula el score crediticio del cliente a partir de su numero de cliente de manera arbitraria con ciertos parametros
    public boolean calcularScoreCrediticio(PrestamoDto prestamoDto) {
        //Para el cliente 10200200 no se aprueba el prestamo
        if (prestamoDto.getNumeroCliente() == 10200200){
            return false;
        }
        //Para el cliente 20300300 se aprueba el prestamo si el monto es menor a 1000000
        if (prestamoDto.getMonto() < 1000000 && prestamoDto.getNumeroCliente() == 20300300){
            return true;
        }
        //Para el cliente 30400400 se aprueba el prestamo sin importar el monto
        if (prestamoDto.getPlazo() > 12 && prestamoDto.getNumeroCliente() == 30400400){
            return true;
        }
        return true;
    }

}
