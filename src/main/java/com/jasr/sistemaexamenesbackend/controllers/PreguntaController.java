package com.jasr.sistemaexamenesbackend.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jasr.sistemaexamenesbackend.entidades.Examen;
import com.jasr.sistemaexamenesbackend.entidades.Pregunta;
import com.jasr.sistemaexamenesbackend.servicios.Interfases.IExamenService;
import com.jasr.sistemaexamenesbackend.servicios.Interfases.IPreguntaService;

@RestController
@RequestMapping("/pregunta")
@CrossOrigin("*")
public class PreguntaController {
    
    @Autowired
    private IPreguntaService preguntaService;

    @Autowired
    private IExamenService examenService;

    @PostMapping("/")
    public ResponseEntity<Pregunta> guardarPregunta(@RequestBody Pregunta pregunta){
        return ResponseEntity.ok(preguntaService.agregarPregunta(pregunta));
    }

    @PutMapping("/")
    public ResponseEntity<Pregunta> actualizarPregunta(@RequestBody Pregunta pregunta){
        return ResponseEntity.ok(preguntaService.actualizarPregunta(pregunta));
    }

    @GetMapping("/examen/{exameneId}")
    public ResponseEntity<?> listarPreguntasDelExamen(@PathVariable("exameneId") Long examenId){
        
        /*Examen examen = examenService.obtenerExamen(examenId);
        return ResponseEntity.ok(preguntaService.ObtenerPreguntasDelExamen(examen));*/

        Examen examen = examenService.obtenerExamen(examenId);
        Set<Pregunta> preguntas = examen.getPreguntas();

        List examenes = new ArrayList(preguntas);
        if(examenes.size() > Integer.parseInt(examen.getNumeroPreguntas())){
            examenes = examenes.subList(0,Integer.parseInt(examen.getNumeroPreguntas() + 1));
        }

        Collections.shuffle(examenes);
        return ResponseEntity.ok(examenes);
    }

    @GetMapping("/{preguntaId}")
    public Pregunta listarPreguntaPorId(@PathVariable("preguntaId") Long preguntaId){
        return preguntaService.obtenerPregunta(preguntaId);
    }

    //Este metodo personal para todas las preguntas de todos los examenes
    @GetMapping("/")
    public Set<Pregunta> listarPreguntas(){
        return preguntaService.obtenerPreguntas();
    }

    @DeleteMapping("/{preguntaId}")
    public void eliminarPregunta(@PathVariable("preguntaId") Long preguntaId){
        preguntaService.eliminarPregunta(preguntaId);
    }

    @GetMapping("/examen/todos/{examenId}")
    public ResponseEntity<?> listarPreguntasDelExamenComoAdministrador(@PathVariable("examenId") Long examenId){
        Examen examen = new Examen();
        examen.setExamenId(examenId);
        Set<Pregunta> preguntas = preguntaService.ObtenerPreguntasDelExamen(examen);
        return ResponseEntity.ok(preguntas);
    }
}
