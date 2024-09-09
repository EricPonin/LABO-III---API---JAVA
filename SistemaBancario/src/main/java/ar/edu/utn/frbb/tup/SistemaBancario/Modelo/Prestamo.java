package ar.edu.utn.frbb.tup.SistemaBancario.Modelo;

import java.util.Random;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.PrestamoDto;

public class Prestamo {
    private long numeroPrestamo;
    private long numeroCliente;
    private int plazo;
    private double monto;
    private double interes;
    private double montoCuota;
    private int pagosRealizados;
    private double montoPagado;
    private String estado;
    private String moneda;
    private static final Random random = new Random(); 

    public Prestamo(long numeroPrestamo, long numeroCliente, int plazo, double monto, double interes, double montoCuota, String estado, String moneda) {
        this.numeroPrestamo = numeroPrestamo;
        this.numeroCliente = numeroCliente;
        this.plazo = plazo;
        this.monto = monto;
        this.interes = interes;
        this.montoCuota = montoCuota;
        this.montoPagado = 0.0;
        this.pagosRealizados = 0;
        this.estado = estado;
        this.moneda = moneda;
    }

    public Prestamo(PrestamoDto prestamoDto) {
        numeroPrestamo = 10000 + random.nextInt(90000);
        numeroCliente = prestamoDto.getNumeroCliente();
        plazo = prestamoDto.getPlazo();
        monto = prestamoDto.getMonto();
        interes = prestamoDto.getInteres();
        montoCuota = prestamoDto.getMontoCuota();
        montoPagado = 0.0;
        pagosRealizados = 0;
        estado = prestamoDto.getEstado();
        moneda = prestamoDto.getMoneda();
    }

    public long getNumeroPrestamo() {
        return numeroPrestamo;
    }
    public long getNumeroCliente() {
        return numeroCliente;
    }

    public int getPlazo() {
        return plazo;
    }

    public double getMonto() {
        return monto;
    }

    public double getInteres() {
        return interes;
    }

    public double getMontoCuota() {
        return montoCuota;
    }
    public int getPagosRealizados() {
        return pagosRealizados;
    }
    public double getMontoPagado() {
        return montoPagado;
    }

    public String getEstado() {
        return estado;
    }

    public String getMoneda() {
        return moneda;
    }
    public void setNumeroPrestamo(long numeroPrestamo) {
        this.numeroPrestamo = numeroPrestamo;
    }

    public void setNumeroCliente(long numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    public void setInteres(double interes) {
        this.interes = interes;
    }

    public void setMontoCuota(double montoCuota) {
        this.montoCuota = montoCuota;
    }

    public void setPagosRealizados(int pagosRealizados) {
        this.pagosRealizados = pagosRealizados;
    }
    public void setMontoPagado(double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
    
    @Override
    public String toString(){
        return numeroPrestamo +"," + numeroCliente +","+ plazo +"," + monto +"," + interes +"," + montoCuota +"," + pagosRealizados +"," + montoPagado +"," + estado +"," + moneda;
    }
    
}
