package model;

public class Materia {
    private int id;
    private String nombre;
    private int numCreditos;
    private int duracionDias;
    private String horario;
    private int idDocente;  // FK hacia tb_docente
    private int idModulo;  // FK hacia tb_carrera

    public Materia() {}

    public Materia(String nombre, int numCreditos, int duracionDias, String horario, int idDocente, int idModulo) {
        this.nombre = nombre;
        this.numCreditos = numCreditos;
        this.duracionDias = duracionDias;
        this.horario = horario;
        this.idDocente = idDocente;
        this.idModulo = idModulo;
    }



    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getNumCreditos() { return numCreditos; }
    public void setNumCreditos(int numCreditos) { this.numCreditos = numCreditos; }

    public int getDuracionDias() { return duracionDias; }
    public void setDuracionDias(int duracionDias) { this.duracionDias = duracionDias; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public int getIdDocente() { return idDocente; }
    public void setIdDocente(int idDocente) { this.idDocente = idDocente; }

    public int getIdModulo() { return idModulo; }
    public void setIdModulo(int idModulo) { this.idModulo = idModulo; }
}
