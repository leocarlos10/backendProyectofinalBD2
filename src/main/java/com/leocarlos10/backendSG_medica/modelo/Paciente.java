package com.leocarlos10.backendSG_medica.modelo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class Paciente {

    @Setter @Getter
    private int cedula;
    @Setter @Getter
    private String nombre;
    @Setter @Getter
    private String apellido;
    @Setter @Getter
    private LocalDate fechaNacimiento;
    @Setter @Getter
    private String telefono;
    @Setter @Getter
    private LocalDate fechaUltValoracion;
    @Setter @Getter
    private String ciudad;
    @Setter @Getter
    private String motivoC;
    @Setter @Getter
    private String remitente;
    @Setter @Getter
    private int idcita;

    public Paciente() {
    }

    public Paciente( int cedula, String nombre, String apellido, LocalDate fechaNacimiento, String telefono, LocalDate fechaUltValoracion, String ciudad, String motivoC, String remitente, int idcita) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.fechaUltValoracion = fechaUltValoracion;
        this.ciudad = ciudad;
        this.motivoC = motivoC;
        this.remitente = remitente;
        this.idcita = idcita;
    }
}
