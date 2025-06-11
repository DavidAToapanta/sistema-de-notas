package model;

public class DetalleMatricula {

    private int id;
    private int idMatricula;
    private int idMateria;

    public DetalleMatricula(){}

    public DetalleMatricula(int idMatricula, int idMateria) {
        this.idMatricula = idMatricula;
        this.idMateria = idMateria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }
}
