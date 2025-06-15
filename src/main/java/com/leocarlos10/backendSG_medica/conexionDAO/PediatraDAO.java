package com.leocarlos10.backendSG_medica.conexionDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.leocarlos10.backendSG_medica.Models.Pediatra;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@Transactional
public class PediatraDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private static final class PediatraRowMapper implements RowMapper<Pediatra> {
        @Override
        public Pediatra mapRow(ResultSet rs, int rowNum) throws SQLException {
            Pediatra pediatra = new Pediatra();
            pediatra.setUsuario(rs.getString("usuario"));
            pediatra.setPass(rs.getString("pass"));
            return pediatra;
        }
    }


    public Pediatra obtenerPediatra(Pediatra pediatra) throws SQLException {
        String sql = "SELECT * FROM pediatra WHERE usuario = ? AND pass = ?";
        return jdbcTemplate.queryForObject(sql, new PediatraRowMapper(), pediatra.getUsuario(), pediatra.getPass());
    }
    
}
