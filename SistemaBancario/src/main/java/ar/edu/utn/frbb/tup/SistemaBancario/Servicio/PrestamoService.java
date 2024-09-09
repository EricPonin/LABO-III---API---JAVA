package ar.edu.utn.frbb.tup.SistemaBancario.Servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.ConsultaPrestamoDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.PagoCuotaPrestamoDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.PlanPagoDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.PrestamoDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.PrestamoResponseDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Controlador.Dto.PrestamosClienteResponseDto;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Prestamo;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Excepciones.ClienteAlreadyExistsException;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Excepciones.CuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.SistemaBancario.Modelo.Excepciones.PrestamoAlreadyExistsException;
import ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.ClienteDao;
import ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.CuentaDao;
import ar.edu.utn.frbb.tup.SistemaBancario.Persistencia.PrestamoDao;

@Service
public class PrestamoService {
    
    @Autowired
    private PrestamoDao prestamoDao;

    @Autowired
    private CuentaDao cuentaDao;

    @Autowired
    private ClienteDao clienteDao;

    @Autowired
    private CuentaService cuentaService;

//-------------------------------------------------------------------------------------------------------------------------------------------------------

    public PrestamoResponseDto nuevoPrestamo(PrestamoDto prestamoDto)throws ClienteAlreadyExistsException, CuentaAlreadyExistsException{
        Prestamo nuevoPrestamo = new Prestamo(prestamoDto);
        PrestamoResponseDto prestamoResponseDto = new PrestamoResponseDto();

        // Cambiar esta clase  con los parametros que dicen en la guia..
        ScoreCrediticioService scoreCrediticioService = new ScoreCrediticioService();
        // Verifico que el cliente tenga una cuenta en esa moneda
        if (cuentaDao.find(nuevoPrestamo.getNumeroCliente(),nuevoPrestamo.getMoneda()) == null) {
            throw new CuentaAlreadyExistsException("El cliente no tiene una cuenta en esa moneda.. " );
        }
        // Si existe el cliente sigo adelante con el prestamo
        if (clienteDao.find(prestamoDto.getNumeroCliente()) != null) {
                // Si se autoriza mediante el score crediticio proseguimos con el prestamo
                if (scoreCrediticioService.calcularScoreCrediticio(prestamoDto)) {
                    //Aprobado el presatamo seteo el estado y los intereses del mismo
                    nuevoPrestamo.setEstado("Aprobado");
                    double intereses = (nuevoPrestamo.getPlazo() / 12) * 0.05 * nuevoPrestamo.getMonto();
                    nuevoPrestamo.setInteres(intereses);
                    nuevoPrestamo.setMontoCuota((nuevoPrestamo.getMonto()+nuevoPrestamo.getInteres())/nuevoPrestamo.getPlazo());
                    prestamoDao.save(nuevoPrestamo);
                    //Hago el deposito del monto del prestamo en la cuenta del cliente
                    cuentaService.deposito(nuevoPrestamo.getNumeroCliente(), nuevoPrestamo.getMoneda(), nuevoPrestamo.getMonto());
                    //Genero el plan de pagos
                    List<PlanPagoDto> planPagos = new ArrayList<>();
                    for (int i = 0; i < nuevoPrestamo.getPlazo(); i++) {
                        PlanPagoDto cuota = new PlanPagoDto(i+1, nuevoPrestamo.getMontoCuota());
                        planPagos.add(cuota);
                    }
                    prestamoResponseDto.setEstado("Aprobado");
                    prestamoResponseDto.setMensaje("El monto del prestamo fue acreditado en su cuenta");
                    prestamoResponseDto.setPlanPagos(planPagos);
                    return prestamoResponseDto;
                }else{
                nuevoPrestamo.setEstado("Rechazado");
                prestamoDao.save(nuevoPrestamo);
                prestamoResponseDto.setEstado("Rechazado");
                prestamoResponseDto.setMensaje("El cliente no tiene Score Crediticio suficiente para el prestamo.. " );
                return prestamoResponseDto;
                }
        }else{
                throw new ClienteAlreadyExistsException("El cliente no existe.. " );
            }
        
    }

//-------------------------------------------------------------------------------------------------------------------------------------------------------
// Funcion para realizar el pago de una cuota de un prestamo especifico datos pasados por JSON
    public PrestamoResponseDto pagarCuota(PagoCuotaPrestamoDto pagoCuotaPrestamoDto)throws ClienteAlreadyExistsException, CuentaAlreadyExistsException{
        PrestamoResponseDto prestamoResponseDto = new PrestamoResponseDto();

        if (clienteDao.find(pagoCuotaPrestamoDto.getNumeroCliente()) == null) {
            throw new ClienteAlreadyExistsException("El cliente no existe.. " );
        }
        
        if (cuentaDao.find(pagoCuotaPrestamoDto.getNumeroCliente(),pagoCuotaPrestamoDto.getMoneda()) == null) {
            throw new CuentaAlreadyExistsException("El cliente no tiene una cuenta en esa moneda.. " );
        }

        if (prestamoDao.find(pagoCuotaPrestamoDto.getNumeroPrestamo())!= null){
            Prestamo prestamo = prestamoDao.find(pagoCuotaPrestamoDto.getNumeroPrestamo());

           if( prestamoDao.updateCuota(prestamo)){
                //debitar de la cuenta del cliente el monto de la cuota
                cuentaService.debitoCuota(prestamo.getNumeroCliente(), prestamo.getMoneda(), prestamo.getMontoCuota());
           }else{
                throw new IllegalArgumentException("No hay cuotas pendientes para pagar.. " );
           }
            
            
           //Genero el plan de pagos para mostrar la respuesta JSON
            List<PlanPagoDto> planPagos = new ArrayList<>();
            for (int i = prestamo.getPagosRealizados()+1; i < prestamo.getPlazo(); i++) {
                PlanPagoDto cuota = new PlanPagoDto(i+1, prestamo.getMontoCuota());
                planPagos.add(cuota);
            }
            prestamoResponseDto.setEstado("Aprobado");
            prestamoResponseDto.setMensaje("La cuota fue debitada de su cuenta, Restantes:");
            prestamoResponseDto.setPlanPagos(planPagos);
            return prestamoResponseDto;
        }
        else{ 
            throw new PrestamoAlreadyExistsException("El prestamo no existe.. " );
        }
    }

//-------------------------------------------------------------------------------------------------------------------------------------------------------
// Funcion para consultar todos los prestamos de un cliente pasado por parametro
    public PrestamosClienteResponseDto consultarPrestamo(long numeroCliente) throws ClienteAlreadyExistsException{
        PrestamosClienteResponseDto prestamosClienteResponseDto = new PrestamosClienteResponseDto();
        ArrayList<ConsultaPrestamoDto> prestamosDto = new ArrayList<>();
        
        if (clienteDao.find(numeroCliente) != null) {
           ArrayList<Prestamo> prestamosCliente = prestamoDao.findAllCliente(numeroCliente);

            for (Prestamo prestamo : prestamosCliente){
                ConsultaPrestamoDto presDto = new ConsultaPrestamoDto();
                presDto.setIdPrestamo(prestamo.getNumeroPrestamo());
                presDto.setMoneda(prestamo.getMoneda());
                presDto.setMonto(prestamo.getMonto());
                presDto.setPlazoMeses(prestamo.getPlazo());
                presDto.setPagosRealizados(prestamo.getPagosRealizados());
                presDto.setSaldoRestante(prestamo.getMonto()-(prestamo.getMontoCuota()*prestamo.getPagosRealizados()));
                prestamosDto.add(presDto);
                
            }
            prestamosClienteResponseDto.setNumeroCliente(numeroCliente);
            prestamosClienteResponseDto.setConsultaPrestamos(prestamosDto);
            return prestamosClienteResponseDto;
        }else{
            throw new ClienteAlreadyExistsException("El cliente no existe.. " );
        }

    }


}
