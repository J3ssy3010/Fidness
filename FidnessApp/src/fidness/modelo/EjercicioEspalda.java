package fidness.modelo;

/**
 * Representa los ejercicios relacionados con la espalda.
 */
public class EjercicioEspalda extends Ejercicio {

    // Constructor
    public EjercicioEspalda(int idEjercicio, String nombre, String descripcion,
            String instrucciones) {

        super(idEjercicio, nombre, descripcion, "Espalda", instrucciones);
    }

    // Muestra la información específica del ejercicio de espalda
    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion();
    }
}