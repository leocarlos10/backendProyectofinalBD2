package com.leocarlos10.backendSG_medica.service;

import org.springframework.stereotype.Service;
import com.leocarlos10.backendSG_medica.Models.Usuario;
import com.leocarlos10.backendSG_medica.conexionDAO.UsuarioDAO;
import com.leocarlos10.backendSG_medica.dto.usuario.LoginRequest;
import com.leocarlos10.backendSG_medica.dto.usuario.LoginResponse;
import com.leocarlos10.backendSG_medica.jwt.JWTUtil;

import lombok.RequiredArgsConstructor;

import com.leocarlos10.backendSG_medica.exception.EntityNotFoundException;
import com.leocarlos10.backendSG_medica.exception.ValidationException;
import com.leocarlos10.backendSG_medica.exception.BusinessException;

@Service
@RequiredArgsConstructor
public class UsuarioService extends service {

    private final UsuarioDAO usuarioDAO;

    private final JWTUtil jwt;

    /**
     * Registra un nuevo usuario en la base de datos
     * 
     * @param usuario: El usuario a registrar
     * @return: El usuario registrado
     */
    public Usuario registrarUsuario(Usuario usuario) {

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
     * @param request: Datos del login de tipo LoginRequest
     * @return: un objeto Response con toda la informacion del LoginResponse.
     */
    public LoginResponse loginUsuario(LoginRequest request) {

        logger.info("Intento de login para usuario: {}", request.getCedula());

        try {
            Usuario user = usuarioDAO.obtenerPorId(request.getCedula());

            if (user == null) {
                logger.warn("Usuario no encontrado: {}", request.getCedula());
                throw new EntityNotFoundException("Usuario", request.getCedula());
            }

            if (!request.getPass().equals(user.getPass())) {
                logger.warn("Contraseña incorrecta para usuario: {}", request.getCedula());
                throw new ValidationException("Contraseña incorrecta");
            }

            String token = jwt.create(request.getCedula(), user.getNombre());

            LoginResponse loginResponse = LoginResponse.builder()
                    .token(token)
                    .nombre(user.getNombre())
                    .cedula(user.getCedula())
                    .build();

            return loginResponse;

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
