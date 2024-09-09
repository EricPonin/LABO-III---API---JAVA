package ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Cliente;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Cuenta;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Prestamo;

public class ClienteDto extends PersonaDto{
    private String tipoPersona;
    private String nombre;
    private String apellido;
    private String direccion;
    private String email;
    private String fechaNac;
    private LocalDate fechaAlta;
    private long dni;
    private ArrayList <Cuenta> cuentas;
    private ArrayList <Prestamo> prestamos;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public ClienteDto(Cliente cliente) {
        this.tipoPersona = cliente.getTipoPersona();
        this.nombre = cliente.getNombre();
        this.apellido = cliente.getApellido();
        this.direccion = cliente.getDireccion();
        this.email = cliente.getEmail();
        this.fechaNac = cliente.getFechaNac().format(formatter);
        this.fechaAlta = cliente.getFechaAlta();
        this.dni = cliente.getDni();
        this.cuentas = cliente.getCuentas();
        this.prestamos = cliente.getPrestamos();
    }


    public ClienteDto(String tipoPersona, String nombre, String apellido, String direccion, String email, String fechaNac, int dni) {
        this.tipoPersona = tipoPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.email = email;
        this.fechaNac = fechaNac; 
        this.fechaAlta = LocalDate.now();
        this.dni = dni;
        this.cuentas = new ArrayList<>();
        this.prestamos = new ArrayList<>();
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public long getDni() {
        return dni;
    }

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public ArrayList<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamo(ArrayList<Prestamo> prestamos){
        this.prestamos = prestamos;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setCuentas(ArrayList<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

  
}
