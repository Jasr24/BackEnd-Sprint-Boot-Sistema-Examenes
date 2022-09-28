package com.jasr.sistemaexamenesbackend.entidades;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

//Esta tabla es intermedia.. hae la conexion entre Usuario y Rol

@Entity
public class UsuarioRol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioRolId;

    @ManyToOne(fetch = FetchType.EAGER) //Fectch.Eagaer de tipo ansioso, de carga ansiosa.
    private Usuario usuario; //Este es el propietario de la relacion en la clase Usuario.java.. mira el mappedBy.  //este es de tipo Usuario.

    @ManyToOne
    private Rol rol;  //Este es el propietario de la relacion en la clase Rol.java.. mira el mappedBy.


    public UsuarioRol (){
        
    }

    public Long getUsuarioRolId() {
        return usuarioRolId;
    }

    public void setUsuarioRolId(Long usuarioRolId) {
        this.usuarioRolId = usuarioRolId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    
}
