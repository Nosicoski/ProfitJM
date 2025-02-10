package com.example.Profit.service;


import com.example.Profit.Model.Pedido;

import java.util.List;

public interface IPedidoService {

    void eliminarPedido(Long id);

    List<Pedido> listarTodos();

    Pedido buscarPedido(Long id);

    Pedido guardarPedido(Pedido requestDto);

    Pedido actualizarPedido(Long id, Pedido requestDto);
}
