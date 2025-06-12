package com.leocarlos10.backendSG_medica.conexionDAO;

import com.leocarlos10.backendSG_medica.Models.Cita;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CitaDAO implements DAO<Cita> {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private static final class CitaRowMapper implements RowMapper<Cita> {
        @Override
        public Cita mapRow(ResultSet rs, int rowNum) throws SQLException {
            Cita cita = new Cita();
            cita.setId_cita(rs.getInt("id_cita"));
            cita.setFechaHora(rs.getTimestamp("fechaHora").toLocalDateTime());
            cita.setEstado(rs.getString("estado"));
            cita.setMotivoC(rs.getString("motivoC"));
            cita.setRemitente(rs.getString("remitente"));
            cita.setFechaU_Valoracion(rs.getDate("fechaU_Valoracion").toLocalDate());
            cita.setCedula_usuario(rs.getString("cedula_usuario"));
            cita.setTipo_cita(rs.getString("tipo_cita"));
            return cita;
        }
    }

    @Override
    public int registrar(Cita cita) throws SQLException {
        String sql = "INSERT INTO cita (fechaHora, estado, motivoC, remitente, fechaU_Valoracion, cedula_usuario, tipo_cita) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, 
            cita.getFechaHora(),
            cita.getEstado(),
            cita.getMotivoC(),
            cita.getRemitente(),
            cita.getFechaU_Valoracion(),
            cita.getCedula_usuario(),
            cita.getTipo_cita()
        );
    }

    @Override
    public List<Cita> obtenerTodo() throws SQLException {
        String sql = "SELECT * FROM cita";
        return jdbcTemplate.query(sql, new CitaRowMapper());
    }

    @Override
    public Cita obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM cita WHERE id_cita = ?";
        return jdbcTemplate.queryForObject(sql, new CitaRowMapper(), id);
    }

    @Override
    public int actualizar(Cita cita) throws SQLException {
        String sql = "UPDATE cita SET fechaHora = ?, estado = ?, motivoC = ?, remitente = ?, fechaU_Valoracion = ?, cedula_usuario = ?, tipo_cita = ? WHERE id_cita = ?";
        return jdbcTemplate.update(sql,
            cita.getFechaHora(),
            cita.getEstado(),
            cita.getMotivoC(),
            cita.getRemitente(),
            cita.getFechaU_Valoracion(),
            cita.getCedula_usuario(),
            cita.getTipo_cita(),
            cita.getId_cita()
        );
    }

    @Override
    public int eliminar(int id) throws SQLException {
        String sql = "DELETE FROM cita WHERE id_cita = ?";
        return jdbcTemplate.update(sql, id);
    }
}
