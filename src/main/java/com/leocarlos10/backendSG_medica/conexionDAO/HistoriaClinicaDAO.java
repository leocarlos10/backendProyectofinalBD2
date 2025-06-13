package com.leocarlos10.backendSG_medica.conexionDAO;

import com.leocarlos10.backendSG_medica.Models.HistoriaClinica;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Repository: Indica que esta clase es un repositorio de Spring
 * @Transactional: Indica que esta clase es transaccional,
 * Garantiza que las operaciones de base de datos sean atómicas y consistentes, 
 * manejando automáticamente las transacciones.
 */

@Repository
@Transactional
public class HistoriaClinicaDAO implements DAO<HistoriaClinica> {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private static final class HistoriaClinicaRowMapper implements RowMapper<HistoriaClinica> {
        @Override
        public HistoriaClinica mapRow(ResultSet rs, int rowNum) throws SQLException {
            HistoriaClinica historia = new HistoriaClinica();
            historia.setId_historia(rs.getInt("id_historia"));
            historia.setFecha_nacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
            historia.setFecha_creacion(rs.getDate("fecha_creacion").toLocalDate());
            historia.setCedula_usuario(rs.getString("cedula_usuario"));
            return historia;
        }
    }

    @Override
    public int registrar(HistoriaClinica historia) throws SQLException {
        String sql = "INSERT INTO historiaclinica (fecha_nacimiento, fecha_creacion, cedula_usuario) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, 
            historia.getFecha_nacimiento(),
            historia.getFecha_creacion(),
            historia.getCedula_usuario()
        );
    }

    @Override
    public List<HistoriaClinica> obtenerTodo() throws SQLException {
        String sql = "SELECT * FROM historiaclinica";
        return jdbcTemplate.query(sql, new HistoriaClinicaRowMapper());
    }

    @Override
    public HistoriaClinica obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM historiaclinica WHERE id_historia = ?";
        return jdbcTemplate.queryForObject(sql, new HistoriaClinicaRowMapper(), id);
    }

    @Override
    public int actualizar(HistoriaClinica historia) throws SQLException {
        String sql = "UPDATE historiaclinica SET fecha_nacimiento = ?, fecha_creacion = ?, cedula_usuario = ? WHERE id_historia = ?";
        return jdbcTemplate.update(sql,
            historia.getFecha_nacimiento(),
            historia.getFecha_creacion(),
            historia.getCedula_usuario(),
            historia.getId_historia()
        );
    }

    @Override
    public int eliminar(int id) throws SQLException {
        String sql = "DELETE FROM historiaclinica WHERE id_historia = ?";
        return jdbcTemplate.update(sql, id);
    }
} 