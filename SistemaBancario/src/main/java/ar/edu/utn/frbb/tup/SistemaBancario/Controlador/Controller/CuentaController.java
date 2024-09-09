package ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.CuentaDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.validator.CuentaValidator;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Cuenta;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Excepciones.CuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.SistemaBancario.Servicio.CuentaService;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {



    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private CuentaValidator cuentaValidator;
//---------------------------------------------------------------------------------------------------------------------
// EndPointt para listar todas las cuentas
    @GetMapping("/listar")
    public ArrayList<CuentaDto> getCuentas() {
        return cuentaService.ListarCuentasDto();
    }

//---------------------------------------------------------------------------------------------------------------------
// EndPoint para dar de alta una cuenta
    @PostMapping("/alta")
    public Cuenta altaCuenta(@RequestBody CuentaDto cuentaDto) throws CuentaAlreadyExistsException{
        cuentaValidator.validate(cuentaDto);
        return cuentaService.agregarCuenta(cuentaDto);
    }

//---------------------------------------------------------------------------------------------------------------------
// EndPoint para buscar una cuenta por su numero de cuenta
    @GetMapping("/{NroCuenta}")
    public CuentaDto getCuenta(@PathVariable int NroCuenta) {
        return cuentaService.verCuenta(NroCuenta);
    }

//---------------------------------------------------------------------------------------------------------------------
//EndPoint para borrar una Cuenta
    @DeleteMapping("/baja/{NroCta}")
    public CuentaDto borrarCuenta(@PathVariable int NroCta){
       return cuentaService.eliminarCuenta(NroCta);
    }

    
    
















}
