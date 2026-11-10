package fidness.modelo;

/**
 * Representa los ejercicios relacionados con los brazos.
 */
public class EjercicioBrazo extends Ejercicio {

    // Constructor
    public EjercicioBrazo(int idEjercicio, String nombre, String descripcion,
            String instrucciones) {

        super(idEjercicio, nombre, descripcion, "Brazos", instrucciones);
    }

    // Muestra la información específica del ejercicio de brazo
    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion();
    }
}