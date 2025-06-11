package model;

public class Docente{

    private int id;
    private String nombre;
    private String apellido;

    public Docente() {}

    public Docente(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getIdDocente(){return id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre(){return nombre;}
    public void setNombre(String nombre){this.nombre = nombre;}



    public String getApellido(){return apellido;}
    public void setApellido(String apellido){this.apellido = apellido;}
}
