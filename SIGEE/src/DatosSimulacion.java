import java.util.ArrayList;
import java.util.List;

public class DatosSimulacion {
    private final List<Estacionamiento> espacios;
    private final ModuloAdministracion admin;

    public DatosSimulacion(int totalEspacios) {
        espacios = new ArrayList<>();
        for (int i = 1; i <= totalEspacios; i++) {
            espacios.add(new EspacioMoto("M" + i));
        }
        admin = new ModuloAdministracion(totalEspacios);
    }

    public List<Estacionamiento> getEspacios() {
        return espacios;
    }

    public ModuloAdministracion getAdmin() {
        return admin;
    }

    public void limpiarDatos() {
        for (Estacionamiento espacio : espacios) {
            if (espacio.isOcupado()) {
                espacio.liberarEspacio();
            }
        }
        admin.resetearEspacios();
    }
}
