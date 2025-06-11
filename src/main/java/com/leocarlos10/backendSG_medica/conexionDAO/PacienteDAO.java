package com.leocarlos10.backendSG_medica.conexionDAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.leocarlos10.backendSG_medica.modelo.Paciente;

@Repository
public class PacienteDAO implements DAO<Paciente> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PacienteDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Paciente> get(String entity) {
        return null;
    }


    @Override
    public List<Paciente> getAll() {

        return null;
    }

    @Override
    public boolean eliminar(Long id) {
       return false;
    }

    @Override
    public int registrar(Paciente paciente) {
        System.out.println(paciente.getTelefono());
        String sql = "INSERT INTO paciente (cedula, nombre, apellido, fechaNacimiento, telefono, fechaUltValoracion, ciudad, motivoC, remitente, idcita) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
       int filasAfectadas =  jdbcTemplate.update(sql, paciente.getCedula() , paciente.getNombre(), paciente.getApellido(),
                paciente.getFechaNacimiento(), paciente.getTelefono(), paciente.getFechaUltValoracion(),
                paciente.getCiudad(), paciente.getMotivoC(), paciente.getRemitente(), paciente.getIdcita());
        System.out.println(filasAfectadas);
       return filasAfectadas;
    }


    @Override
    public void actualizar(Paciente entidad) {
        // TODO Auto-generated method stub
    }

}
