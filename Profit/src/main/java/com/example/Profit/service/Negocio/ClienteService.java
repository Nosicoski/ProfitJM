package com.example.Profit.service.Negocio;

import com.example.Profit.Model.Cliente;
import com.example.Profit.Exception.ResourceNotFoundException;
import com.example.Profit.Repository.IClienteRepository;

import com.example.Profit.service.IClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements IClienteService {

    private final IClienteRepository clienteRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(ClienteService.class);

    public ClienteService(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void eliminarCliente(Long id) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el cliente a eliminar con id: " + id);
        }
        clienteRepository.deleteById(id);
        LOGGER.info("Cliente eliminado con id: {}", id);
    }

    @Override
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarCliente(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el cliente con id: " + id));
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        Cliente clienteGuardado = clienteRepository.save(cliente);
        LOGGER.info("Cliente guardado con id: {}", clienteGuardado.getId());
        return clienteGuardado;
    }

    @Override
    public Cliente actualizarCliente(Long id, Cliente cliente) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isPresent()) {
            Cliente clienteActual = clienteExistente.get();
            clienteActual.setNombre(cliente.getNombre());
            clienteActual.setApellido(cliente.getApellido());
            clienteActual.setEmail(cliente.getEmail());
            clienteActual.setTelefono(cliente.getTelefono());
            // En caso de querer actualizar la lista de pedidos, se podría agregar lógica adicional aquí.
            Cliente clienteActualizado = clienteRepository.save(clienteActual);
            LOGGER.info("Cliente actualizado: {}", clienteActualizado);
            return clienteActualizado;
        } else {
            throw new ResourceNotFoundException("No se encontró el cliente a actualizar con id: " + id);
        }
    }
}
