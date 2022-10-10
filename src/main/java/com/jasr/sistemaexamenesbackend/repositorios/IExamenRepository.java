package com.jasr.sistemaexamenesbackend.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jasr.sistemaexamenesbackend.entidades.Examen;

public interface IExamenRepository extends JpaRepository<Examen,Long>{

    
}
