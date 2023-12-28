package com.kevin.proyecto_ma;

public class Persona {
    String cedula,nombre,apellido,estado;

    public Persona(String cedula, String nombre, String apellido, String estado) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.estado = estado;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
