package fidness.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una rutina de entrenamiento del usuario.
 */
public class Rutina implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idRutina;
    private String nombre;
    private List<Ejercicio> ejercicios;

    public Rutina(int idRutina, String nombre) {
        this.idRutina = idRutina;
        this.nombre = nombre;
        this.ejercicios = new ArrayList<>();
    }

    public int getIdRutina() {
        return idRutina;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void agregarEjercicio(Ejercicio ejercicio) {
        if (ejercicio != null) {
            ejercicios.add(ejercicio);
        }
    }

    public void eliminarEjercicio(Ejercicio ejercicio) {
        ejercicios.remove(ejercicio);
    }

    public String mostrarRutina() {
        StringBuilder resultado = new StringBuilder();

        resultado.append("Rutina: ").append(nombre).append("\n");
        resultado.append("Ejercicios:\n");

        if (ejercicios.isEmpty()) {
            resultado.append("No hay ejercicios agregados.");
        } else {
            for (Ejercicio ejercicio : ejercicios) {
                resultado.append("- ")
                        .append(ejercicio.getNombre())
                        .append("\n");
            }
        }

        return resultado.toString();
    }

    public String exportarRutina() {
        return mostrarRutina();
    }
}