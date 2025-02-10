package com.example.Profit.Controller;


import com.example.Profit.Model.DetallePedido;
import com.example.Profit.service.IDetallePedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class DetallePedidoController {

    private final IDetallePedidoService detallePedidoService ;

    public DetallePedidoController(IDetallePedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
    }

    @GetMapping("/ListarTodos")
    public ResponseEntity<List<DetallePedido>> listarTodos() {
        return ResponseEntity.ok(detallePedidoService.listarTodos());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<DetallePedido> buscarDetallePedido(@PathVariable Long id) {
        return ResponseEntity.ok(detallePedidoService.buscarDetallePedido(id));
    }

    @PostMapping("/guardar")
    public ResponseEntity<DetallePedido> guardarDetallePedido(@RequestBody DetallePedido detallePedido) {
        return ResponseEntity.ok(detallePedidoService.guardarDetallePedido(detallePedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallePedido> actualizarDetallePedido(@PathVariable Long id, @RequestBody DetallePedido detallePedido) {
        return ResponseEntity.ok(detallePedidoService.actualizarDetallePedido(id, detallePedido));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetallePedido(@PathVariable Long id) {
        detallePedidoService.eliminarDetallePedido(id);
        return ResponseEntity.noContent().build();
    }
}
