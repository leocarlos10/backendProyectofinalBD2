package com.leocarlos10.backendSG_medica.conexionDAO;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    int registrar(T t) throws SQLException;
    List<T> obtenerTodo() throws SQLException;
    T obtenerPorId(int id) throws SQLException;
    int actualizar(T t) throws SQLException;
    int eliminar(int id) throws SQLException;
}