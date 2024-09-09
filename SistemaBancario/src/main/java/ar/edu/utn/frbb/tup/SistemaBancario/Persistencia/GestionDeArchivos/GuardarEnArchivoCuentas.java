package ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.GestionDeArchivos;

import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Cuenta;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class GuardarEnArchivoCuentas {

    public static void guardarCuentaEnCSV(ArrayList<Cuenta> cuentas, String archivoCSV) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoCSV))) {
            for (Cuenta cuenta : cuentas){
                bw.write(cuenta.getTitular() + "," + cuenta.getNroCuenta() + "," + cuenta.getSaldo() + "," + cuenta.getTipoCuenta() +  "," + cuenta.getTipoMoneda());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
