package com.jasr.sistemaexamenesbackend.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jasr.sistemaexamenesbackend.entidades.Rol;

public interface IRolRepository extends JpaRepository<Rol,Long> {
    
}