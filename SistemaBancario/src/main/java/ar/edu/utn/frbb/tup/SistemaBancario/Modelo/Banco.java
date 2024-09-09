package ar.edu.utn.frbb.tup.SistemaBancario.Modelo;

import java.util.ArrayList;


public class Banco {
    private ArrayList<Cliente> clientes;
    private ArrayList<Cuenta> cuentas;
    private ArrayList<Registro> registros;


    public Banco(){
        this.clientes = new ArrayList<>();
        this.cuentas = new ArrayList<>();
        this.registros = new ArrayList<>();
    }

    public ArrayList<Cliente> getListaClientes() {
        return clientes;
    }

    public ArrayList<Cuenta> getListaCuentas() {
        return cuentas;
    }

    public ArrayList<Registro> getListaRegistros() {
        return registros;
    }
  
    public void setListaClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setListaCuentas(ArrayList<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public void setListaRegistros(ArrayList<Registro> registros) {
        this.registros = registros;
    }

}
