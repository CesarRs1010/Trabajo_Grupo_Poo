import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Cesar: Modificada para cargar los espacios desde la base de datos.
public class DatosSimulacion {
    private final List<Estacionamiento> espacios = new ArrayList<>();
    private final ModuloAdministracion admin;
    private final BaseDatos baseDatos = new BaseDatos(); // Cesar: Nueva dependencia

    public DatosSimulacion() {
        cargarEspaciosDesdeBD();
        admin = new ModuloAdministracion(espacios.size());
    }

    // Cesar: Método actualizado para cargar espacios desde la base de datos
    private void cargarEspaciosDesdeBD() {
        String query = "SELECT * FROM Espacios";
        try (ResultSet rs = baseDatos.obtenerEspacios(query)) {
            while (rs.next()) {
                String id = rs.getString("ID");
                boolean ocupado = rs.getBoolean("Estado");
                EspacioMoto espacio = new EspacioMoto(id);
                if (ocupado) {
                    espacio.ocuparEspacio();
                }
                espacios.add(espacio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al cargar espacios desde la base de datos.");
        }
    }

    // Devuelve la lista de espacios en memoria
    public List<Estacionamiento> getEspacios() {
        return espacios;
    }

    // Devuelve el administrador en memoria
    public ModuloAdministracion getAdmin() {
        return admin;
    }

    // Cesar: Actualiza tanto en memoria como en la base de datos
    public void actualizarEstadoEspacio(String idEspacio, boolean ocupado) {
        for (Estacionamiento espacio : espacios) {
            if (espacio.getIdEspacio().equals(idEspacio)) {
                if (ocupado) {
                    espacio.ocuparEspacio();
                } else {
                    espacio.liberarEspacio();
                }
                break;
            }
        }
        baseDatos.actualizarEstadoEspacio(idEspacio, ocupado);
    }
}
