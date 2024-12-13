import java.sql.*;

public class BaseDatos {
    private static final String URL = "jdbc:mysql://localhost:3306/estacionamiento";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Cambia si tienes una contraseña configurada

    // Método para conectar a la base de datos
    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Método para actualizar el estado de un espacio
    public void actualizarEstadoEspacio(String idEspacio, boolean ocupado) {
        String query = "UPDATE Espacios SET Estado = ? WHERE ID = ?";
        try (Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setBoolean(1, ocupado);
            pst.setString(2, idEspacio);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para registrar una moto en la base de datos
    public void registrarMoto(String placa, String idEspacio, Timestamp entrada) {
        String query = "INSERT INTO Motos (Placa, Espacio, TiempoEntrada) VALUES (?, ?, ?)";
        try (Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, placa);
            pst.setString(2, idEspacio);
            pst.setTimestamp(3, entrada);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener espacios según una consulta específica
    public ResultSet obtenerEspacios(String query) {
        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(query);
            return pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método anterior para obtener todos los espacios (sin cambiar)
    public ResultSet obtenerEspacios() {
        return obtenerEspacios("SELECT * FROM Espacios");
    }

    // Método para registrar un evento en el historial
    public void registrarHistorial(String placa, String idEspacio, String evento) {
        String query = "INSERT INTO Historial (Placa, Espacio, Evento) VALUES (?, ?, ?)";
        try (Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, placa);       // Puede ser NULL si no hay una placa
            pst.setString(2, idEspacio);  // ID del espacio involucrado
            pst.setString(3, evento);     // Evento (Ocupado, Liberado, etc.)
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para generar un reporte del estado de los espacios
    public void generarReporte() {
        String query = "SELECT * FROM Espacios";
        try (Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            System.out.println("Estado de los espacios:");
            while (rs.next()) {
                String id = rs.getString("ID");
                boolean ocupado = rs.getBoolean("Estado");
                System.out.println("Espacio " + id + ": " + (ocupado ? "Ocupado" : "Libre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para limpiar una tabla específica
    public void limpiarTabla(String tabla) {
        String query = "TRUNCATE TABLE " + tabla;
        try (Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.executeUpdate();
            System.out.println("Tabla " + tabla + " limpiada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para reiniciar el contador de AUTO_INCREMENT
    public void reiniciarAutoIncrement(String tabla) {
        String query = "ALTER TABLE " + tabla + " AUTO_INCREMENT = 1";
        try (Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.executeUpdate();
            System.out.println("AUTO_INCREMENT reiniciado para la tabla: " + tabla);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
