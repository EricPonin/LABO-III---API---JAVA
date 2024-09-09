package ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto;

import java.util.List;

public class PrestamosClienteResponseDto {
    
    private long numeroCliente;
    private List<ConsultaPrestamoDto> consultaPrestamos;


    public PrestamosClienteResponseDto() {
    }

    public PrestamosClienteResponseDto(long numeroCliente, List<ConsultaPrestamoDto> consultaPrestamos) {
        this.numeroCliente = numeroCliente;
        this.consultaPrestamos = consultaPrestamos;
    }



    public long getNumeroCliente() {
        return numeroCliente;
    }



    public void setNumeroCliente(long numeroCliente) {
        this.numeroCliente = numeroCliente;
    }



    public List<ConsultaPrestamoDto> getConsultaPrestamos() {
        return consultaPrestamos;
    }



    public void setConsultaPrestamos(List<ConsultaPrestamoDto> consultaPrestamos) {
        this.consultaPrestamos = consultaPrestamos;
    }



    
    
}
