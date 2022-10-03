package com.jasr.sistemaexamenesbackend.servicios.Interfases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jasr.sistemaexamenesbackend.entidades.Usuario;
import com.jasr.sistemaexamenesbackend.repositorios.IUsuarioRepository;

//esta Clase lo que hara es cargar un usuraio por su username
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioRepository.findByUsername(username);
        if (usuario == null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return usuario;
    }
    
}
