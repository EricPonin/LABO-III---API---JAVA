package ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.GestionDeArchivos;

import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Cliente;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class LeerDeArchivoClientes {

    public static ArrayList<Cliente> leerClientesDeCSV(String archivoCSV) {
        ArrayList<Cliente> clientes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 8) {
                    try {
                        String tipoPersona = datos[0];
                        String nombre = datos[1];
                        String apellido = datos[2];
                        String direccion = datos[3];
                        String email = datos[4];
                        LocalDate fechaNac = LocalDate.parse(datos[5]); 
                        LocalDate fechaAlta = LocalDate.parse(datos[6]);
                        long dni = Long.parseLong(datos[7]);
                        Cliente cliente = new Cliente(tipoPersona, nombre, apellido, direccion, email, fechaNac,fechaAlta, dni);
                        clientes.add(cliente);
                    } catch (NumberFormatException e) {
                        System.err.println("Error al convertir DNI: " + datos[7] + " - " + e.getMessage());
                    } catch (Exception e) {
                        System.err.println("Error al procesar los datos del cliente: " + e.getMessage());
                    }
                } else {
                    System.err.println("Datos incompletos o incorrectos en la línea: " + linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientes;
    }
}
