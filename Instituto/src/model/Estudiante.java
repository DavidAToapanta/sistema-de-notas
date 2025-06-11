package model;

public class Estudiante {

        private int id;
        private String tipoIdentificacion;
        private String identificacion;
        private String nombres;
        private String apellidos;
        private String direccion;
        private String estadoCivil;
        private int cargasFamiliares;
        private String pais;
        private String ciudad;
        private String telefono;
        private boolean trabaja;
        private String lugarTrabajo;

        public Estudiante() {}

        public Estudiante(String tipoIdentificacion, String identificacion, String nombre, String apellidos, String direccion,
                          String estadoCivil, int cargasFamiliares, String pais, String ciudad, String telefono, boolean trabaja, String lugarTrabajo) {
            this.tipoIdentificacion = tipoIdentificacion;
            this.identificacion = identificacion;
            this.nombres = nombre;
            this.apellidos = apellidos;
            this.direccion = direccion;
            this.estadoCivil = estadoCivil;
            this.cargasFamiliares = cargasFamiliares;
            this.pais = pais;
            this.ciudad = ciudad;
            this.telefono = telefono;
            this.trabaja = trabaja;
            this.lugarTrabajo = lugarTrabajo;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getLugarTrabajo() {
        return lugarTrabajo;
    }

    public void setLugarTrabajo(String lugarTrabajo) {
        this.lugarTrabajo = lugarTrabajo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public int getCargasFamiliares() {
        return cargasFamiliares;
    }

    public void setCargasFamiliares(int cargasFamiliares) {
        this.cargasFamiliares = cargasFamiliares;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isTrabaja() {
        return trabaja;
    }

    public void setTrabaja(boolean trabaja) {
        this.trabaja = trabaja;
    }

}
