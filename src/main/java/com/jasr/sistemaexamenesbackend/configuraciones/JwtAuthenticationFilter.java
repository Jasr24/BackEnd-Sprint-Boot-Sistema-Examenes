package com.jasr.sistemaexamenesbackend.configuraciones;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jasr.sistemaexamenesbackend.servicios.Interfases.UserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;


//Esta clase se encarga de interceptar todas las invocaciones al servidor. comprueba la existencia del token, si existe lo desencripta y valida, si esta ok añade la configuracion nesesaria al contexto de Spring para autorizar la peticion
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")){
            jwtToken = requestTokenHeader.substring(7);

            try{
                username = this.jwtUtil.extractUsername(jwtToken);
            }catch(ExpiredJwtException expiredJwtException){
                System.out.println("El token a expirado");
            }catch (Exception exception){   
                exception.printStackTrace();    
            }
        }else {
            System.out.println("Token invalido, no empieza conbeare string");
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if(this.jwtUtil.validateToken(jwtToken, userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }else {
                System.out.println("El token no es valido");
            }
            filterChain.doFilter(request, response);
        }
    }
    
}
