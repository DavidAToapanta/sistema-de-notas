package model;

public class Nota {

    private int id;
    private int idDetalleMatricula;
    private double pacrial1;
    private double pacrial2;
    private double examenFinal;

    private double promedio;
    private boolean aprovado;

    public Nota() {}

    public Nota(int idDetalleMatricula, double pacrial1, double pacrial2, double examenFinal, double promedio) {
        this.idDetalleMatricula = idDetalleMatricula;
        this.pacrial1 = pacrial1;
        this.pacrial2 = pacrial2;
        this.examenFinal = examenFinal;
        this.promedio = promedio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDetalleMatricula() {
        return idDetalleMatricula;
    }

    public void setIdDetalleMatricula(int idDetalleMatricula) {
        this.idDetalleMatricula = idDetalleMatricula;
    }

    public double getPacrial1() {
        return pacrial1;
    }

    public void setPacrial1(double pacrial1) {
        this.pacrial1 = pacrial1;
    }

    public double getPacrial2() {
        return pacrial2;
    }

    public void setPacrial2(double pacrial2) {
        this.pacrial2 = pacrial2;
    }

    public double getExamenFinal() {
        return examenFinal;
    }

    public void setExamenFinal(double examenFinal) {
        this.examenFinal = examenFinal;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }
}
