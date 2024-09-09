package ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.GestionDeArchivos;

import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Cliente;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GuardarEnArchivoClientes {

    public static void guardarClientesEnCSV(ArrayList<Cliente> clientes, String archivoCSV) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoCSV))) {
            for (Cliente cliente : clientes) {
                bw.write(cliente.getTipoPersona() + "," + cliente.getNombre() + "," + cliente.getApellido() + "," + cliente.getDireccion() + "," + cliente.getEmail() + "," + cliente.getFechaNac() + "," + cliente.getFechaAlta() + "," + cliente.getDni());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
