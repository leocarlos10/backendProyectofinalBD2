package com.leocarlos10.backendSG_medica.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.leocarlos10.backendSG_medica.service.UsuarioService;
import com.leocarlos10.backendSG_medica.conexionDAO.CitaDAO; 
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Map;
import com.leocarlos10.backendSG_medica.jwt.JWTUtil;



@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    protected UsuarioService usuarioService;
    @Autowired
    protected CitaDAO citaDAO;
    @Autowired
    protected JWTUtil jwt;
    
    protected ResponseEntity<?> ResponseHttp(HttpStatus httpStatus, Map<?,?> respuesta  ){
        return ResponseEntity.status(httpStatus).body(respuesta);
    }
    
   

}
