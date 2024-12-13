import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModuloAdministracion {
    private static final Logger LOGGER = Logger.getLogger(ModuloAdministracion.class.getName()); // Logger para la clase

    private final int espaciosTotales;
    private int espaciosOcupados;
    private final BaseDatos baseDatos = new BaseDatos(); // Instancia para manejar la base de datos

    public ModuloAdministracion(int espaciosTotales) {
        this.espaciosTotales = espaciosTotales;
        this.espaciosOcupados = 0;
    }

    // Incrementa el contador de espacios ocupados
    public void incrementarEspaciosOcupados() {
        if (espaciosOcupados < espaciosTotales) {
            espaciosOcupados++;
        }
    }

    // Decrementa el contador de espacios ocupados
    public void decrementarEspaciosOcupados() {
        if (espaciosOcupados > 0) {
            espaciosOcupados--;
        }
    }

    // Genera un reporte del estado actual de los espacios de estacionamiento
    public void generarReporte() {
        // Consulta para obtener los espacios ordenados numéricamente por ID
        String query = "SELECT * FROM Espacios ORDER BY CAST(SUBSTRING(ID, 2) AS UNSIGNED)";
        try (ResultSet espacios = baseDatos.obtenerEspacios(query)) {
            System.out.println("Estado de los espacios de estacionamiento:");
            while (espacios.next()) {
                String id = espacios.getString("ID");
                boolean ocupado = espacios.getBoolean("Estado");
                System.out.println("Espacio " + id + ": " + (ocupado ? "Ocupado" : "Libre"));
            }
        } catch (SQLException e) {
            // Manejo de errores usando Logger en lugar de printStackTrace
            LOGGER.log(Level.SEVERE, "Error al generar el reporte de los espacios.", e);
        }
    }

    // Devuelve el número de espacios libres
    public int getEspaciosLibres() {
        return espaciosTotales - espaciosOcupados;
    }

    // Resetea el estado del administrador
    public void resetearEspacios() {
        this.espaciosOcupados = 0; // Reiniciar la cantidad de espacios ocupados
    }
}
