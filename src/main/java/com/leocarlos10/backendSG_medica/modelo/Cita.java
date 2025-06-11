package com.leocarlos10.backendSG_medica.modelo;


import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

public class Cita {
    @Setter @Getter
    private int id;

    @Setter @Getter
    private LocalDateTime fechahora;
    @Setter @Getter
    private String servicio;

    public Cita(int id, LocalDateTime fechahora, String servicio) {
        this.id = id;
        this.fechahora = fechahora;
        this.servicio = servicio;
    }

    public Cita (){}
}
