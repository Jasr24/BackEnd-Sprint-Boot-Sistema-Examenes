package com.jasr.sistemaexamenesbackend.servicios;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jasr.sistemaexamenesbackend.entidades.Examen;
import com.jasr.sistemaexamenesbackend.entidades.Pregunta;
import com.jasr.sistemaexamenesbackend.repositorios.IPreguntaRepository;
import com.jasr.sistemaexamenesbackend.servicios.Interfases.IPreguntaService;

@Service
public class PreguntaService implements IPreguntaService{

    @Autowired
    private IPreguntaRepository preguntaRepository;

    @Override
    public Pregunta agregarPregunta(Pregunta pregunta) {
        return preguntaRepository.save(pregunta);
    }

    @Override
    public Pregunta actualizarPregunta(Pregunta pregunta) {
        return preguntaRepository.save(pregunta);
    }

    @Override
    public Set<Pregunta> obtenerPreguntas() {
        //return (Set<Pregunta>) preguntaRepository.findAll(); 
        return new LinkedHashSet<>(preguntaRepository.findAll());
    }

    @Override
    public Pregunta obtenerPregunta(Long preguntaId) {
        return preguntaRepository.findById(preguntaId).get();
    }

    @Override
    public Set<Pregunta> ObtenerPreguntasDelExamen(Examen examen) {
        return preguntaRepository.findByExamen(examen);
    }

    @Override
    public void eliminarPregunta(Long preguntaId) {
        preguntaRepository.deleteById(preguntaId);
    }

    @Override
    public Pregunta listarPregunta(Long preguntaId) {
        return this.preguntaRepository.getOne(preguntaId);
    }
    
}
