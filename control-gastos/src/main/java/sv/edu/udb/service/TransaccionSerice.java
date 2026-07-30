package sv.edu.udb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sv.edu.udb.model.Transaccion;
import sv.edu.udb.repository.TransaccionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransaccionService {

    private final TransaccionRepository transaccionRepository;

    public Transaccion registrarTransaccion(Transaccion transaccion) {
        if (transaccion.getFecha() == null) {
            transaccion.setFecha(LocalDateTime.now());
        }
        return transaccionRepository.save(transaccion);
    }

    public List<Transaccion> listarTransacciones() {
        return transaccionRepository.findAll();
    }

    public BigDecimal calcularBalance() {
        BigDecimal ingresos = transaccionRepository.sumMontoByTipo("INGRESO");
        BigDecimal gastos = transaccionRepository.sumMontoByTipo("GASTO");
        return ingresos.subtract(gastos);
    }

    // Metodo adicional para obtener transacciones por categoría
    public List<Transaccion> listarPorCategoria(Long categoriaId) {
        return transaccionRepository.findByCategoriaId(categoriaId);
    }
}
