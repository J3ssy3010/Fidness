package fidness.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Administra las rutinas de entrenamiento de Fidness.
 */
public class GestorRutinas implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Rutina> rutinas;
    private int siguienteIdRutina;

    /**
     * Constructor.
     * Recupera las rutinas almacenadas anteriormente.
     */
    public GestorRutinas() {

        GestorRutinas gestorGuardado = AlmacenamientoRutinas.cargar();

        if (gestorGuardado != null) {

            this.rutinas = gestorGuardado.rutinas;
            this.siguienteIdRutina = gestorGuardado.siguienteIdRutina;

        } else {

            this.rutinas = new ArrayList<>();
            this.siguienteIdRutina = 1;
        }
    }

    /**
     * Crea una nueva rutina.
     */
    public Rutina crearRutina(String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            return null;
        }

        return new Rutina(siguienteIdRutina++, nombre.trim());
    }

    /**
     * Guarda una rutina y actualiza el archivo.
     */
    public void guardarRutina(Rutina rutina) {

        if (rutina != null && buscarRutina(rutina.getIdRutina()) == null) {

            rutinas.add(rutina);

            AlmacenamientoRutinas.guardar(this);
        }
    }

    /**
     * Busca una rutina por su identificador.
     */
    public Rutina buscarRutina(int idRutina) {

        for (Rutina rutina : rutinas) {

            if (rutina.getIdRutina() == idRutina) {
                return rutina;
            }
        }

        return null;
    }

    /**
     * Elimina una rutina y actualiza el archivo.
     */
    public boolean eliminarRutina(int idRutina) {

        Rutina rutina = buscarRutina(idRutina);

        if (rutina != null) {

            rutinas.remove(rutina);

            AlmacenamientoRutinas.guardar(this);

            return true;
        }

        return false;
    }

    /**
     * Devuelve las rutinas registradas.
     */
    public List<Rutina> listarRutinas() {

        return new ArrayList<>(rutinas);
    }
}