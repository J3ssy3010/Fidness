package fidness.modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

/**
 * Administra el almacenamiento permanente de las rutinas de Fidness.
 */
public class AlmacenamientoRutinas {

    private static final String RUTA_ARCHIVO = "datos/rutinas.dat";

    /**
     * Guarda el gestor de rutinas en un archivo.
     */
    public static boolean guardar(GestorRutinas gestor) {

        if (gestor == null) {
            return false;
        }

        File archivo = new File(RUTA_ARCHIVO);
        File carpeta = archivo.getParentFile();

        try {

            if (carpeta != null && !carpeta.exists()) {
                carpeta.mkdirs();
            }

            try (ObjectOutputStream salida = new ObjectOutputStream(
                    new FileOutputStream(archivo))) {

                salida.writeObject(gestor);
                salida.flush();
            }

            return true;

        } catch (IOException e) {

            System.err.println("Error al guardar las rutinas: "
                    + e.getMessage());

            return false;
        }
    }

    /**
     * Recupera el gestor de rutinas almacenado.
     */
    public static GestorRutinas cargar() {

        File archivo = new File(RUTA_ARCHIVO);

        if (!archivo.exists()) {
            return null;
        }

        try (ObjectInputStream entrada = new ObjectInputStream(
                new FileInputStream(archivo))) {

            Object objeto = entrada.readObject();

            if (objeto instanceof GestorRutinas) {
                return (GestorRutinas) objeto;
            }

        } catch (IOException | ClassNotFoundException e) {

            System.err.println("Error al recuperar las rutinas: "
                    + e.getMessage());
        }

        return null;
    }
}