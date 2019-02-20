package logica;

import datos.Modelo;
import pojos.Arma;
import pojos.Movimiento;
import pojos.Personaje;
import ui.PersonajesUI;

import java.util.ArrayList;
import java.util.List;

public class ControladorPersonajes extends ControladorCRUD<Personaje> {

    private Modelo modelo;
    public PersonajesUI vista;
    private Controlador controlador;

    public ControladorPersonajes(Modelo modelo, PersonajesUI vista, Controlador controlador) {
        super(modelo.modeloPersonajes, vista);
        this.modelo = modelo;
        this.vista = vista;
        this.controlador = controlador;
    }

    public void cambioEnMovimientos(List<Movimiento> movimientos) {
        // Cargar combobox de movimientos
        vista.movimientoComboBox.removeAllItems();
        movimientos.forEach(vista.movimientoComboBox::addItem);

        // Refrescar la lista
        cargarLista(modeloCRUD.cogerTodo());
    }

    public void cambioEnArmas(List<Arma> listaDatos) {
        vista.armasMultiCombo.setComboOptions(listaDatos);
        cargarLista(modeloCRUD.cogerTodo());
    }

    @Override
    public void cargarDatos() {
        vista.nombreTextField.setText(datoPantalla.getNombre());
        List<Movimiento> movimientos = modelo.buscarMovimientosDePersonaje(datoPantalla);
        vista.movimientoComboBox.setSelectedItem(movimientos.size() > 0 ?
                movimientos.get(0) : null);
        vista.vidaTextField.setText(String.valueOf(datoPantalla.getVida()));
        vista.armasMultiCombo.setListItems(datoPantalla.getArmas());
    }

    @Override
    public Personaje extraerDatos(Personaje personaje) {
        String textoNombre = vista.nombreTextField.getText();
        String textoVida = vista.vidaTextField.getText();

        personaje.setNombre(!textoNombre.isEmpty() ? textoNombre : "Sin nombre");
        personaje.setVida(!textoVida.isEmpty() ? Integer.parseInt(textoVida) : 0);
        personaje.setArmas(vista.armasMultiCombo.getListItems());

        return personaje;
    }

    @Override
    public void limpiarPantalla() {
        vista.nombreTextField.setText("");
        vista.movimientoComboBox.setSelectedItem(null);
        vista.armasMultiCombo.setListItems(new ArrayList<>());
        vista.vidaTextField.setText("0");
    }

    @Override
    public void datosCambiados() {
        List<Personaje> personajes = modeloCRUD.cogerTodo();
        controlador.controladorArmas.cambioEnPersonajes(personajes);
        controlador.controladorMovimientos.cambioEnPersonajes(personajes);
    }

    @Override
    public Personaje nuevoDatoVacio() {
        return new Personaje();
    }
}
