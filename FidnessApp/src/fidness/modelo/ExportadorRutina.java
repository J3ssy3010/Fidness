package fidness.modelo;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Permite exportar las rutinas de entrenamiento.
 */
public class ExportadorRutina {

    // Genera el contenido de la rutina
    public String generarArchivo(Rutina rutina) {
        if (rutina == null) {
            return "";
        }

        return rutina.exportarRutina();
    }

    // Exporta la rutina a un archivo de texto
    public boolean exportar(Rutina rutina, String nombreArchivo) {

        if (rutina == null || nombreArchivo == null
                || nombreArchivo.trim().isEmpty()) {
            return false;
        }

        try (FileWriter escritor = new FileWriter(nombreArchivo)) {

            escritor.write(generarArchivo(rutina));
            return true;

        } catch (IOException e) {
            System.out.println("Error al exportar la rutina: "
                    + e.getMessage());
            return false;
        }
    }
}