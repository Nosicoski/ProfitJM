package com.example.Profit.service.Negocio;

import com.example.Profit.Model.Producto;
import com.example.Profit.Repository.IProductoRepository;
import com.example.Profit.service.IProductoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ProductoService implements IProductoService{
    private final IProductoRepository productoRepository;

    public ProductoService(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto buscarProducto(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public Producto guardarProducto(Producto requestDto) {
        return productoRepository.save(requestDto);
    }

    @Override
    @Transactional
    public Producto actualizarProducto(Long id, Producto producto) {
        Producto productoExistente = buscarProducto(id);
        productoExistente.setNombre( producto.getNombre());
        productoExistente.setPrecio( producto.getPrecio());
        productoExistente.setStock(producto.getStock());
        return productoRepository.save(productoExistente);
    }

    @Override
    @Transactional
    public void eliminarProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new EntityNotFoundException("No se puede eliminar. Producto no encontrado con ID: " + id);
        }
        productoRepository.deleteById(id);
    }
}
