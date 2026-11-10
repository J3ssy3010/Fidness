package fidness.modelo;

import java.io.Serializable;

/**
 * Representa un ejercicio de la aplicación Fidness.
 */
public class Ejercicio implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idEjercicio;
    private String nombre;
    private String descripcion;
    private String grupoMuscular;
    private String instrucciones;

    public Ejercicio(int idEjercicio, String nombre, String descripcion,
            String grupoMuscular, String instrucciones) {

        this.idEjercicio = idEjercicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.grupoMuscular = grupoMuscular;
        this.instrucciones = instrucciones;
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getGrupoMuscular() {
        return grupoMuscular;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setGrupoMuscular(String grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String mostrarInformacion() {
        return "Ejercicio: " + nombre
                + "\nDescripción: " + descripcion
                + "\nGrupo muscular: " + grupoMuscular;
    }

    public String mostrarInstrucciones() {
        return instrucciones;
    }
}