package ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.GestionDeArchivos;

import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Registro;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GuardarEnArchivoRegistros {

    public static void guardarRegistrosEnCSV(ArrayList<Registro> registros, String archivoCSV) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoCSV))) {
            for (Registro registro : registros) {
                // Escribo los datos del registro
                bw.write(registro.getMonto() + "," + registro.getFecha().format(formatter) + "," + registro.getMovimiento() + "," + registro.getCuentaOrigen() + "," + registro.getCuentaDestino());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
