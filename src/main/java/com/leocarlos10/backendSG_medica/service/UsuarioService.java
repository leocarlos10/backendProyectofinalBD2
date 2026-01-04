package com.leocarlos10.backendSG_medica.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.leocarlos10.backendSG_medica.Models.Usuario;
import com.leocarlos10.backendSG_medica.conexionDAO.UsuarioDAO;
import com.leocarlos10.backendSG_medica.jwt.JWTUtil;
import com.leocarlos10.backendSG_medica.exception.EntityNotFoundException;
import com.leocarlos10.backendSG_medica.exception.ValidationException;
import com.leocarlos10.backendSG_medica.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

@Service
public class UsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private JWTUtil jwt;

    /**
     * Registra un nuevo usuario en la base de datos
     * 
     * @param usuario: El usuario a registrar
     * @return: El usuario registrado
     */
    public Usuario registrarUsuario(Usuario usuario) {
        logger.info("Registrando nuevo usuario con cédula: {}", usuario.getCedula());

        if (usuario == null || usuario.getCedula() == null || usuario.getCedula().isEmpty()) {
            throw new ValidationException("La cédula del usuario es requerida");
        }

        if (usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
            throw new ValidationException("El nombre del usuario es requerido");
        }

        try {
            int resultado = usuarioDAO.registrar(usuario);
            if (resultado > 0) {
                logger.info("Usuario registrado exitosamente: {}", usuario.getCedula());
                return usuario;
            } else {
                throw new BusinessException("REGISTRO_FALLIDO", "No se pudo registrar el usuario");
            }
        } catch (Exception e) {
            logger.error("Error al registrar usuario: {}", e.getMessage(), e);
            throw new BusinessException("REGISTRO_ERROR", "Error al registrar el usuario", e);
        }
    }

    /**
     * Realiza el login de un usuario
     * 
     * @param usuario: Usuario con cédula y contraseña
     * @return: Map con token, nombre y cédula del usuario
     */
    public Map<String, String> loginUsuario(Usuario usuario) {
        logger.info("Intento de login para usuario: {}", usuario.getCedula());

        if (usuario == null || usuario.getCedula() == null || usuario.getCedula().isEmpty()) {
            throw new ValidationException("La cédula es requerida para el login");
        }

        if (usuario.getPass() == null || usuario.getPass().isEmpty()) {
            throw new ValidationException("La contraseña es requerida para el login");
        }

        try {
            Usuario user = usuarioDAO.obtenerPorId(usuario.getCedula());

            if (user == null) {
                logger.warn("Usuario no encontrado: {}", usuario.getCedula());
                throw new EntityNotFoundException("Usuario", usuario.getCedula());
            }

            if (!usuario.getPass().equals(user.getPass())) {
                logger.warn("Contraseña incorrecta para usuario: {}", usuario.getCedula());
                throw new ValidationException("Contraseña incorrecta");
            }

            String token = jwt.create(usuario.getCedula(), user.getNombre());
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("nombre", user.getNombre());
            response.put("cedula", user.getCedula());

            logger.info("Login exitoso para usuario: {}", usuario.getCedula());
            return response;

        } catch (EntityNotFoundException | ValidationException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error en loginUsuario: {}", e.getMessage(), e);
            throw new BusinessException("LOGIN_ERROR", "Error al iniciar sesión", e);
        }
    }

    /**
     * Obtiene un usuario por cédula
     * 
     * @param cedula: Cédula del usuario
     * @return: El usuario
     */
    public Usuario obtenerPorCedula(String cedula) {
        logger.info("Obteniendo usuario con cédula: {}", cedula);

        if (cedula == null || cedula.isEmpty()) {
            throw new ValidationException("La cédula es requerida");
        }

        try {
            Usuario usuario = usuarioDAO.obtenerPorId(cedula);
            if (usuario == null) {
                throw new EntityNotFoundException("Usuario", cedula);
            }
            return usuario;
        } catch (Exception e) {
            logger.error("Error al obtener usuario: {}", e.getMessage(), e);
            throw new BusinessException("OBTENER_ERROR", "Error al obtener el usuario", e);
        }
    }
}
