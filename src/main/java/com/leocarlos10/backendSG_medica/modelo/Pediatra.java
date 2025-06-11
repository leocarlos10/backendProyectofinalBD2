package com.leocarlos10.backendSG_medica.modelo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class Pediatra {

    @Setter @Getter
    private int id;
    @Setter @Getter
    private String nombre;
    @Setter @Getter
    private String especialidad;
    @Setter @Getter
    private String consultorio;
    @Setter @Getter
    private String telefono;
    @Setter @Getter
    private LocalDateTime horarioAsem;
    @Setter @Getter
    private LocalDateTime horarioAFsem;
    @Setter @Getter
    private String pass;
    @Setter @Getter
    private String cedulap;

    public Pediatra(int id, String nombre, String especialidad, String consultorio, String telefono, LocalDateTime horarioAsem, LocalDateTime horarioAFsem, String pass, String cedulap) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.consultorio = consultorio;
        this.telefono = telefono;
        this.horarioAsem = horarioAsem;
        this.horarioAFsem = horarioAFsem;
        this.pass = pass;
        this.cedulap = cedulap;
    }

    public Pediatra(){}
}
