package ar.edu.utn.frbb.tup.SistemaBancario.Persistencia;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Cliente;
import ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.GestionDeArchivos.LeerDeArchivoClientes;
import ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.GestionDeArchivos.GuardarEnArchivoClientes;

@Repository
public class ClienteDao {

    String rutaArchivoClientes = "C:\\Users\\Eric\\Desktop\\SistemaBancario\\src\\main\\java\\ar\\edu\\utn\\frbb\\tup\\SistemaBancario\\Persistencia\\Archivos\\Clientes.txt";

//---------------------------------------------------------------------------------------------------------------------
// Este metodo devuelve un cliente a partir de su numero de cliente
    public Cliente find(long dni) {
        ArrayList<Cliente> clientes = LeerDeArchivoClientes.leerClientesDeCSV(rutaArchivoClientes);

        for (Cliente cliente : clientes) {
            if (cliente.getDni() == dni) {
                return cliente;
            }
        }
        return null;
    }

//---------------------------------------------------------------------------------------------------------------------
//  Este metodo guarda un cliente en el archivo de clientes
    public void save(Cliente cliente){
        ArrayList<Cliente> clientes = LeerDeArchivoClientes.leerClientesDeCSV(rutaArchivoClientes);
        clientes.add(cliente);
        GuardarEnArchivoClientes.guardarClientesEnCSV(clientes, rutaArchivoClientes);
    }

    public void update(Cliente cliente){
       
    }

//---------------------------------------------------------------------------------------------------------------------
// Este metodo elimina un cliente a partir de su numero de cliente
    public void delete(long dni){
        ArrayList<Cliente> clientes = LeerDeArchivoClientes.leerClientesDeCSV(rutaArchivoClientes);
        clientes.removeIf(cliente -> cliente.getDni() == dni);
        GuardarEnArchivoClientes.guardarClientesEnCSV(clientes, rutaArchivoClientes);
    }

//---------------------------------------------------------------------------------------------------------------------
// Este metodo devuelve todos los clientes
    public ArrayList<Cliente> findAll(){
        return LeerDeArchivoClientes.leerClientesDeCSV(rutaArchivoClientes);
    }
    
}
