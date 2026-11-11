package fidness.modelo;

/**
 * Representa los ejercicios relacionados con las piernas.
 */
public class EjercicioPierna extends Ejercicio {

    // Constructor
    public EjercicioPierna(int idEjercicio, String nombre, String descripcion,
            String instrucciones) {

        super(idEjercicio, nombre, descripcion, "Piernas", instrucciones);
    }

    // Muestra la información específica del ejercicio de pierna
    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion();
    }
}