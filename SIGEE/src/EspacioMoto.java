public class EspacioMoto extends Estacionamiento {
    public EspacioMoto(String idEspacio) {
        super(idEspacio);
    }

    @Override
    public void ocuparEspacio() {
        if (!ocupado) {
            ocupado = true;
            System.out.println("Espacio " + getIdEspacio() + " ha sido ocupado por una moto.");
        } else {
            System.out.println("Espacio " + getIdEspacio() + " ya está ocupado.");
        }
    }

    @Override
    public void liberarEspacio() {
        if (ocupado) {
            ocupado = false;
            System.out.println("Espacio " + getIdEspacio() + " ha sido liberado.");
        } else {
            System.out.println("Espacio " + getIdEspacio() + " ya está libre.");
        }
    }
}
