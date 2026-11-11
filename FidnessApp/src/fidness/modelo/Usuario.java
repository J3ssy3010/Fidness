package fidness.modelo;

/**
 * Representa a un usuario de la aplicación Fidness.
 */
public class Usuario {

    private int idUsuario;
    private String nombreUsuario;
    private String contrasena;

    public Usuario(int idUsuario, String nombreUsuario, String contrasena) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public boolean iniciarSesion(String usuario, String clave) {
        return nombreUsuario.equals(usuario)
                && contrasena.equals(clave);
    }

    public void cerrarSesion() {
        System.out.println("Sesion cerrada correctamente.");
    }
}