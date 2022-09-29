package com.jasr.sistemaexamenesbackend.servicios.Interfases;

import java.util.Set;

import com.jasr.sistemaexamenesbackend.entidades.Usuario;
import com.jasr.sistemaexamenesbackend.entidades.UsuarioRol;

public interface IUsuarioService {

    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;
    
    public Usuario obtenerUsuario(String username);

    public void eliminarUsuario (Long usuarioId);
}
