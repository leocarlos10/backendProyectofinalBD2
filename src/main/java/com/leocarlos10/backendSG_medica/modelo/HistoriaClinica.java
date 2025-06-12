package com.leocarlos10.backendSG_medica.modelo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

public class HistoriaClinica {
    @Getter @Setter
    private int idhistoriaClinica;
    @Getter @Setter
    private Date fechaNacimiento;
    @Getter @Setter
    private Date fechaCreacion;
    @Getter @Setter
    private String cedulaUsuario;

    HistoriaClinica() {
    }
    
    public HistoriaClinica(int idhistoriaClinica, Date fechaNacimiento, Date fechaCreacion, String cedulaUsuario) {
        this.idhistoriaClinica = idhistoriaClinica;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaCreacion = fechaCreacion;
        this.cedulaUsuario = cedulaUsuario;
    }
}