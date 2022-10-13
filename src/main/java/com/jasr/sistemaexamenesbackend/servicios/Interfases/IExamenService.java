package com.jasr.sistemaexamenesbackend.servicios.Interfases;

import java.util.List;
import java.util.Set;

import com.jasr.sistemaexamenesbackend.entidades.Categoria;
import com.jasr.sistemaexamenesbackend.entidades.Examen;

public interface IExamenService {
    
    Examen agregarExamen(Examen examen);
    Examen actualizarExamen (Examen examen);
    Set<Examen> obtenerExamenes();
    Examen obtenerExamen(Long ExamenId);
    void eliminarExamen(Long examenId);
    List<Examen> listarExamenesDeUnaCategoria(Categoria categoria);

}
