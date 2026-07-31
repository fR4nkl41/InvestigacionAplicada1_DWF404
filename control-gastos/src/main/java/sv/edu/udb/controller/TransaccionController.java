ackage sv.edu.udb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.model.Transaccion;
import sv.edu.udb.service.TransaccionService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transacciones")
@RequiredArgsConstructor
public class TransaccionController {

    private final TransaccionService transaccionService;

    @PostMapping
    public ResponseEntity<Transaccion> registrarTransaccion(@RequestBody Transaccion transaccion) {
        Transaccion registrada = transaccionService.registrarTransaccion(transaccion);
        return new ResponseEntity<>(registrada, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Transaccion>> listarTransacciones() {
        List<Transaccion> transacciones = transaccionService.listarTransacciones();
        return ResponseEntity.ok(transacciones);
    }

    @GetMapping("/balance")
    public ResponseEntity<Map<String, BigDecimal>> obtenerBalance() {
        BigDecimal balance = transaccionService.calcularBalance();
        return ResponseEntity.ok(Map.of("balance", balance));
    }
}
