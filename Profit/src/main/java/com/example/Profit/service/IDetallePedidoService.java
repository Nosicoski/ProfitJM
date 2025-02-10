package com.example.Profit.service;


import com.example.Profit.Model.DetallePedido;

import java.util.List;

public interface IDetallePedidoService {

    void eliminarDetallePedido(Long id);

    List<DetallePedido> listarTodos();

    DetallePedido buscarDetallePedido(Long id);

    DetallePedido guardarDetallePedido(DetallePedido requestDto);

    DetallePedido actualizarDetallePedido(Long id, DetallePedido requestDto);
}
