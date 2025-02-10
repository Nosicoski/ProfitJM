package com.example.Profit.Controller;


import com.example.Profit.Model.Proveedor;
import com.example.Profit.service.IProveedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ProveedorController {
    private IProveedorService proveedorService;

    public ProveedorController(IProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping("/ListarTodos")
    public ResponseEntity<List<Proveedor>> listarTodos() {
        return ResponseEntity.ok(proveedorService.listarTodos());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Proveedor> buscarProveedor(@PathVariable Long id) {
        return ResponseEntity.ok(proveedorService.buscarProveedor(id));
    }

    @PostMapping("/guardar")
    public ResponseEntity<Proveedor> guardarProveedor(@RequestBody Proveedor proveedor) {
        return ResponseEntity.ok(proveedorService.guardarProveedor(proveedor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizarProveedor(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        return ResponseEntity.ok(proveedorService.actualizarProveedor(id, proveedor));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable Long id) {
        proveedorService.eliminarProveedor(id);
        return ResponseEntity.noContent().build();
    }
}
