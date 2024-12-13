import java.sql.ResultSet;
import java.sql.SQLException;

public class ModuloAdministracion {
    private final int espaciosTotales;
    private int espaciosOcupados;
    private final BaseDatos baseDatos = new BaseDatos(); // Instancia para manejar la base de datos

    public ModuloAdministracion(int espaciosTotales) {
        this.espaciosTotales = espaciosTotales;
        this.espaciosOcupados = 0;
    }

    public void incrementarEspaciosOcupados() {
        if (espaciosOcupados < espaciosTotales) {
            espaciosOcupados++;
        }
    }

    public void decrementarEspaciosOcupados() {
        if (espaciosOcupados > 0) {
            espaciosOcupados--;
        }
    }

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
            e.printStackTrace();
            System.err.println("Error al generar el reporte de los espacios.");
        }
    }

    public int getEspaciosLibres() {
        return espaciosTotales - espaciosOcupados;
    }

    public void resetearEspacios() {
        this.espaciosOcupados = 0; // Reiniciar la cantidad de espacios ocupados
    }
}