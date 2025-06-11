package model;

public class Modulo {
//    CREATE TABLE tb_modulo (
//            mo_id INT PRIMARY KEY AUTO_INCREMENT,
//            mo_nombre VARCHAR(100) NOT NULL,
//    mo_nro_materias INT NOT NULL,
//    ca_id INT,
//    FOREIGN KEY (ca_id) REFERENCES tb_carrera(ca_id)
//            );
    private int id;
    private String nombre;
    private int numeroMateria;
    private int idCarrera;

    public Modulo() {}

    public Modulo(String nombre, int numeroMateria, int idCarrera) {
        this.nombre = nombre;
        this.numeroMateria = numeroMateria;
        this.idCarrera = idCarrera;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroMateria() {
        return numeroMateria;
    }

    public void setNumeroMateria(int numeroMateria) {
        this.numeroMateria = numeroMateria;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }
}
