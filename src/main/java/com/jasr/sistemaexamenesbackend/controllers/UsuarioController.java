package com.jasr.sistemaexamenesbackend.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jasr.sistemaexamenesbackend.entidades.Rol;
import com.jasr.sistemaexamenesbackend.entidades.Usuario;
import com.jasr.sistemaexamenesbackend.entidades.UsuarioRol;
import com.jasr.sistemaexamenesbackend.servicios.Interfases.IUsuarioService;

@RestController //El rescontroller se tienen que usar para manejar lo del json
@RequestMapping("/usuarios")
@CrossOrigin("*") //el * permite a cualquier peticion.. get put delete ect.
public class UsuarioController {
    
    @Autowired
    private IUsuarioService usuarioService; //El profesor uso la clase concreta.. aqui uso la generica.

    @PostMapping("/")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception{ //Requesbody es para pasar poder mandar los objetos con sus datos
        usuario.setPerfil("default.png");
        Set<UsuarioRol> roles = new HashSet<>();
        
        Rol rol = new Rol();
        rol.setRolId(2L);
        rol.setNombre("NORMAL");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);
        roles.add(usuarioRol); 

        return usuarioService.guardarUsuario(usuario, roles);
    }

    @GetMapping("/{username}")
    public Usuario obtenerUsuario(@PathVariable("username") String username){ //PathVariable es la variable que pasamos en la ruta del url en este cato el del getMappint {username}.
        return usuarioService.obtenerUsuario(username);
    }

    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario (@PathVariable("usuarioId") Long id){   //PathVariable es la variable que pasamos en la ruta del url en este cato el del getMappint {username}.
        usuarioService.eliminarUsuario(id);        
    }
}