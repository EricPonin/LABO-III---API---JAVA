package ar.edu.utn.frbb.tup.SistemaBancario.Persistencia;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Prestamo;
import ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.GestionDeArchivos.GuardarEnArchivoPrestamos;
import ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.GestionDeArchivos.LeerDeArchivoPrestamos;

@Repository
public class PrestamoDao {

    
    String rutaArchivoPrestamos = "C:\\Users\\Eric\\Desktop\\SistemaBancario\\src\\main\\java\\ar\\edu\\utn\\frbb\\tup\\SistemaBancario\\Persistencia\\Archivos\\Prestamos.txt";

//---------------------------------------------------------------------------------------------------------------------
// Funcion que guarda un prestamo en el archivo de prestamos
    public boolean save(Prestamo prestamo){
        ArrayList<Prestamo> prestamos = LeerDeArchivoPrestamos.leerPrestamosDeCSV(rutaArchivoPrestamos);
        prestamos.add(prestamo);
        GuardarEnArchivoPrestamos.guardarPrestamosEnCSV(prestamos, rutaArchivoPrestamos);
        return true;
    }

//---------------------------------------------------------------------------------------------------------------------
// Funcion que devuelve un prestamo a partir de su numero de prestamo
    public Prestamo find(long numeroPrestamo){
        ArrayList<Prestamo> prestamos = LeerDeArchivoPrestamos.leerPrestamosDeCSV(rutaArchivoPrestamos);
        for(Prestamo prestamo : prestamos){
            if(prestamo.getNumeroPrestamo() == numeroPrestamo){
                return prestamo;
            }
        }
        return null;

    }

//---------------------------------------------------------------------------------------------------------------------
// Funcion que actualiza las cuotas pagadas de un prestamo
    public boolean updateCuota(Prestamo prestamoModificado){
        boolean bandera = false;
        ArrayList<Prestamo> prestamos = LeerDeArchivoPrestamos.leerPrestamosDeCSV(rutaArchivoPrestamos);
        for(Prestamo prestamo : prestamos){
            if(prestamo.getNumeroPrestamo() == prestamoModificado.getNumeroPrestamo()){
                if (prestamo.getPagosRealizados() != prestamo.getPlazo()){
                    prestamo.setMontoPagado(prestamoModificado.getMontoCuota());
                    prestamo.setPagosRealizados(prestamo.getPagosRealizados()+1);
                    bandera = true;
                }else{
                    bandera = false;
                }
            }
        }
        GuardarEnArchivoPrestamos.guardarPrestamosEnCSV(prestamos, rutaArchivoPrestamos);
        return bandera;
    }

//---------------------------------------------------------------------------------------------------------------------
// Funcion que devuelve todos los prestamos de un cliente
    public ArrayList<Prestamo> findAllCliente(long numeroCLiente){
        ArrayList<Prestamo> prestamos = LeerDeArchivoPrestamos.leerPrestamosDeCSV(rutaArchivoPrestamos);
        ArrayList<Prestamo> prestamosCliente = new ArrayList<Prestamo>();
        for(Prestamo prestamo : prestamos){
            if(prestamo.getNumeroCliente() == numeroCLiente){
                prestamosCliente.add(prestamo);
            }
        }
        return prestamosCliente;
    }



}
