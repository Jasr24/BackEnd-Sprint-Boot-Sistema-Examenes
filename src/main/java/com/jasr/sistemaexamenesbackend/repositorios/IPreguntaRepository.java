package com.jasr.sistemaexamenesbackend.repositorios;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jasr.sistemaexamenesbackend.entidades.Examen;
import com.jasr.sistemaexamenesbackend.entidades.Pregunta;

public interface IPreguntaRepository extends JpaRepository<Pregunta,Long>{

    Set<Pregunta> findByExamen(Examen examen);
    
}
