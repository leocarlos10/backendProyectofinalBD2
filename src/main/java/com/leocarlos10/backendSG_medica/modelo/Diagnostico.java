package com.leocarlos10.backendSG_medica.modelo;

import lombok.Getter;
import lombok.Setter;

public class Diagnostico {
    @Getter @Setter
    private int idDiagnostico;
    @Getter @Setter
    private String tratamiento;
    @Getter @Setter
    private String observaciones;
    @Getter @Setter
    private int idhistoriaClinica;
    @Getter @Setter
    private String notaCorta;
    @Getter @Setter
    private String notaLarga;
    @Getter @Setter
    private String fechaDiagnostico;

    public Diagnostico() {
    }
    public Diagnostico(int idDiagnostico, String tratamiento, String observaciones, int idhistoriaClinica, String notaCorta, String notaLarga, String fechaDiagnostico) {
        this.idDiagnostico = idDiagnostico;
        this.tratamiento = tratamiento;
        this.observaciones = observaciones;
        this.idhistoriaClinica = idhistoriaClinica;
        this.notaCorta = notaCorta;
        this.notaLarga = notaLarga;
        this.fechaDiagnostico = fechaDiagnostico;
    }
    
}
