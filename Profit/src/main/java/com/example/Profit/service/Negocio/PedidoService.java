package com.example.Profit.service.impl;

import com.example.Profit.Model.Pedido;
import com.example.Profit.Exception.ResourceNotFoundException;
import com.example.Profit.Repository.IPedidoRepository;
import com.example.Profit.service.IPedidoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService implements IPedidoService {

    private final IPedidoRepository pedidoRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(PedidoService.class);

    public PedidoService(IPedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public void eliminarPedido(Long id) {
        Optional<Pedido> pedidoExistente = pedidoRepository.findById(id);
        if (pedidoExistente.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el pedido a eliminar con id: " + id);
        }
        pedidoRepository.deleteById(id);
        LOGGER.info("Pedido eliminado con id: {}", id);
    }

    @Override
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido buscarPedido(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el pedido con id: " + id));
    }

    @Override
    public Pedido guardarPedido(Pedido pedido) {
        Pedido pedidoGuardado = pedidoRepository.save(pedido);
        LOGGER.info("Pedido guardado con id: {}", pedidoGuardado.getId());
        return pedidoGuardado;
    }

    @Override
    public Pedido actualizarPedido(Long id, Pedido pedido) {
        Optional<Pedido> pedidoExistente = pedidoRepository.findById(id);
        if (pedidoExistente.isPresent()) {
            Pedido pedidoActual = pedidoExistente.get();
            // Actualiza los campos del pedido según corresponda.
            // Por ejemplo, si Pedido tiene atributos como fecha, cliente, total, etc.:
            // pedidoActual.setFecha(pedido.getFecha());
            // pedidoActual.setCliente(pedido.getCliente());
            // pedidoActual.setTotal(pedido.getTotal());
            // Agrega la actualización de otros campos según la estructura de tu entidad Pedido.
            Pedido pedidoActualizado = pedidoRepository.save(pedidoActual);
            LOGGER.info("Pedido actualizado: {}", pedidoActualizado);
            return pedidoActualizado;
        } else {
            throw new ResourceNotFoundException("No se encontró el pedido a actualizar con id: " + id);
        }
    }
}
