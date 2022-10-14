package com.jasr.sistemaexamenesbackend.servicios.Interfases;

import java.util.Set;

import com.jasr.sistemaexamenesbackend.entidades.Examen;
import com.jasr.sistemaexamenesbackend.entidades.Pregunta;

public interface IPreguntaService {
    
    Pregunta agregarPregunta(Pregunta pregunta);
    Pregunta actualizarPregunta (Pregunta regunta);
    Set<Pregunta> obtenerPreguntas();
    Pregunta obtenerPregunta(Long preguntaId);
    Set<Pregunta> ObtenerPreguntasDelExamen(Examen examen);
    void eliminarPregunta(Long preguntaId);
    Pregunta listarPregunta(Long preguntaId);
}
