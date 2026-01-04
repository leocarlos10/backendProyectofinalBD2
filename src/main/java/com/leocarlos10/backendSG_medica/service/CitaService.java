package com.leocarlos10.backendSG_medica.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.leocarlos10.backendSG_medica.Models.Cita;
import com.leocarlos10.backendSG_medica.Models.CitaUsuarioDTO;
import com.leocarlos10.backendSG_medica.conexionDAO.CitaDAO;
import com.leocarlos10.backendSG_medica.exception.EntityNotFoundException;
import com.leocarlos10.backendSG_medica.exception.ValidationException;
import com.leocarlos10.backendSG_medica.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
public class CitaService {

    private static final Logger logger = LoggerFactory.getLogger(CitaService.class);

    @Autowired
    private CitaDAO citaDAO;

    /**
     * Registra una nueva cita
     * 
     * @param cita: La cita a registrar
     * @return: La cita registrada
     */
    @Transactional
    public Cita registrarCita(Cita cita) {
        logger.info("Registrando nueva cita para usuario: {}", cita.getCedula_usuario());

        if (cita == null || cita.getCedula_usuario() == null) {
            throw new ValidationException("La cédula del usuario es requerida");
        }

        try {
            int filas = citaDAO.registrar(cita);
            if (filas > 0) {
                logger.info("Cita registrada exitosamente");
                return cita;
            } else {
                throw new BusinessException("CITA_NO_REGISTRADA", "No se pudo registrar la cita");
            }
        } catch (Exception e) {
            logger.error("Error al registrar cita: {}", e.getMessage(), e);
            throw new BusinessException("CITA_ERROR", "Error al registrar la cita", e);
        }
    }

    /**
     * Obtiene todas las citas
     * 
     * @return: Lista de citas
     */
    public List<Cita> obtenerTodasLasCitas() {
        logger.info("Obteniendo todas las citas");
        try {
            List<Cita> citas = citaDAO.obtenerTodo();
            if (citas.isEmpty()) {
                throw new EntityNotFoundException("No se encontraron citas");
            }
            return citas;
        } catch (Exception e) {
            logger.error("Error al obtener citas: {}", e.getMessage(), e);
            throw new BusinessException("OBTENER_CITAS_ERROR", "Error al obtener las citas", e);
        }
    }

    /**
     * Obtiene una cita por ID
     * 
     * @param id: ID de la cita
     * @return: La cita
     */
    public Cita obtenerPorId(Integer id) {
        logger.info("Obteniendo cita con ID: {}", id);

        if (id == null || id <= 0) {
            throw new ValidationException("El ID de la cita es inválido");
        }

        try {
            Cita cita = citaDAO.obtenerPorId(id);
            if (cita == null) {
                throw new EntityNotFoundException("Cita", String.valueOf(id));
            }
            return cita;
        } catch (Exception e) {
            logger.error("Error al obtener cita: {}", e.getMessage(), e);
            throw new BusinessException("OBTENER_CITA_ERROR", "Error al obtener la cita", e);
        }
    }

    /**
     * Obtiene las citas de un usuario específico
     * 
     * @param cedula: Cédula del usuario
     * @return: Lista de citas del usuario
     */
    public List<Cita> obtenerCitasPorUsuario(String cedula) {
        logger.info("Obteniendo citas para usuario: {}", cedula);

        if (cedula == null || cedula.isEmpty()) {
            throw new ValidationException("La cédula del usuario es requerida");
        }

        try {
            List<Cita> citas = citaDAO.obtenerCitaPorUsuario(cedula);
            if (citas.isEmpty()) {
                throw new EntityNotFoundException("No se encontraron citas para el usuario: " + cedula);
            }
            return citas;
        } catch (Exception e) {
            logger.error("Error al obtener citas del usuario: {}", e.getMessage(), e);
            throw new BusinessException("OBTENER_CITAS_USUARIO_ERROR", "Error al obtener las citas", e);
        }
    }

    /**
     * Obtiene citas con información del usuario
     * 
     * @return: Lista de DTOs con cita y usuario
     */
    public List<CitaUsuarioDTO> obtenerCitasConUsuario() {
        logger.info("Obteniendo citas con información de usuario");
        try {
            List<CitaUsuarioDTO> citas = citaDAO.obtenerUsuarioCita();
            if (citas.isEmpty()) {
                throw new EntityNotFoundException("No se encontraron citas con usuarios");
            }
            return citas;
        } catch (Exception e) {
            logger.error("Error al obtener citas con usuario: {}", e.getMessage(), e);
            throw new BusinessException("OBTENER_CITAS_USUARIO_ERROR", "Error al obtener las citas", e);
        }
    }

    /**
     * Obtiene citas de la papelera
     * 
     * @return: Lista de citas eliminadas
     */
    public List<Cita> obtenerPapeleraCitas() {
        logger.info("Obteniendo citas de papelera");
        try {
            List<Cita> citas = citaDAO.obtenerPapeleraCita();
            if (citas.isEmpty()) {
                throw new EntityNotFoundException("No hay citas en papelera");
            }
            return citas;
        } catch (Exception e) {
            logger.error("Error al obtener papelera de citas: {}", e.getMessage(), e);
            throw new BusinessException("PAPELERA_ERROR", "Error al obtener la papelera", e);
        }
    }

    /**
     * Actualiza una cita
     * 
     * @param cita: La cita con datos actualizados
     * @return: La cita actualizada
     */
    @Transactional
    public Cita actualizarCita(Cita cita) {
        logger.info("Actualizando cita con ID: {}", cita.getId_cita());

        if (cita == null || cita.getId_cita() <= 0) {
            throw new ValidationException("El ID de la cita es inválido");
        }

        try {
            int filas = citaDAO.actualizar(cita);
            if (filas > 0) {
                logger.info("Cita actualizada exitosamente");
                return cita;
            } else {
                throw new BusinessException("CITA_NO_ACTUALIZADA", "No se pudo actualizar la cita");
            }
        } catch (Exception e) {
            logger.error("Error al actualizar cita: {}", e.getMessage(), e);
            throw new BusinessException("ACTUALIZAR_CITA_ERROR", "Error al actualizar la cita", e);
        }
    }

    /**
     * Elimina una cita
     * 
     * @param id: ID de la cita a eliminar
     */
    @Transactional
    public void eliminarCita(Integer id) {
        logger.info("Eliminando cita con ID: {}", id);

        if (id == null || id <= 0) {
            throw new ValidationException("El ID de la cita es inválido");
        }

        try {
            int filas = citaDAO.eliminar(id);
            if (filas > 0) {
                logger.info("Cita eliminada exitosamente");
            } else {
                throw new BusinessException("CITA_NO_ELIMINADA", "No se pudo eliminar la cita");
            }
        } catch (Exception e) {
            logger.error("Error al eliminar cita: {}", e.getMessage(), e);
            throw new BusinessException("ELIMINAR_CITA_ERROR", "Error al eliminar la cita", e);
        }
    }
}
