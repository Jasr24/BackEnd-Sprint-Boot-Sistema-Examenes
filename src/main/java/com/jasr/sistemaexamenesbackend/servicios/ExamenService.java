package com.jasr.sistemaexamenesbackend.servicios;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jasr.sistemaexamenesbackend.entidades.Categoria;
import com.jasr.sistemaexamenesbackend.entidades.Examen;
import com.jasr.sistemaexamenesbackend.repositorios.IExamenRepository;
import com.jasr.sistemaexamenesbackend.servicios.Interfases.IExamenService;

@Service
public class ExamenService implements IExamenService{


    @Autowired
    private IExamenRepository examenRepository;

    @Override
    public Examen agregarExamen(Examen examen) {
        return examenRepository.save(examen);
    }

    @Override
    public Examen actualizarExamen(Examen examen) {
        return examenRepository.save(examen);
    }
    
    @Override
    public Set<Examen> obtenerExamenes() {
        return new LinkedHashSet<>(examenRepository.findAll());
    }

    @Override
    public Examen obtenerExamen(Long ExamenId) {
        return examenRepository.findById(ExamenId).get();
    }

    @Override
    public void eliminarExamen(Long examenId) {
        /*
        La iso el profesor
        Examen examen = new Examen();
        examen.setExamenId(examenId) 
        examenRepository.delete(examen);
        */
        /*
        La ise yo
        Examen examen = obtenerExamen(examenId);
        examenRepository.delete(examen);
        */

        //La iso mi hermano es mas rapida.
        examenRepository.deleteById(examenId);
    }

    @Override
    public List<Examen> listarExamenesDeUnaCategoria(Categoria categoria) {
        return this.examenRepository.findByCategoria(categoria);
    }

    @Override
    public List<Examen> obtenerExamenesActivos() {
        return examenRepository.findByActivo(true);
    }

    @Override
    public List<Examen> obtenerExamenesActivosDeUnaCategoria(Categoria categoria) {
        return examenRepository.findByCategoriaAndActivo(categoria, true);
    }
    
}
