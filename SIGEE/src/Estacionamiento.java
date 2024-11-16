public abstract class Estacionamiento {
    protected String idEspacio;
    protected boolean ocupado;

    public Estacionamiento(String idEspacio) {
        this.idEspacio = idEspacio;
        this.ocupado = false;
    }

    // Método abstracto que las subclases deben implementar
    public abstract void ocuparEspacio();
    public abstract void liberarEspacio();

    public boolean isOcupado() {
        return ocupado;
    }
}
