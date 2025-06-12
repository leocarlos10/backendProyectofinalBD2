package com.leocarlos10.backendSG_medica.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.leocarlos10.backendSG_medica.service.UsuarioService;


@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    protected UsuarioService usuarioService;

}
