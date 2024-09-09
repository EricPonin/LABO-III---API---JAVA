package ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Controller;

import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.PagoCuotaPrestamoDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.PrestamoDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.PrestamoResponseDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.PrestamosClienteResponseDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.validator.CuotaPrestamoValidator;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.validator.PrestamoValidator;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Excepciones.ClienteAlreadyExistsException;
import ar.edu.utn.frbb.tup.SistemaBancario.Servicio.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prestamo")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @Autowired
    private PrestamoValidator prestamoValidator;

    @Autowired
    private CuotaPrestamoValidator cuotaPrestamoValidator;
//---------------------------------------------------------------------------------------------------------------------
//EndPoint para solicitar un prestamo
    @PostMapping("/solicitar")
    public PrestamoResponseDto SolicitarPrestamo(@RequestBody PrestamoDto prestamoDto) throws ClienteAlreadyExistsException{
        prestamoValidator.validate(prestamoDto);
        return prestamoService.nuevoPrestamo(prestamoDto);

    }

//---------------------------------------------------------------------------------------------------------------------
//EndPoint para consultar un prestamo
    @GetMapping("/{ClienteId}")
    public PrestamosClienteResponseDto ConsultaPrestamo(@PathVariable long ClienteId){
        return prestamoService.consultarPrestamo(ClienteId);
    }

//---------------------------------------------------------------------------------------------------------------------
//EndPoint para pagar una cuota de un prestamo
    @PutMapping("/pagar")
    public PrestamoResponseDto DebitarCuota(@RequestBody PagoCuotaPrestamoDto pagoCuotaPrestamoDto){
        cuotaPrestamoValidator.validate(pagoCuotaPrestamoDto);
        return prestamoService.pagarCuota(pagoCuotaPrestamoDto);
    }

}

