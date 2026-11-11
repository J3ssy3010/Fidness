package fidness.modelo;

/**
 * Valida los datos de las rutinas de entrenamiento.
 */
public class ValidadorRutina {

    public boolean validarNombre(String nombre) {
        return nombre != null && !nombre.trim().isEmpty();
    }

    public boolean validarEjercicios(Rutina rutina) {
        return rutina != null
                && rutina.getEjercicios() != null
                && !rutina.getEjercicios().isEmpty();
    }

    public boolean validarRutinaCompleta(Rutina rutina) {
        return validarNombre(rutina != null ? rutina.getNombre() : null)
                && validarEjercicios(rutina);
    }
}
