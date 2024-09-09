package ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.GestionDeArchivos;

import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Banco;

public class InicializarBaseSistema {

    public void InicializarBaseDeDatos(Banco banco) {

        String rutaArchivoClientes = "G:\\Mi unidad\\UTN\\Laboratorio3\\Sistema-Bancario-Labo3-Maven\\Sistema-Bancario\\src\\main\\java\\ar\\edu\\utn\\frbb\\tup\\Persistencia\\Archivos\\Clientes.txt";
        String rutaArchivoRegistros = "G:\\Mi unidad\\UTN\\Laboratorio3\\Sistema-Bancario-Labo3-Maven\\Sistema-Bancario\\src\\main\\java\\ar\\edu\\utn\\frbb\\tup\\Persistencia\\Archivos\\Registros.txt";

        banco.setListaClientes(LeerDeArchivoClientes.leerClientesDeCSV(rutaArchivoClientes));
       // banco.setListaCuentas(LeerDeArchivoCuentas.leerCuentasDeCSV(rutaArchivoCuentas));
        banco.setListaRegistros(LeerDeArchivoRegistros.leerRegistrosDeCSV(rutaArchivoRegistros));

        
    }
    
}
