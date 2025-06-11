package model;

import java.time.LocalDate;

public class Matricula {

    private int id;
    private LocalDate fecha;
    private String periodo;
    private int idCarrera;
    private int idEstudiante;

    public Matricula(){}

    public Matricula(LocalDate fecha, String periodo, int idCarrera, int idEstudiante) {
        this.fecha = fecha;
        this.periodo = periodo;
        this.idCarrera = idCarrera;
        this.idEstudiante = idEstudiante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }
}
