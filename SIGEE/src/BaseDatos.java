import java.sql.*;

public class BaseDatos {
    private static final String URL = "jdbc:mysql://localhost:3306/estacionamiento"; // URL de la base de datos
    private static final String USER = "root"; // Usuario
    private static final String PASSWORD = ""; // Contraseña

    //Método para conectar a la base de datos
    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    //Método para actualizar el estado de un espacio en la base de datos
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

    //Método para registrar una moto en la base de datos
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

    //Método para obtener espacios según una consulta
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

    //Método para registrar un evento en el historial
    public void registrarHistorial(String placa, String idEspacio, String evento) {
        String query = "INSERT INTO Historial (Placa, Espacio, Evento) VALUES (?, ?, ?)";
        try (Connection con = conectar();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, placa);
            pst.setString(2, idEspacio);
            pst.setString(3, evento);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
