package ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Controller;

import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.ClienteDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.validator.ClienteValidator;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Excepciones.ClienteAlreadyExistsException;
import ar.edu.utn.frbb.tup.SistemaBancario.Servicio.ClienteService;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Cliente;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteValidator clienteValidator;

//--------------------------------------------------------------------------------------------------------------------- 
//EndPoint para listar todos los clientes
    @GetMapping("/listar")
    public ArrayList<ClienteDto> getClientes() {
        return clienteService.ListarClientes();
    }

//---------------------------------------------------------------------------------------------------------------------
 //EndPoint para crear un cliente
    @PostMapping("/alta")
    public Cliente crearCliente(@RequestBody ClienteDto clienteDto) throws ClienteAlreadyExistsException {
        clienteValidator.validate(clienteDto);
        return clienteService.darDeAltaCliente(clienteDto);
    }

//---------------------------------------------------------------------------------------------------------------------
//EndPoint para borrar un cliente
    @DeleteMapping("/baja/{id}")
    public ClienteDto borrarCliente(@PathVariable long id){
       return clienteService.darDeBajaCliente(id);
    }

//---------------------------------------------------------------------------------------------------------------------
//EndPoint para buscar un cliente por id(DNI)
    @GetMapping("/{id}")
    public ClienteDto verClientePorId(@PathVariable long id) {
        return clienteService.buscarClientePorDni(id);
    }

}
