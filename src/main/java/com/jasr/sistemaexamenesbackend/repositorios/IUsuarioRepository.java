package com.jasr.sistemaexamenesbackend.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jasr.sistemaexamenesbackend.entidades.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario,Long> {

    public Usuario findByUsername (String username);

}
