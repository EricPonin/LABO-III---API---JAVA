package ar.edu.utn.frbb.tup.SistemaBancario.Persistencia;

import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Cuenta;
import ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.GestionDeArchivos.GuardarEnArchivoCuentas;
import ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.GestionDeArchivos.LeerDeArchivoCuentas;

@Repository
public class CuentaDao {

    String rutaArchivoCuentas ="C:\\Users\\Eric\\Desktop\\SistemaBancario\\src\\main\\java\\ar\\edu\\utn\\frbb\\tup\\SistemaBancario\\Persistencia\\Archivos\\Cuentas.txt";

//---------------------------------------------------------------------------------------------------------------------
// Agregar una cuenta al archivo cuentas
    public void save(Cuenta cuenta){
        ArrayList<Cuenta> cuentas = LeerDeArchivoCuentas.leerCuentasDeCSV(rutaArchivoCuentas);
        cuentas.add(cuenta);
        GuardarEnArchivoCuentas.guardarCuentaEnCSV(cuentas, rutaArchivoCuentas);
    }

//---------------------------------------------------------------------------------------------------------------------

public Cuenta find(long numeroCliente, String moneda) {
    for (Cuenta cuenta : LeerDeArchivoCuentas.leerCuentasDeCSV(rutaArchivoCuentas)) {
        if (cuenta.getTitular() == numeroCliente && moneda.equals(cuenta.getTipoMoneda())) {
            return cuenta;
        }
    }
    return null;
}
//---------------------------------------------------------------------------------------------------------------------
// Buscar una cuenta por su numero
public Cuenta find(int Cta) {
    ArrayList<Cuenta> cuentas = LeerDeArchivoCuentas.leerCuentasDeCSV(rutaArchivoCuentas);
    for (Cuenta cuenta : cuentas) {
        if (cuenta.getNroCuenta() == Cta) {
            return cuenta;
        }
    }
    return null;
}

//---------------------------------------------------------------------------------------------------------------------
// Depositar en una cuenta
    public void depositarEnCuenta(long numeroCliente, String moneda, double monto){
        ArrayList<Cuenta> cuentas = LeerDeArchivoCuentas.leerCuentasDeCSV(rutaArchivoCuentas);
        for (Cuenta cuenta : cuentas) {
            if(cuenta.getTitular() == numeroCliente && cuenta.getTipoMoneda().equals(moneda)) {
                cuenta.setSaldo(cuenta.getSaldo() + monto);
            }
        }
        GuardarEnArchivoCuentas.guardarCuentaEnCSV(cuentas, rutaArchivoCuentas);
    }

//---------------------------------------------------------------------------------------------------------------------
// Retirar de una cuenta
    public void retirarDeCuenta(long numeroCliente, String moneda, double monto){
        ArrayList<Cuenta> cuentas = LeerDeArchivoCuentas.leerCuentasDeCSV(rutaArchivoCuentas);
        for (Cuenta cuenta : cuentas) {
            if(cuenta.getTitular() == numeroCliente && cuenta.getTipoMoneda().equals(moneda)) {
                cuenta.setSaldo(cuenta.getSaldo() - monto);
            }
        }
        GuardarEnArchivoCuentas.guardarCuentaEnCSV(cuentas, rutaArchivoCuentas);
    }

    public ArrayList<Cuenta> findAll(){
        return LeerDeArchivoCuentas.leerCuentasDeCSV(rutaArchivoCuentas);
    }
    

//---------------------------------------------------------------------------------------------------------------------
// Eliminar una cuenta del archivo cuentas
    public void delete(int Cta){
        ArrayList<Cuenta> cuentas = LeerDeArchivoCuentas.leerCuentasDeCSV(rutaArchivoCuentas);
        cuentas.removeIf(cuenta -> cuenta.getNroCuenta()== Cta);
        GuardarEnArchivoCuentas.guardarCuentaEnCSV(cuentas, rutaArchivoCuentas);
    }



    
}