package ar.edu.utn.frbb.tup.SistemaBancario.Modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Registro {
   
    private double monto;
    private LocalDate fecha;
    private String movimiento;
    private int cuentaOrigen;
    private int cuentaDestino;

    public Registro(double monto, LocalDate fecha, String movimiento, int cuentaOrigen, int cuentaDestino) {
        this.monto = monto;
        this.fecha = fecha;
        this.movimiento = movimiento;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
    }
    public Registro(double monto, LocalDate fecha, String movimiento, int cuentaOrigen) {
        this.monto = monto;
        this.fecha = fecha;
        this.movimiento = movimiento;
        this.cuentaOrigen = cuentaOrigen;
    }

    public Registro(double monto, LocalDateTime fechaHora, String movimiento, int cuentaOrigen, int cuentaDestino) {
        this.monto = monto;
        this.movimiento = movimiento;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
    }

    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public String getMovimiento() {
        return movimiento;
    }
    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }
    public int getCuentaOrigen() {
        return cuentaOrigen;
    }
    public void setCuentaOrigen(int cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }
    public int getCuentaDestino() {
        return cuentaDestino;
    }
    public void setCuentaDestino(int cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

}