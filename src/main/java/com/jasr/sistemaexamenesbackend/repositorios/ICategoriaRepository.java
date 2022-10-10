package com.jasr.sistemaexamenesbackend.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jasr.sistemaexamenesbackend.entidades.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria,Long>{
    
}
