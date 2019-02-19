package datos;

import pojos.Arma;
import pojos.Movimiento;
import pojos.Personaje;

public class Modelo {

    public ModeloCRUD<Movimiento> modeloMovimientos;
    public ModeloCRUD<Personaje> modeloPersonajes;
    public ModeloCRUD<Arma> modeloArmas;

    public Modelo() {
        modeloArmas = new ModeloCRUD<>(Arma.class);
        modeloPersonajes = new ModeloCRUD<>(Personaje.class);
        modeloMovimientos = new ModeloCRUD<>(Movimiento.class);
    }

    public Object buscarPersonajePorIdMovimiento(long id) {
        return null;
    }
}
