package ar.edu.utn.frbb.tup.SistemaBancario.Servicio;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.CuentaDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Cliente;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Cuenta;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Excepciones.ClienteAlreadyExistsException;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Excepciones.CuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.ClienteDao;
import ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.CuentaDao;


@Service
public class CuentaService {

    CuentaDao cuentaDao = new CuentaDao();
    ClienteDao clienteDao = new ClienteDao();

//---------------------------------------------------------------------------------------------------------------------
//Agregar una cuenta por JSON
public Cuenta agregarCuenta(CuentaDto cuentaDto) throws CuentaAlreadyExistsException, ClienteAlreadyExistsException {
    Cliente cliente = clienteDao.find(cuentaDto.getTitular());
    if (cliente == null) {
        throw new ClienteAlreadyExistsException("El cliente no existe");
    }
    ArrayList<Cuenta> cuentasCliente = cuentaDao.findAll();
    // El cliente solo puede tener una cuenta en cada tipo de moneda
    
    for (Cuenta c : cuentasCliente) {
        if (c.getTitular() == cuentaDto.getTitular()) {
            if (c.getTipoMoneda().equalsIgnoreCase(cuentaDto.getTipoMoneda())) {
                throw new CuentaAlreadyExistsException("El cliente ya posee una cuenta en " + cuentaDto.getTipoMoneda());
            }
        }
    }
    // Controlar que la cuenta no exista por número de cuenta
    Cuenta cuentaExistente = cuentaDao.find(cuentaDto.getNroCuenta());
    if (cuentaExistente == null) {
        Cuenta nuevaCuenta = new Cuenta(cuentaDto);
        cuentaDao.save(nuevaCuenta);
        return nuevaCuenta;
    } else {
        throw new CuentaAlreadyExistsException("Ya existe una cuenta con el número: " + cuentaDto.getNroCuenta());
    }
}


//---------------------------------------------------------------------------------------------------------------------
// Elimina una cuenta por numero de cuenta
    public CuentaDto eliminarCuenta(int NroCta) throws CuentaAlreadyExistsException {
        Cuenta cuenta = cuentaDao.find(NroCta);
        if (cuenta != null) {
            cuentaDao.delete(NroCta);
            return new CuentaDto(cuenta);
        }else{
            throw new CuentaAlreadyExistsException("No existe una cuenta con el numero: " + NroCta);
        }
    }

//---------------------------------------------------------------------------------------------------------------------
// Listar Cuentas Funcion para JSON
    public ArrayList<CuentaDto> ListarCuentasDto(){
        ArrayList<CuentaDto> listaCuentas = new ArrayList<>();
        for (Cuenta cuenta : cuentaDao.findAll()) {
            CuentaDto cuentaDto = new CuentaDto(cuenta);
            listaCuentas.add(cuentaDto);
        }
        return listaCuentas;
    }

//---------------------------------------------------------------------------------------------------------------------
// Funcion para depositar en la cuenta
    public boolean deposito(long numeroCliente, String moneda, double monto){
        cuentaDao.depositarEnCuenta(numeroCliente, moneda, monto); 
        return true;
    }

//---------------------------------------------------------------------------------------------------------------------
// Funcion para retirar de la cuenta
    public boolean debitoCuota(long numeroCliente, String moneda, double monto){
        cuentaDao.retirarDeCuenta(numeroCliente, moneda, monto);
        return true;
    }

//---------------------------------------------------------------------------------------------------------------------
// Funcion para ver la cuenta
    public CuentaDto verCuenta(int NroCuenta) throws CuentaAlreadyExistsException{
        Cuenta cuenta = cuentaDao.find(NroCuenta);
        if (cuenta != null) {
            return new CuentaDto(cuenta);
        }else{
            throw new CuentaAlreadyExistsException("No existe un a cuenta con el numero: " + NroCuenta);
            }
        }

}
