package fidness.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Centraliza los ejercicios disponibles en Fidness.
 */
public class CatalogoEjercicios {

    private static final List<Ejercicio> ejercicios = new ArrayList<>();

    static {

        // Ejercicios de piernas
        ejercicios.add(new EjercicioPierna(
                1,
                "Sentadilla",
                "Ejercicio para fortalecer los músculos de las piernas y glúteos.",
                "1. Coloque los pies al ancho de los hombros.\n"
                + "2. Flexione las rodillas y baje la cadera.\n"
                + "3. Mantenga la espalda recta.\n"
                + "4. Regrese lentamente a la posición inicial."
        ));

        ejercicios.add(new EjercicioPierna(
                2,
                "Peso muerto",
                "Ejercicio que trabaja principalmente las piernas y los glúteos.",
                "1. Coloque los pies al ancho de las caderas.\n"
                + "2. Sujete la barra frente a sus piernas.\n"
                + "3. Incline la cadera manteniendo la espalda recta.\n"
                + "4. Levante el peso de manera controlada."
        ));

        ejercicios.add(new EjercicioPierna(
                3,
                "Extensión de piernas",
                "Ejercicio enfocado en fortalecer los cuádriceps.",
                "1. Siéntese correctamente en la máquina.\n"
                + "2. Coloque los tobillos detrás del soporte.\n"
                + "3. Extienda las piernas lentamente.\n"
                + "4. Regrese a la posición inicial."
        ));

        // Ejercicios de espalda
        ejercicios.add(new EjercicioEspalda(
                4,
                "Remo con barra",
                "Ejercicio que fortalece los músculos de la espalda.",
                "1. Sujete la barra con ambas manos.\n"
                + "2. Incline ligeramente el torso hacia adelante.\n"
                + "3. Lleve la barra hacia el abdomen.\n"
                + "4. Baje la barra lentamente."
        ));

        ejercicios.add(new EjercicioEspalda(
                5,
                "Jalón al pecho",
                "Ejercicio para trabajar los músculos de la espalda.",
                "1. Siéntese frente a la máquina.\n"
                + "2. Sujete la barra con ambas manos.\n"
                + "3. Lleve la barra hacia la parte superior del pecho.\n"
                + "4. Regrese lentamente a la posición inicial."
        ));

        // Ejercicios de brazos
        ejercicios.add(new EjercicioBrazo(
                6,
                "Press de banca",
                "Ejercicio que trabaja principalmente el pecho y los tríceps.",
                "1. Acuéstese en el banco.\n"
                + "2. Sujete la barra con ambas manos.\n"
                + "3. Baje la barra de manera controlada.\n"
                + "4. Empuje la barra hacia arriba."
        ));

        ejercicios.add(new EjercicioBrazo(
                7,
                "Curl de bíceps",
                "Ejercicio para fortalecer los músculos bíceps.",
                "1. Sostenga una mancuerna en cada mano.\n"
                + "2. Mantenga los codos cerca del cuerpo.\n"
                + "3. Flexione los brazos llevando las mancuernas hacia los hombros.\n"
                + "4. Baje lentamente las mancuernas."
        ));
    }

    /**
     * Devuelve la lista de ejercicios disponibles.
     */
    public static List<Ejercicio> obtenerEjercicios() {
        return new ArrayList<>(ejercicios);
    }

    /**
     * Busca un ejercicio por su identificador.
     */
    public static Ejercicio buscarPorId(int idEjercicio) {

        for (Ejercicio ejercicio : ejercicios) {

            if (ejercicio.getIdEjercicio() == idEjercicio) {
                return ejercicio;
            }
        }

        return null;
    }
}