package com.example.Profit.service.Negocio;

import com.example.Profit.Model.Proveedor;
import com.example.Profit.Exception.ResourceNotFoundException;
import com.example.Profit.Repository.IProveedorRepository;
import com.example.Profit.service.IProveedorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService implements IProveedorService {

    private final IProveedorRepository proveedorRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(ProveedorService.class);

    public ProveedorService(IProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public void eliminarProveedor(Long id) {
        Optional<Proveedor> proveedorExistente = proveedorRepository.findById(id);
        if (proveedorExistente.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró el proveedor a eliminar con id: " + id);
        }
        proveedorRepository.deleteById(id);
        LOGGER.info("Proveedor eliminado con id: {}", id);
    }

    @Override
    public List<Proveedor> listarTodos() {
        return proveedorRepository.findAll();
    }

    @Override
    public Proveedor buscarProveedor(Long id) {
        return proveedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el proveedor con id: " + id));
    }

    @Override
    public Proveedor guardarProveedor(Proveedor proveedor) {
        Proveedor proveedorGuardado = proveedorRepository.save(proveedor);
        LOGGER.info("Proveedor guardado con id: {}", proveedorGuardado.getId());
        return proveedorGuardado;
    }

    @Override
    public Proveedor actualizarProveedor(Long id, Proveedor proveedor) {
        Optional<Proveedor> proveedorExistente = proveedorRepository.findById(id);
        if (proveedorExistente.isPresent()) {
            Proveedor proveedorActual = proveedorExistente.get();
            // Actualiza los campos de acuerdo a la estructura de la entidad Proveedor.
            // Suponiendo que la entidad tenga, por ejemplo, nombre, direccion, telefono, email, etc.
            proveedorActual.setNombre(proveedor.getNombre());
            // Si la entidad Proveedor posee más atributos, agrégalos aquí:
            // proveedorActual.setDireccion(proveedor.getDireccion());
            // proveedorActual.setTelefono(proveedor.getTelefono());
            // proveedorActual.setEmail(proveedor.getEmail());
            Proveedor proveedorActualizado = proveedorRepository.save(proveedorActual);
            LOGGER.info("Proveedor actualizado: {}", proveedorActualizado);
            return proveedorActualizado;
        } else {
            throw new ResourceNotFoundException("No se encontró el proveedor a actualizar con id: " + id);
        }
    }
}
