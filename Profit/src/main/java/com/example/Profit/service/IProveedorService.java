package com.example.Profit.service;

import com.example.Profit.Model.Proveedor;

import java.util.List;


public interface IProveedorService {



    void eliminarProveedor(Long id);

    List<Proveedor> listarTodos();

    Proveedor buscarProveedor(Long id);

    Proveedor guardarProveedor(Proveedor requestDto);

    Proveedor actualizarProveedor(Long id, Proveedor requestDto);

}
