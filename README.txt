-------------------------------------------------------------------
SECCION CLIENTES:
La seccion de CLientes tiene definidos 4 ENDPOINTS

@RestController
@RequestMapping("/cliente")

1 - @GetMapping("/listar")
2 - @PostMapping("/alta")
3 - @DeleteMapping("/baja")
4 - @GetMapping("/{id}")

Para hacer las solicitudes:
1 - GET - http://localhost:8080/cliente/listar

2 - POST - http://localhost:8080/cliente/alta
Ejemplo JSON para el alta:
{
    "nombre": "Andres",
    "apellido": "Calamaro",
    "dni": 99888777,
    "tipoPersona": "F",
    "direccion": "Chiclana 20",
    "email": "andres@gmail.com",
    "fechaNac": "1988-09-09"
}

3 - DELETE - http://localhost:8080/cliente/baja/99888777

4 - GET - http://localhost:8080/cliente/99888777

-------------------------------------------------------------------
SECCION CUENTAS:
La seccion de Cuentas tiene definidos 4 ENDPOINTS

@RestController
@RequestMapping("/cuenta")

1 - @GetMapping("/listar")
2 - @PostMapping("/alta")
3 - @DeleteMapping("/baja/{NroCta}")
4 - @GetMapping("/{NroCta}")

Para hacer las solicitudes:
1 - GET - http://localhost:8080/cuenta/listar

2 - POST - http://localhost:8080/cuenta/alta
Ejemplo JSON para el alta:
{
    "titular": 32586510,
    "nroCuenta":70000,
    "saldo": 2500000,
    "tipoCuenta": "CC",
    "tipoMoneda": "PESOS"
} 

3 - DELETE - http://localhost:8080/cuenta/baja/70000

4 - GET - http://localhost:8080/cuenta/70000
-------------------------------------------------------------------
SECCION PRESTAMOS:
La seccion de Prestamos tiene definidos 3 ENDPOINTS

@RestController
@RequestMapping("/prestamo")

1 - @PostMapping("/solicitar")
2 - @GetMapping("/{ClienteId}")
3 - PutMapping("/pagar")

Para hacer las solicitudes:
1 - POST - http://localhost:8080/solicitar
Ejemplo JSON para la solicitud:
{
    "numeroCliente":32586510,
    "plazo":36,
    "monto":50000,
    "moneda":"PeSos"
}

2 - GET - http://localhost:8080/32586510

3 - PUT - http://localhost:8080/pagar
Ejemplo JSON para realizar el pago de una cuota de un prestamo

{
    "numeroCliente":32586510,
    "numeroPrestamo":35545,
    "moneda":"Pesos"
}
-------------------------------------------------------------------
Guia para el score crediticio:
DNI 10200200 no se le aprueba el credito
DNI 20300300 se le aprueba el credito si el monto es menor 1000000
DNI 30400400 se le aprueba el credito si el plazo es mayor a 12 cuotas
Cualquier otro DNI se aprueba el prestamo..



