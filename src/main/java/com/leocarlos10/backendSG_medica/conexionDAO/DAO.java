package com.leocarlos10.backendSG_medica.conexionDAO;

import java.sql.SQLException;
import java.util.List;
/**
 * Interface con genericos 
 * @T - para el tipo de enidades
 * @ID - para variar el tipo del identificador
 */
public interface DAO<T, ID> {
    int registrar(T t) throws SQLException;
    List<T> obtenerTodo() throws SQLException;
    T obtenerPorId(ID id) throws SQLException;
    int actualizar(T t) throws SQLException;
    int eliminar(ID id) throws SQLException;
}