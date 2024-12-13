import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatosSimulacion {
    private final List<Estacionamiento> espacios = new ArrayList<>();
    private final ModuloAdministracion admin;
    private final BaseDatos baseDatos = new BaseDatos();

    public DatosSimulacion() {
        cargarEspaciosDesdeBD();
        admin = new ModuloAdministracion(espacios.size());
    }

    // Cargar los espacios desde la base de datos a la memoria
    private void cargarEspaciosDesdeBD() {
        // Consulta actualizada para ordenar los espacios
        String query = "SELECT * FROM Espacios ORDER BY CAST(SUBSTRING(ID, 2) AS UNSIGNED)";
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

    // Devuelve la lista en memoria de los espacios
    public List<Estacionamiento> getEspacios() {
        return espacios;
    }

    // Devuelve el administrador en memoria
    public ModuloAdministracion getAdmin() {
        return admin;
    }

    // Actualiza tanto en memoria como en la base de datos
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

    // Limpia todos los datos en memoria y sincroniza con la base de datos
    public void limpiarDatos() {
        // Liberar espacios en memoria y base de datos
        for (Estacionamiento espacio : espacios) {
            if (espacio.isOcupado()) {
                espacio.liberarEspacio();
                baseDatos.actualizarEstadoEspacio(espacio.getIdEspacio(), false);
            }
        }

        // Resetear el módulo de administración
        admin.resetearEspacios();

        // Limpia la tabla Historial en la base de datos
        baseDatos.limpiarTabla("Historial");

        // Reinicia el contador AUTO_INCREMENT para la tabla Historial
        baseDatos.reiniciarAutoIncrement("Historial");

        System.out.println("Datos de la simulación limpiados correctamente.");
    }
}