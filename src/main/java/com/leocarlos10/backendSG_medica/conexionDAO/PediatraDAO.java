package com.leocarlos10.backendSG_medica.conexionDAO;

import com.leocarlos10.backendSG_medica.modelo.Pediatra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class PediatraDAO implements DAO<Pediatra> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PediatraDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @SuppressWarnings("deprecation")
    @Override
    public List<Pediatra> get(String nombre) {

        String sql = "SELECT * FROM pediatra WHERE nombre = ?";
         return jdbcTemplate.query(sql, new Object[] { nombre }, new BeanPropertyRowMapper<>(Pediatra.class));
    }


    @Override
    public List<Pediatra> getAll() {
        return null;
    }

    @Override
    public boolean eliminar(Long id) {
        return false;
    }

    @Override
    public int registrar(Pediatra entidad) {
        return 0;
    }


    @Override
    public void actualizar(Pediatra entidad) {

    }
}