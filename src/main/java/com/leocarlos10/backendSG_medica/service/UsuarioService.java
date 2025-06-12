package com.leocarlos10.backendSG_medica.service;

import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import com.leocarlos10.backendSG_medica.Models.Usuario;
import org.springframework.http.HttpStatus;

@Service
public class UsuarioService extends service {

    public ResponseEntity<?> registrarUsuario(Usuario usuario) {
        
        try {
            int resultado = usuarioDAO.registrar(usuario);
            if(resultado > 0 ){
                return ResponseHttp( HttpStatus.OK, respuesta("mensaje", "Usuario registrado correctamente"));
            } else{
                return ResponseHttp(HttpStatus.BAD_REQUEST, respuesta("mensaje", "Error al registrar usuario"));
            }
        } catch (Exception e) {
            return ResponseHttp(HttpStatus.INTERNAL_SERVER_ERROR, respuesta("mensaje", "Error al registrar usuario"));
        }
    }

    
}
