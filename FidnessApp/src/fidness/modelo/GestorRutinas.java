package fidness.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Administra las rutinas de entrenamiento de Fidness.
 */
public class GestorRutinas {

    private List<Rutina> rutinas;
    private int siguienteIdRutina;

    // Constructor
    public GestorRutinas() {
        this.rutinas = new ArrayList<>();
        this.siguienteIdRutina = 1;
    }

    // Crea una nueva rutina
    public Rutina crearRutina(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return null;
        }

        return new Rutina(siguienteIdRutina++, nombre);
    }

    // Guarda una rutina en la lista
    public void guardarRutina(Rutina rutina) {
        if (rutina != null && buscarRutina(rutina.getIdRutina()) == null) {
            rutinas.add(rutina);
        }
    }

    // Busca una rutina por su identificador
    public Rutina buscarRutina(int idRutina) {
        for (Rutina rutina : rutinas) {
            if (rutina.getIdRutina() == idRutina) {
                return rutina;
            }
        }

        return null;
    }

    // Elimina una rutina por su identificador
    public boolean eliminarRutina(int idRutina) {
        Rutina rutina = buscarRutina(idRutina);

        if (rutina != null) {
            rutinas.remove(rutina);
            return true;
        }

        return false;
    }

    // Devuelve la lista de rutinas registradas
    public List<Rutina> listarRutinas() {
        return new ArrayList<>(rutinas);
    }
}