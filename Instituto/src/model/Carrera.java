package model;

public class Carrera {
    private int id;
    private String nombre;
    private int numModulo;
    private int tiempoDuracion;

    public Carrera() {}

    public Carrera(String nombre, int numModulo, int tiempoDuracion) {
        this.nombre = nombre;
        this.numModulo = numModulo;
        this.tiempoDuracion = tiempoDuracion;
    }

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public int getNumModulo() {return numModulo;}
    public void setNumModulo(int numModulo) {this.numModulo = numModulo;}

    public int getTiempoDuracion() {return tiempoDuracion;}
    public void setTiempoDuracion(int tiempoDuracion){this.tiempoDuracion = tiempoDuracion;}
}
