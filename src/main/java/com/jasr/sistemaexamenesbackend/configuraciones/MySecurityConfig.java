package com.jasr.sistemaexamenesbackend.configuraciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jasr.sistemaexamenesbackend.servicios.Interfases.UserDetailsService;

@EnableWebSecurity  //Es para nos permite especificar la configuracion de acceso a los recursos publicados, en este caso se permiten todas la llamadas al controlador /user, pero el resto de la llamadas requieren autenticacion.
@Configuration      //Es para nos permite especificar la configuracion de acceso a los recursos publicados, en este caso se permiten todas la llamadas al controlador /user, pero el resto de la llamadas requieren autenticacion.
@EnableGlobalMethodSecurity(prePostEnabled = true) //Es para cuando queremos abilitar la seguridad de nivel de metodo de sprin solo nesecitamos usar esta anotacion. y en cualquier instancia de configuracion para lograr este objetico. a la vez no proporciona 3 metodos diferentes. prepostenabled, secureEnabled y jsr250Enabled .
public class MySecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizeHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
            .disable()
            .cors()
            .disable()
            .authorizeRequests()
            .antMatchers("/generate-token", "/usuarios/").permitAll()
            .antMatchers(HttpMethod.OPTIONS).permitAll()
            .anyRequest().authenticated()
            .and()
            .exceptionHandling().authenticationEntryPoint(unauthorizeHandler)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
