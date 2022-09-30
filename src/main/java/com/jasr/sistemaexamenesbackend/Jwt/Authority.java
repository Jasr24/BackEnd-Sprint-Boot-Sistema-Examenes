package com.jasr.sistemaexamenesbackend.Jwt;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority{

    private String authority;


    public Authority(String authority) {
        this.authority = authority;
    }


    @Override
    public String getAuthority() {  //Metodo de obtener autoridad.
        return this.authority;
    }
    
}
