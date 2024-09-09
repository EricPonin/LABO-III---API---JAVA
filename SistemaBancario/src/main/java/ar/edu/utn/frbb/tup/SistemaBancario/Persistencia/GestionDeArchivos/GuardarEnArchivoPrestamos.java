package ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.GestionDeArchivos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Prestamo;

public class GuardarEnArchivoPrestamos {

    public static void guardarPrestamosEnCSV(ArrayList<Prestamo> prestamos, String archivoCSV) {

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoCSV))) {
                for (Prestamo prestamo : prestamos) {
                    bw.write(prestamo.toString());
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    
}
