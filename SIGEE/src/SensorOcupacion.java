public class SensorOcupacion implements Sensor {
    @Override
    public void detectarOcupacion() {
        System.out.println("El sensor ha detectado que el espacio está ocupado.");
        System.out.println("################ Sensor activado #################");
        System.out.println("############## Color Rojo: OCUPADO ##############");
    }

    @Override
    public void detectarLibreracion() {
        System.out.println("El sensor ha detectado que el espacio está libre.");
        System.out.println("################ Sensor activado #################");
        System.out.println("############## Color Verde: Libre #################");
    }
}