import java.util.ArrayList;
import java.util.List;

public class DatosSimulacion {
    private final List<Estacionamiento> espacios; // Lista de espacios de estacionamiento
    private final ModuloAdministracion admin; // Módulo de administración para manejar los espacios ocupados

    public DatosSimulacion(int totalEspacios) {
        espacios = new ArrayList<>();
        for (int i = 1; i <= totalEspacios; i++) {
            espacios.add(new EspacioMoto("M" + i));
        }
        admin = new ModuloAdministracion(totalEspacios);
    }

    // Método para obtener la lista de espacios
    public List<Estacionamiento> getEspacios() {
        return espacios;
    }

    // Método para obtener el administrador de los espacios
    public ModuloAdministracion getAdmin() {
        return admin;
    }

    // Método para limpiar los datos de la simulación (resetear)
    public void limpiarDatos() {
        for (Estacionamiento espacio : espacios) {
            if (espacio.isOcupado()) {
                espacio.liberarEspacio(); // Liberar todos los espacios ocupados
            }
        }
        admin.resetearEspacios(); // Reiniciar el módulo de administración a su estado inicial
    }
}
