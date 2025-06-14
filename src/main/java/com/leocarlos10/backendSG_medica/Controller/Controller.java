package com.leocarlos10.backendSG_medica.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.leocarlos10.backendSG_medica.service.UsuarioService;
import com.leocarlos10.backendSG_medica.conexionDAO.CitaDAO; 
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
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
    
    Map<String, List<?>> responseList;
    Map<String, String> response;

    protected ResponseEntity<?> ResponseHttp(HttpStatus httpStatus, Map<String, String> respuesta  ){
        return ResponseEntity.status(httpStatus).body(respuesta);
    }

    protected ResponseEntity<?> ResponseHttpOfObject(HttpStatus httpStatus, Map<String, List<?>> responseList  ){
        return ResponseEntity.status(httpStatus).body(responseList);
    }

    /**
     * Este metodo es el encargado de crear 
     * el Map con el nombre y la descripcion
     */
    protected Map<String, String> respuesta(String clave, String valor){
        response = new HashMap<>();
        response.put(clave, valor);
        return response; 
    }

   

}
