package com.example.Profit.service;


import com.example.Profit.Model.Producto;

import java.util.List;

public interface IProductoService {

    void eliminarProducto(Long id);

    List<Producto> listarTodos();

    Producto buscarProducto(Long id);

    Producto guardarProducto(Producto requestDto);

    Producto actualizarProducto(Long id, Producto requestDto);
}
