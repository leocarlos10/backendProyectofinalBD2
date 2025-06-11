package com.leocarlos10.backendSG_medica.conexionDAO;


import java.util.List;

public interface DAO <T>{

    List<T> get(String object);

    List<T> getAll();
    boolean eliminar(Long id);
    int registrar(T entidad);
    void actualizar(T entidad);

}