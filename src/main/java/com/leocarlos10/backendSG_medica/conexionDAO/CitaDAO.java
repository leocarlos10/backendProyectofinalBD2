package com.leocarlos10.backendSG_medica.conexionDAO;

import com.leocarlos10.backendSG_medica.modelo.Cita;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CitaDAO implements DAO<Cita> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CitaDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Cita> get(String entity) {
        return null;
    }

    @Override
    public List<Cita> getAll() {
        String sql = "SELECT * FROM cita";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Cita.class));
    }

    @Override
    public boolean eliminar(Long id) {
        String sql = "DELETE FROM cita WHERE idCita = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, id);
            return rowsAffected > 0;  // Retorna true si la cita fue eliminada
        } catch (Exception e) {
            e.printStackTrace();
            return false;  // Retorna false si ocurrió un error
        }   
    }
    // el metodo guarda la cita y devuelve el id de esa misma cita para usarlo en el frontend
    @Override
    public int registrar(Cita cita) {

        String sql = "INSERT INTO cita (servicio, fechahora) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cita.getServicio());
            ps.setObject(2, cita.getFechahora());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue(); // Devuelve el ID generado
    }

    @Override
    public void actualizar(Cita entidad) {

    }
}
