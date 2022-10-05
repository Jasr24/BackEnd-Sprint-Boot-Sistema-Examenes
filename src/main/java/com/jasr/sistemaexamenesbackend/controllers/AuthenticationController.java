package com.jasr.sistemaexamenesbackend.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jasr.sistemaexamenesbackend.Jwt.JwtRequest;
import com.jasr.sistemaexamenesbackend.Jwt.JwtResponse;
import com.jasr.sistemaexamenesbackend.configuraciones.JwtUtil;
import com.jasr.sistemaexamenesbackend.entidades.Usuario;
import com.jasr.sistemaexamenesbackend.servicios.Interfases.UserDetailsService;

@RestController
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generarToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        try {
             autenticar(jwtRequest.getUsername(), jwtRequest.getPassword());
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("Usuario no encontrado");
        }
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void autenticar (String username, String password) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch(DisabledException disabledException){
            throw new Exception("USUARIO DESHABILITADO " + disabledException.getMessage());
        }catch (BadCredentialsException  badCredentialsException){
            throw new Exception("CREDENCIALES INVALIDAS " + badCredentialsException.getMessage());
        }
    }

    //Devuelve el usuario que esta en seccion. el actual usuario.
    @GetMapping("/actual-usuario")
    public Usuario obtenerUsuarioActual(Principal principal){
        return (Usuario) this.userDetailsService.loadUserByUsername(principal.getName());
    }
}
