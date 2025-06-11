package com.leocarlos10.backendSG_medica.Controller;

import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class prueba {
    
    @GetMapping("/usuarios")
    public List<String> getUsuarios() {
        return List.of("usuario1", "usuario2", "usuario3", "usuario4", "usuario5");
    }
}
