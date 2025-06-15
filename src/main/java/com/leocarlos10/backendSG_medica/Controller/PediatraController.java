package com.leocarlos10.backendSG_medica.Controller;


import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.leocarlos10.backendSG_medica.Models.Pediatra;
import com.leocarlos10.backendSG_medica.conexionDAO.PediatraDAO;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/pediatra")
public class PediatraController extends Controller{

    @Autowired
    private PediatraDAO pediatraDAO;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Pediatra pediatra){

        try {
            Pediatra ped = pediatraDAO.obtenerPediatra(pediatra);
            if(ped != null){
                return ResponseEntity.ok(Map.of("respuesta", true, "mensaje", "Login exitoso"));
            } else{
                return ResponseHttp(HttpStatus.NOT_FOUND, Map.of("respuesta", false, "mensaje", "Usuario o contraseña incorrectos"));
            }
        } catch (Exception e) {
            System.out.println("error login-PediatraController" + e);
            return ResponseHttp(HttpStatus.INTERNAL_SERVER_ERROR, Map.of("mensaje", "Error al iniciar sesion", "respuesta", false));
        }
    }
    
}
