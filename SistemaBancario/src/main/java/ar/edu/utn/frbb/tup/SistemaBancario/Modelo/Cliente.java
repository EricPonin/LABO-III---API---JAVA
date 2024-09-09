package ar.edu.utn.frbb.tup.SistemaBancario.Modelo;

import java.time.LocalDate;
import java.util.ArrayList;

import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.ClienteDto;

public class Cliente {
    private String tipoPersona;
    private String nombre;
    private String apellido;
    private String direccion;
    private String email;
    private LocalDate fechaNac;
    private LocalDate fechaAlta;
    private long dni;
    private ArrayList <Cuenta> cuentas;
    private ArrayList <Prestamo> prestamos;


    public Cliente(){
        this.cuentas = new ArrayList<>();
    }

    public Cliente(String tipoPersona, String nombre, String apellido, String direccion, String email, LocalDate fechaNac, long dni){
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

    public Cliente(String tipoPersona, String nombre, String apellido, String direccion, String email, LocalDate fechaNac,LocalDate fechaAlta, long dni){
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

    public Cliente(ClienteDto clienteDto) {
        tipoPersona = clienteDto.getTipoPersona();
        nombre = clienteDto.getNombre();
        apellido = clienteDto.getApellido();
        direccion = clienteDto.getDireccion();
        email = clienteDto.getEmail(); 
        fechaNac= LocalDate.parse(clienteDto.getFechaNac());
        fechaAlta = LocalDate.now();       
        dni = clienteDto.getDni();
        cuentas = new ArrayList<>();
        prestamos = new ArrayList<>();
    }

    public long getEdad(){
        return LocalDate.now().getYear() - fechaNac.getYear();
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

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public long getDni() {
        return dni;
    }

    public ArrayList<Prestamo> getPrestamos(){
        return prestamos;
    }

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
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

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    public void setCuentas(ArrayList<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public void setPrestamos(ArrayList<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    
    @Override
    public String toString(){
        return "Cliente{" + tipoPersona + ", " + nombre + ", " + apellido + ", " + direccion + ", " + email + ", " + fechaNac + ", " + fechaAlta + ", " + dni + "}";
    }

  
 

    // public void TransferirDeCuenta(Banco banco, Cliente cliente){
    //     VerCuentasCliente();
    //     int nroCuenta = Validaciones.IngresoEntero("Numero de cuenta a depositar: ");
    //     double monto = Validaciones.IngresoDouble("Ingrese monto a transferir: ");
    //     banco.Transferencia(cliente.getDni(),monto,nroCuenta);
    // }
   
    // public void verMovimientos(){
    //     int NroCuenta = Validaciones.IngresoEntero("Ingrese el numero de la cuenta para ver sus movimientos: ");
    //     for (Cuenta cuenta : cuentas) {
    //         if (cuenta.getNroCuenta() == NroCuenta) {
    //             cuenta.VerRegistros();
    //         }else{
    //             System.out.println("Cuenta no encontrada");
    //         }
    //     }
    // }

 

}
