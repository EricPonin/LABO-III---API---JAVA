package ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.GestionDeArchivos;

import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Prestamo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LeerDeArchivoPrestamos {

    public static ArrayList<Prestamo> leerPrestamosDeCSV(String archivoCSV) {
        ArrayList<Prestamo> prestamos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 10) {
                    // Datos del préstamo
                    long numeroPrestamo = Long.parseLong(datos[0]);
                    long numeroCliente = Long.parseLong(datos[1]);
                    int plazo = Integer.parseInt(datos[2]);
                    double monto = Double.parseDouble(datos[3]);
                    double interes = Double.parseDouble(datos[4]);
                    double montoCuota = Double.parseDouble(datos[5]);
                    int pagosRealizados = Integer.parseInt(datos[6]);
                    double montoPagado = Double.parseDouble(datos[7]);
                    String estado = datos[8];
                    String moneda = datos[9];
                    
                    Prestamo prestamo = new Prestamo(numeroPrestamo, numeroCliente, plazo, monto, interes, montoCuota, estado, moneda);
                    prestamo.setPagosRealizados(pagosRealizados);
                    prestamo.setMontoPagado(montoPagado);
                    prestamos.add(prestamo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prestamos;
    }
}
