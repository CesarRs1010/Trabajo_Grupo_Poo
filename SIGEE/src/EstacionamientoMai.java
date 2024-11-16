public class EstacionamientoMai {
    public static void main(String[] args) throws Exception {
        SensorOcupacion sensor = new SensorOcupacion();
        AplicacionMovil app = new AplicacionMovil();
        EspacioMoto espacio1 = new EspacioMoto("M1");

        // Probar la asignacion de espacio
        sensor.detectarOcupacion();
        app.asignarEspacio(espacio1);

        //Parte para la liberacion de espacio
        sensor.detectarLibreracion();
        espacio1.liberarEspacio();

    }
}
