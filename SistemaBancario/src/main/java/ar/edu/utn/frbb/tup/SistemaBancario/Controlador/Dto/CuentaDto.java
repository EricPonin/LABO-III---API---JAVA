package ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto;

import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Cuenta;

public class CuentaDto {

    private long titular;
    private int nroCuenta;
    private double saldo;
    private String TipoCuenta;
    private String tipoMoneda;


    public CuentaDto(Cuenta cuenta) {
        this.titular = cuenta.getTitular();
        this.nroCuenta = cuenta.getNroCuenta();
        this.saldo = cuenta.getSaldo();
        this.TipoCuenta = cuenta.getTipoCuenta();
        this.tipoMoneda = cuenta.getTipoMoneda();
    }

    public CuentaDto(int titular, int nroCuenta, double saldo, String TipoCuenta , String TipoMoneda) {
        this.titular = titular;
        this.nroCuenta = nroCuenta;
        this.saldo = saldo;
        this.TipoCuenta = TipoCuenta;
        this.tipoMoneda = TipoMoneda;
    }


    public long getTitular() {
        return titular;
    }

    public int getNroCuenta() {
        return nroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getTipoCuenta() {
        return TipoCuenta;
    }


    public void setTitular(long titular) {
        this.titular = titular;
    }

    public void setNroCuenta(int nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setTipoCuenta(String tipoCuenta) {
        TipoCuenta = tipoCuenta;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }
    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    
}
