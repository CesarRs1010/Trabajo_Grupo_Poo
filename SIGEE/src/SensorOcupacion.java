public class SensorOcupacion implements Sensor {
    @Override
    public void detectarOcupacion() {
        System.out.println("El sensor ha detectado que el espacio está ocupado.");
    }

    @Override
    public void detectarLibreracion() {
        System.out.println("El sensor ha detectado que el espacio está libre.");
    }
}
