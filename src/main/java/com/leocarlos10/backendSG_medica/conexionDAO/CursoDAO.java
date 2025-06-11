package com.leocarlos10.backendSG_medica.conexionDAO;

import com.leocarlos10.backendSG_medica.modelo.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class CursoDAO implements DAO<Curso>{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CursoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Curso> get(String entity) {
        return null;
    }


    @Override
    public List<Curso> getAll() {
        String sql = "SELECT * FROM curso ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Curso.class));
    }

    @Override
    public boolean eliminar(Long id) {
        return false;
    }

    @Override
    public int registrar(Curso entidad) {
        return 0;
    }


    @Override
    public void actualizar(Curso entidad) {

    }
}
