package ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.GestionDeArchivos;

import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Cuenta;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LeerDeArchivoCuentas {

    public static ArrayList<Cuenta> leerCuentasDeCSV(String archivoCSV) {
        ArrayList<Cuenta> cuentas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 5) {
                    try {
                        long numeroCliente = Long.parseLong(datos[0]);
                        int nroCuenta = Integer.parseInt(datos[1]);
                        double saldo = Double.parseDouble(datos[2]);
                        String tipo = datos[3];
                        String moneda = datos[4];
                        
                        Cuenta cuenta = new Cuenta(numeroCliente, nroCuenta, saldo, tipo, moneda);
                        cuentas.add(cuenta);
                    } catch (NumberFormatException e) {
                        System.err.println("Error al convertir datos numéricos: " + e.getMessage());
                    } catch (Exception e) {
                        System.err.println("Error al procesar la cuenta: " + e.getMessage());
                    }
                } else {
                    System.err.println("Datos incompletos o incorrectos en la línea: " + linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cuentas;
    }
}
