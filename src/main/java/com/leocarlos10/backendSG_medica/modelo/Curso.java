package com.leocarlos10.backendSG_medica.modelo;

import lombok.Getter;
import lombok.Setter;



public class Curso {
    @Setter @Getter
    private int id;
    @Setter @Getter
    private String titulo;
    @Setter @Getter
    private String descripcion;
    @Setter @Getter
    private String des_larga;
    @Setter @Getter
    private String url_imagen;

    public Curso(int id, String titulo, String descripcion, String des_larga, String url_imagen) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.des_larga = des_larga;
        this.url_imagen = url_imagen;
    }

    public Curso() {
    }
}
