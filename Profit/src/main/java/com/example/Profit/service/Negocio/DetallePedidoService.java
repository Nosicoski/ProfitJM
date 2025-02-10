package com.example.Profit.service.impl;

import com.example.Profit.Model.DetallePedido;
import com.example.Profit.Exception.ResourceNotFoundException;
import com.example.Profit.Repository.IDetallePedidoRepository;
import com.example.Profit.service.IDetallePedidoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetallePedidoService implements IDetallePedidoService {

    private final IDetallePedidoRepository detallePedidoRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(DetallePedidoService.class);

    public DetallePedidoService(IDetallePedidoRepository detallePedidoRepository) {
        this.detallePedidoRepository = detallePedidoRepository;
    }

    @Override
    public void eliminarDetallePedido(Long id) {
        Optional<DetallePedido> detallePedidoExistente = detallePedidoRepository.findById(id);
        if (detallePedidoExistente.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el detalle de pedido a eliminar con id: " + id);
        }
        detallePedidoRepository.deleteById(id);
        LOGGER.info("DetallePedido eliminado con id: {}", id);
    }

    @Override
    public List<DetallePedido> listarTodos() {
        return detallePedidoRepository.findAll();
    }

    @Override
    public DetallePedido buscarDetallePedido(Long id) {
        return detallePedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el detalle de pedido con id: " + id));
    }

    @Override
    public DetallePedido guardarDetallePedido(DetallePedido detallePedido) {
        DetallePedido detallePedidoGuardado = detallePedidoRepository.save(detallePedido);
        LOGGER.info("DetallePedido guardado con id: {}", detallePedidoGuardado.getId());
        return detallePedidoGuardado;
    }

    @Override
    public DetallePedido actualizarDetallePedido(Long id, DetallePedido detallePedido) {
        Optional<DetallePedido> detallePedidoExistente = detallePedidoRepository.findById(id);
        if (detallePedidoExistente.isPresent()) {
            DetallePedido detallePedidoActual = detallePedidoExistente.get();
            // Actualiza los campos del detalle de pedido según corresponda.
            // Por ejemplo, si DetallePedido tiene atributos como cantidad, precioUnitario, producto, etc.:
            // detallePedidoActual.setCantidad(detallePedido.getCantidad());
            // detallePedidoActual.setPrecioUnitario(detallePedido.getPrecioUnitario());
            // detallePedidoActual.setProducto(detallePedido.getProducto());
            // Agrega la actualización de otros campos según la estructura de tu entidad DetallePedido.
            DetallePedido detallePedidoActualizado = detallePedidoRepository.save(detallePedidoActual);
            LOGGER.info("DetallePedido actualizado: {}", detallePedidoActualizado);
            return detallePedidoActualizado;
        } else {
            throw new ResourceNotFoundException("No se encontró el detalle de pedido a actualizar con id: " + id);
        }
    }
}
