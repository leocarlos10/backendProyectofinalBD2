package com.leocarlos10.backendSG_medica.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.leocarlos10.backendSG_medica.service.UsuarioService;
import com.leocarlos10.backendSG_medica.service.CitaService;
import com.leocarlos10.backendSG_medica.service.DiagnosticoService;
import com.leocarlos10.backendSG_medica.service.HistoriaClinicaService;
import com.leocarlos10.backendSG_medica.service.PacienteService;
import com.leocarlos10.backendSG_medica.service.PanelService;
import com.leocarlos10.backendSG_medica.jwt.JWTUtil;
import com.leocarlos10.backendSG_medica.exception.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Controller {

    protected static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    protected UsuarioService usuarioService;

    @Autowired
    protected CitaService citaService;

    @Autowired
    protected DiagnosticoService diagnosticoService;

    @Autowired
    protected HistoriaClinicaService historiaClinicaService;

    @Autowired
    protected PacienteService pacienteService;

    @Autowired
    protected PanelService panelService;

    @Autowired
    protected JWTUtil jwt;

    /**
     * Valida el token JWT proporcionado
     * 
     * @param token: Token JWT con prefijo "Bearer "
     * @throws UnauthorizedException: Si el token es inválido
     */
    protected void validarToken(String token) {
        if (token == null || token.isEmpty()) {
            throw new UnauthorizedException("Token no proporcionado");
        }

        if (!jwt.validarToken(token)) {
            throw new UnauthorizedException("Token inválido o expirado");
        }
    }
}
