package ar.edu.utn.frbb.tup.SistemaBancario.Servicio;

import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.ClienteDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Cliente;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Excepciones.ClienteAlreadyExistsException;
import ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.ClienteDao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteDao clienteDao;

//---------------------------------------------------------------------------------------------------------------------

    public Cliente buscarCliente(long dni){
        return clienteDao.find(dni);
    }

//---------------------------------------------------------------------------------------------------------------------
// Funcion que recibe un Dto para dar de alta un cliente hace algunos controles de negocio y lo guarda en el archivo mediante el CienteDao
    public Cliente darDeAltaCliente(ClienteDto clienteDto) throws ClienteAlreadyExistsException {
        Cliente cliente = new Cliente(clienteDto);

        if (clienteDao.find(cliente.getDni()) != null) {
            throw new ClienteAlreadyExistsException("Ya existe un cliente con DNI " + cliente.getDni());
        }

        if (cliente.getEdad() < 18) {
            throw new IllegalArgumentException("El cliente debe ser mayor a 18 años");
        }

        clienteDao.save(cliente);
        return cliente;
    }

//---------------------------------------------------------------------------------------------------------------------
// Funcion que busca los clientes en el archivo llamando al Dao y retorna un ArrayList de clienteDto
    public ArrayList<ClienteDto> ListarClientes(){
        ArrayList<ClienteDto> listaClientes = new ArrayList<>();
        for (Cliente cliente : clienteDao.findAll()) {
            ClienteDto clienteDto = new ClienteDto(cliente);
            listaClientes.add(clienteDto);           
        }
        return listaClientes;
    }

//---------------------------------------------------------------------------------------------------------------------
// Funcion que busca un cliente llamando al Dao y si lo encuentra lo elimina
    public ClienteDto darDeBajaCliente(long dni) throws ClienteAlreadyExistsException {
        Cliente cliente = clienteDao.find(dni);
        if (cliente != null) {
            clienteDao.delete(dni);
            return new ClienteDto(cliente);
        }else{
            throw new ClienteAlreadyExistsException("No existe un cliente con DNI " + dni);
        }
    }

//---------------------------------------------------------------------------------------------------------------------
// Funcion que busca un cliente por DNI pasado por parametro y si lo encuentra lo retorna en un clienteDto
    public ClienteDto buscarClientePorDni(long dni) throws ClienteAlreadyExistsException {
        Cliente cliente = clienteDao.find(dni);
        if (cliente != null) {
            return new ClienteDto(cliente);
        }else{
            throw new ClienteAlreadyExistsException("No existe un cliente con DNI " + dni);
        }
    }
    
}
