package fidness.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una rutina de entrenamiento del usuario.
 */
public class Rutina {

    private int idRutina;
    private String nombre;
    private List<Ejercicio> ejercicios;

    // Constructor
    public Rutina(int idRutina, String nombre) {
        this.idRutina = idRutina;
        this.nombre = nombre;
        this.ejercicios = new ArrayList<>();
    }

    // Métodos para consultar los atributos
    public int getIdRutina() {
        return idRutina;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    // Modifica el nombre de la rutina
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Agrega un ejercicio a la rutina
    public void agregarEjercicio(Ejercicio ejercicio) {
        if (ejercicio != null) {
            ejercicios.add(ejercicio);
        }
    }

    // Elimina un ejercicio de la rutina
    public void eliminarEjercicio(Ejercicio ejercicio) {
        ejercicios.remove(ejercicio);
    }

    // Muestra el contenido de la rutina
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

    // Prepara el contenido de la rutina para exportarlo
    public String exportarRutina() {
        return mostrarRutina();
    }
}