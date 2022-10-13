package com.jasr.sistemaexamenesbackend.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jasr.sistemaexamenesbackend.entidades.Categoria;
import com.jasr.sistemaexamenesbackend.entidades.Examen;

public interface IExamenRepository extends JpaRepository<Examen,Long>{

    List<Examen> findByCategoria(Categoria categoria);
    
}
