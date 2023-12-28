
package Modelos;

public class Activos {
    
    private String id_activo, nombre, estado;

    public Activos() {
    }

    public Activos(String idActivo, String nombre, String estado) {
        this.id_activo = idActivo;
        this.nombre = nombre;
        this.estado = estado;
    }

    public String getIdActivo() {
        return id_activo;
    }

    public void setIdActivo(String idActivo) {
        this.id_activo = idActivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
    @Override
    public String toString(){
        return "ID: " + this.id_activo +
                "\nNombre: " + this.nombre+
                "\nEstado: " + this.estado;
    }
}
