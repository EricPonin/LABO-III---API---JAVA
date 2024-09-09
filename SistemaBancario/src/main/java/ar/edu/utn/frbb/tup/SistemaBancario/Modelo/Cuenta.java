package ar.edu.utn.frbb.tup.SistemaBancario.Modelo;

import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.CuentaDto;

public class Cuenta {

    private long titular;
    private int nroCuenta;
    private double saldo;
    private String TipoCuenta;
    private String tipoMoneda;



    public Cuenta(CuentaDto cuentaDto){
        titular = cuentaDto.getTitular();
        nroCuenta = cuentaDto.getNroCuenta();
        saldo = cuentaDto.getSaldo();
        TipoCuenta = cuentaDto.getTipoCuenta();
        tipoMoneda = cuentaDto.getTipoMoneda();
    }

    public Cuenta(long titular, int nroCuenta, double saldo, String TipoCuenta , String tipoMoneda) {
        this.titular = titular;
        this.nroCuenta = nroCuenta;
        this.saldo = saldo;
        this.TipoCuenta = TipoCuenta;
        this.tipoMoneda = tipoMoneda;
    }


    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public double getSaldo() {
        return saldo;
    }
    public String getTipoCuenta() {
        return TipoCuenta;
    }
    public void setTipo(String TipoCuenta){
        this.TipoCuenta = TipoCuenta;
    }
    public long getTitular() {
        return titular;
    }
    public void setTitular(int titular) {
        this.titular = titular;
    }
    public int getNroCuenta() {
        return nroCuenta;
    }
    public void setNroCuenta(int nroCliente) {
        this.nroCuenta = nroCliente;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public String toString() {
        return "Cuenta [nroCuenta=" + nroCuenta + ", saldo=" + saldo + ", TipoCuenta=" + TipoCuenta + ", fecha=" + tipoMoneda + "]";
    }

}
