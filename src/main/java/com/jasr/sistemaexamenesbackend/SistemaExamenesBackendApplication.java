package com.jasr.sistemaexamenesbackend;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jasr.sistemaexamenesbackend.entidades.Rol;
import com.jasr.sistemaexamenesbackend.entidades.Usuario;
import com.jasr.sistemaexamenesbackend.entidades.UsuarioRol;
import com.jasr.sistemaexamenesbackend.servicios.Interfases.IUsuarioService;

@SpringBootApplication
public class SistemaExamenesBackendApplication implements CommandLineRunner{//CommandLineRunner esto es para que ejecute el metodo run

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SistemaExamenesBackendApplication.class, args);
	}

	@Override //Implementando el CommandLineRunner este metodo se ejecutara inicialmente... es como otro metodo que vismos por hay que ejecuta algo antes de empezar algo.
	public void run(String... args) throws Exception {

		//Esto lo modificamos solo por un momento para ingresar un nuevo primer usuario con la contraseña encriptada.
		/*try {
			Usuario usuario = new Usuario();
			usuario.setUsername("andres");
			usuario.setPassword(bCryptPasswordEncoder.encode("123456"));
			usuario.setNombre("Andres");
			usuario.setApellido("Romero");
			usuario.setEmail("andres@correo.com");
			usuario.setTelefono("3219243790");
			usuario.setPerfil("foto.png");

			Rol rol = new Rol();
			rol.setRolId(1L);
			rol.setNombre("ADMIN");

			Set<UsuarioRol> usuarioRoles = new HashSet<>();
			UsuarioRol usuarioRol = new UsuarioRol();
			usuarioRol.setRol(rol);
			usuarioRol.setUsuario(usuario);
			usuarioRoles.add(usuarioRol);

			Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario, usuarioRoles);
			System.out.println(usuarioGuardado.getUsername());
		} catch (UsuarioFoundException exception){
			exception.printStackTrace();
		}*/
	}
}
