package com.leocarlos10.backendSG_medica.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.leocarlos10.backendSG_medica.Models.Diagnostico;
import com.leocarlos10.backendSG_medica.conexionDAO.DiagnosticoDAO;
import com.leocarlos10.backendSG_medica.dto.usuario.UsuarioDiagnosticoDTO;
import com.leocarlos10.backendSG_medica.exception.EntityNotFoundException;
import com.leocarlos10.backendSG_medica.exception.ValidationException;

import lombok.RequiredArgsConstructor;

import com.leocarlos10.backendSG_medica.exception.BusinessException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiagnosticoService extends service {

    private DiagnosticoDAO diagnosticoDAO;

    /**
     * Obtiene todos los diagnósticos
     * 
     * @return: Lista de diagnósticos
     */
    public List<Diagnostico> obtenerTodos() {
        logger.info("Obteniendo todos los diagnósticos");
        try {
            List<Diagnostico> diagnosticos = diagnosticoDAO.obtenerTodo();
            if (diagnosticos.isEmpty()) {
                throw new EntityNotFoundException("No se encontraron diagnósticos");
            }
            return diagnosticos;
        } catch (Exception e) {
            logger.error("Error al obtener diagnósticos: {}", e.getMessage(), e);
            throw new BusinessException("OBTENER_DIAGNOSTICOS_ERROR", "Error al obtener los diagnósticos", e);
        }
    }

    /**
     * Obtiene un diagnóstico por ID
     * 
     * @param id: ID del diagnóstico
     * @return: El diagnóstico
     */
    public Diagnostico obtenerPorId(Integer id) {
        logger.info("Obteniendo diagnóstico con ID: {}", id);

        if (id == null || id <= 0) {
            throw new ValidationException("El ID del diagnóstico es inválido");
        }

        try {
            Diagnostico diagnostico = diagnosticoDAO.obtenerPorId(id);
            if (diagnostico == null) {
                throw new EntityNotFoundException("Diagnóstico", String.valueOf(id));
            }
            return diagnostico;
        } catch (Exception e) {
            logger.error("Error al obtener diagnóstico: {}", e.getMessage(), e);
            throw new BusinessException("OBTENER_DIAGNOSTICO_ERROR", "Error al obtener el diagnóstico", e);
        }
    }

    /**
     * Obtiene diagnósticos con información del usuario
     * 
     * @return: Lista de DTOs con diagnóstico y usuario
     */
    public List<UsuarioDiagnosticoDTO> obtenerDiagnosticosConUsuario() {
        logger.info("Obteniendo diagnósticos con información de usuario");
        try {
            List<UsuarioDiagnosticoDTO> diagnosticos = diagnosticoDAO.obtenerDiagnosticosConUsuario();
            if (diagnosticos.isEmpty()) {
                throw new EntityNotFoundException("No se encontraron diagnósticos con usuarios");
            }
            return diagnosticos;
        } catch (Exception e) {
            logger.error("Error al obtener diagnósticos con usuario: {}", e.getMessage(), e);
            throw new BusinessException("OBTENER_DIAGNOSTICOS_USUARIO_ERROR", "Error al obtener los diagnósticos", e);
        }
    }

    /**
     * Obtiene diagnósticos de un usuario específico
     * 
     * @param cedula: Cédula del usuario
     * @return: Lista de diagnósticos del usuario
     */
    public List<UsuarioDiagnosticoDTO> obtenerDiagnosticosPorUsuario(String cedula) {
        logger.info("Obteniendo diagnósticos para usuario: {}", cedula);

        if (cedula == null || cedula.isEmpty()) {
            throw new ValidationException("La cédula del usuario es requerida");
        }

        try {
            List<UsuarioDiagnosticoDTO> diagnosticos = diagnosticoDAO.obtenerDiagnosticosPorUsuario(cedula);
            if (diagnosticos.isEmpty()) {
                throw new EntityNotFoundException("No se encontraron diagnósticos para el usuario: " + cedula);
            }
            return diagnosticos;
        } catch (Exception e) {
            logger.error("Error al obtener diagnósticos del usuario: {}", e.getMessage(), e);
            throw new BusinessException("OBTENER_DIAGNOSTICOS_USUARIO_ERROR", "Error al obtener los diagnósticos", e);
        }
    }

    /**
     * Obtiene diagnósticos de una historia clínica
     * 
     * @param idHistoria: ID de la historia clínica
     * @return: Lista de diagnósticos
     */
    public List<Diagnostico> obtenerDiagnosticosPorHistoria(Integer idHistoria) {
        logger.info("Obteniendo diagnósticos para historia: {}", idHistoria);

        if (idHistoria == null || idHistoria <= 0) {
            throw new ValidationException("El ID de la historia es inválido");
        }

        try {
            List<Diagnostico> diagnosticos = diagnosticoDAO.obtenerDiagnosticosPorHistoria(idHistoria);
            if (diagnosticos.isEmpty()) {
                throw new EntityNotFoundException("No se encontraron diagnósticos para esta historia");
            }
            return diagnosticos;
        } catch (Exception e) {
            logger.error("Error al obtener diagnósticos de historia: {}", e.getMessage(), e);
            throw new BusinessException("OBTENER_DIAGNOSTICOS_HISTORIA_ERROR", "Error al obtener los diagnósticos", e);
        }
    }

    /**
     * Registra un nuevo diagnóstico
     * 
     * @param diagnostico: El diagnóstico a registrar
     * @return: El diagnóstico registrado
     */
    @Transactional
    public Diagnostico registrarDiagnostico(Diagnostico diagnostico) {
        logger.info("Registrando nuevo diagnóstico");

        if (diagnostico == null) {
            throw new ValidationException("El diagnóstico no puede ser nulo");
        }

        try {
            int resultado = diagnosticoDAO.registrar(diagnostico);
            if (resultado > 0) {
                logger.info("Diagnóstico registrado exitosamente");
                return diagnostico;
            } else {
                throw new BusinessException("DIAGNOSTICO_NO_REGISTRADO", "No se pudo registrar el diagnóstico");
            }
        } catch (Exception e) {
            logger.error("Error al registrar diagnóstico: {}", e.getMessage(), e);
            throw new BusinessException("REGISTRAR_DIAGNOSTICO_ERROR", "Error al registrar el diagnóstico", e);
        }
    }

    /**
     * Actualiza un diagnóstico
     * 
     * @param diagnostico: El diagnóstico con datos actualizados
     * @return: El diagnóstico actualizado
     */
    @Transactional
    public Diagnostico actualizarDiagnostico(Diagnostico diagnostico) {
        logger.info("Actualizando diagnóstico con ID: {}", diagnostico.getId_diagnostico());

        if (diagnostico == null || diagnostico.getId_diagnostico() <= 0) {
            throw new ValidationException("El ID del diagnóstico es inválido");
        }

        try {
            int resultado = diagnosticoDAO.actualizar(diagnostico);
            if (resultado > 0) {
                logger.info("Diagnóstico actualizado exitosamente");
                return diagnostico;
            } else {
                throw new BusinessException("DIAGNOSTICO_NO_ACTUALIZADO", "No se pudo actualizar el diagnóstico");
            }
        } catch (Exception e) {
            logger.error("Error al actualizar diagnóstico: {}", e.getMessage(), e);
            throw new BusinessException("ACTUALIZAR_DIAGNOSTICO_ERROR", "Error al actualizar el diagnóstico", e);
        }
    }

    /**
     * Elimina un diagnóstico
     * 
     * @param id: ID del diagnóstico a eliminar
     */
    @Transactional
    public boolean eliminarDiagnostico(Integer id) {
        logger.info("Eliminando diagnóstico con ID: {}", id);

        if (id == null || id <= 0) {
            throw new ValidationException("El ID del diagnóstico es inválido");
        }

        try {
            int resultado = diagnosticoDAO.eliminar(id);
            if (resultado > 0) {
                logger.info("Diagnóstico eliminado exitosamente");
                return true;
            } else {
                throw new BusinessException("DIAGNOSTICO_NO_ELIMINADO", "No se pudo eliminar el diagnóstico");
            }
        } catch (Exception e) {
            logger.error("Error al eliminar diagnóstico: {}", e.getMessage(), e);
            throw new BusinessException("ELIMINAR_DIAGNOSTICO_ERROR", "Error al eliminar el diagnóstico", e);
        }
    }

    /**
     * Crea un diagnóstico junto con una historia clínica
     * 
     * @param cedulaUsuario: Cédula del usuario
     * @param tratamiento:   Tratamiento del diagnóstico
     * @param observaciones: Observaciones
     * @param notaCorta:     Nota corta
     * @param notaLarga:     Nota larga
     * @param fecha:         Fecha del diagnóstico
     * @return: Resultado de la operación
     */
    @Transactional
    public boolean crearDiagnosticoConHistoria(String cedulaUsuario, String tratamiento, String observaciones,
            String notaCorta, String notaLarga, java.time.LocalDate fecha) {
        logger.info("Creando diagnóstico con historia para usuario: {}", cedulaUsuario);

        if (cedulaUsuario == null || cedulaUsuario.isEmpty()) {
            throw new ValidationException("La cédula del usuario es requerida");
        }

        try {
            java.sql.Date sqlDate = java.sql.Date.valueOf(fecha);
            int resultado = diagnosticoDAO.crearDiagnosticoConHistoria(cedulaUsuario, tratamiento, observaciones,
                    notaCorta, notaLarga, sqlDate);
            if (resultado > 0) {
                logger.info("Diagnóstico con historia creado exitosamente");
                return true;
            } else {
                throw new BusinessException("DIAGNOSTICO_NO_CREADO", "No se pudo crear el diagnóstico con historia");
            }
        } catch (Exception e) {
            logger.error("Error al crear diagnóstico con historia: {}", e.getMessage(), e);
            throw new BusinessException("CREAR_DIAGNOSTICO_HISTORIA_ERROR", "Error al crear el diagnóstico", e);
        }
    }
}
