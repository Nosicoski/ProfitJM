package com.example.Profit.Controller;

import com.example.Profit.Model.Pedido;
import com.example.Profit.Model.Producto;
import com.example.Profit.service.IPedidoService;
import com.example.Profit.service.IProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private IPedidoService pedidoService;

    public PedidoController(IPedidoService pdidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/ListarTodos")
    public ResponseEntity<List<Pedido>> listarTodos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Pedido> buscarPedido(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPedido(id));
    }

    @PostMapping("/guardar")
    public ResponseEntity<Pedido> guardarPedido(@RequestBody Pedido pedido) {
        return ResponseEntity.ok(pedidoService.guardarPedido(pedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizarPedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        return ResponseEntity.ok(pedidoService.actualizarPedido(id, pedido));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
