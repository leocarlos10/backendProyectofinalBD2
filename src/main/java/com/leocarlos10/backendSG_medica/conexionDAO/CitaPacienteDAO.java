package com.leocarlos10.backendSG_medica.conexionDAO;

import com.leocarlos10.backendSG_medica.modelo.CitaPacienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CitaPacienteDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CitaPacienteDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CitaPacienteDTO> getCitasPacientes() {

        String sql = """
                    SELECT
                    cita.idCita,
                    cita.fechahora,
                    cita.servicio,
                    paciente.nombre,
                    paciente.telefono,
                    paciente.fechaUltValoracion,
                    paciente.motivoC,
                    paciente.remitente
                FROM
                    paciente
                INNER JOIN
                    cita
                ON
                    paciente.idcita = cita.idCita;
                """;

        return jdbcTemplate.query(sql, new CitaPacienteRowMapper());
    }

    private static class CitaPacienteRowMapper implements RowMapper<CitaPacienteDTO> {
        @Override
        public CitaPacienteDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            CitaPacienteDTO dto = new CitaPacienteDTO();
            dto.setIdCita(rs.getLong("idCita"));
            dto.setFechahora(rs.getTimestamp("fechahora"));
            dto.setServicio(rs.getString("servicio"));
            dto.setNombre(rs.getString("nombre"));
            dto.setTelefono(rs.getString("telefono"));
            dto.setFechaUltValoracion(rs.getDate("fechaUltValoracion"));
            dto.setMotivoC(rs.getString("motivoC"));
            dto.setRemitente(rs.getString("remitente"));
            return dto;
        }
    }
}
