package com.leocarlos10.backendSG_medica.service;

import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import com.leocarlos10.backendSG_medica.Models.Usuario;
import org.springframework.http.HttpStatus;

@Service
public class UsuarioService extends service {


    /**
     * Este metodo es el encargado de registrar un usuario en la base de datos
     * @param usuario: El usuario a registrar
     * @return: Un objeto de tipo ResponseEntity que contiene el resultado de la operación
     */
    public ResponseEntity<?> registrarUsuario(Usuario usuario) {
        
        try {
            int resultado = usuarioDAO.registrar(usuario);
            if(resultado > 0 ){
                return ResponseHttp( HttpStatus.OK, respuesta("mensaje", "Usuario registrado correctamente"));
            } else{
                return ResponseHttp(HttpStatus.BAD_REQUEST, respuesta("mensaje", "Error al registrar usuario"));
            }
        } catch (Exception e) {
            System.out.println("error registrarUsuario-UsuarioService" + e);
            return ResponseHttp(HttpStatus.INTERNAL_SERVER_ERROR, respuesta("mensaje", "Error al registrar usuario"));
           
        }
    }

    
}
