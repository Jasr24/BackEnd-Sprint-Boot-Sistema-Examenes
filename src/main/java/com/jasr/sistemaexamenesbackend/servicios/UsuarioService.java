package com.jasr.sistemaexamenesbackend.servicios;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jasr.sistemaexamenesbackend.entidades.Usuario;
import com.jasr.sistemaexamenesbackend.entidades.UsuarioRol;
import com.jasr.sistemaexamenesbackend.repositorios.IRolRepository;
import com.jasr.sistemaexamenesbackend.repositorios.IUsuarioRepository;
import com.jasr.sistemaexamenesbackend.servicios.Interfases.IUsuarioService;

@Service //Acuerdese que es como component, Controller ....
public class UsuarioService implements IUsuarioService{
    
    @Autowired //Inyeccion de dependencia.
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IRolRepository rolRepository;

    @Override
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
         
        Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());

        if(usuarioLocal != null){
            System.out.println("El usuario ya existe");
            throw new Exception("El usuario ya esta presente");
        } else {
            for (UsuarioRol usuarioRol : usuarioRoles) {
                rolRepository.save(usuarioRol.getRol());
            }
            usuario.getUsuarioRoles().addAll(usuarioRoles);
            usuarioLocal = usuarioRepository.save(usuario);
        }
        return usuarioLocal;
    }
}
