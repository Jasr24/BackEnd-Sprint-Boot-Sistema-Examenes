package com.jasr.sistemaexamenesbackend.servicios.Interfases;

import java.util.Set;

import com.jasr.sistemaexamenesbackend.entidades.Categoria;

public interface ICategoriaService {

    Categoria agregarCategoria(Categoria categoria);
    Categoria actualizarCategoria(Categoria categoria);
    Set<Categoria> obtenerCategorias();
    Categoria obtenerCategoria(Long categoriaId);
    void eliminarCategoria(Long categoriaId);
    
}
