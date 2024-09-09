package ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.GestionDeArchivos;

import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Registro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class LeerDeArchivoRegistros {

    public static ArrayList<Registro> leerRegistrosDeCSV(String archivoCSV) {
        ArrayList<Registro> registros = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                
                if (datos.length == 5) {
                    // Datos del registro
                    LocalDate fechaRegistro = LocalDate.parse(datos[1], formatter);
                    Registro registro = new Registro(Double.parseDouble(datos[0]), fechaRegistro, datos[2], Integer.parseInt(datos[3]), Integer.parseInt(datos[4]));
                    registros.add(registro);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return registros;
    }
}
