import java.sql.ResultSet;
import java.sql.SQLException;

public class ModuloAdministracion {
    private final int espaciosTotales;
    private int espaciosOcupados;
    private final BaseDatos baseDatos = new BaseDatos();

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

    // Genera un reporte del estado actual de los espacios, ordenados
    public void generarReporte() {
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

    // Devuelve el número de espacios libres
    public int getEspaciosLibres() {
        return espaciosTotales - espaciosOcupados;
    }

    // Resetea el estado del administrador
    public void resetearEspacios() {
        this.espaciosOcupados = 0;
    }
}
