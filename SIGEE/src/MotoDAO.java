import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MotoDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/estacionamiento";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ""; // Contraseña vacía en XAMPP por defecto

    public void guardarMoto(Moto moto) {
        String sql = "INSERT INTO Motos (Placa, Espacio, TiempoEntrada) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, moto.getPlaca());
            pstmt.setString(2, moto.getEspacio());
            pstmt.setTimestamp(3, moto.getTiempoEntrada());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("¡Moto guardada exitosamente!");
            }
        } catch (SQLException e) {
            System.err.println("Error al guardar la moto: " + e.getMessage());
        }
    }

    public void actualizarEstadoEspacio(String idEspacio, boolean ocupado) {
        String sql = "UPDATE Espacios SET Estado = ? WHERE ID = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBoolean(1, ocupado);
            pstmt.setString(2, idEspacio);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar el estado del espacio: " + e.getMessage());
        }
    }

    public void registrarHistorial(String placa, String idEspacio, String evento) {
        String sql = "INSERT INTO Historial (Placa, Espacio, Evento) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, placa);       // Puede ser NULL si no hay una placa
            pstmt.setString(2, idEspacio);  // ID del espacio involucrado
            pstmt.setString(3, evento);     // Evento (Ocupado, Liberado, etc.)

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al registrar el historial: " + e.getMessage());
        }
    }
}
