package com.example.Profit.service;


import com.example.Profit.Model.Cliente;

import java.util.List;

public interface IClienteService {

    void eliminarCliente(Long id);

    List<Cliente> listarTodos();

    Cliente buscarCliente(Long id);

    Cliente guardarCliente(Cliente requestDto);

    Cliente actualizarCliente(Long id, Cliente requestDto);
}
