package ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto;

public class PrestamoDto {
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

    public PrestamoDto(){}

    public PrestamoDto(long numeroPrestamo, long numeroCliente, int plazo, double monto, double interes, double montoCuota, String estado, String moneda) {
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

    public void setInteres(double intereses) {
        this.interes = intereses;
    }

    public void setCuotaNro(double montoCuota) {
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

    


    
}
