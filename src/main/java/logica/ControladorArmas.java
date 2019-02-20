package logica;

import datos.Modelo;
import pojos.Arma;
import pojos.Personaje;
import ui.ArmasUI;

import java.util.ArrayList;
import java.util.List;

public class ControladorArmas extends ControladorCRUD<Arma> {

    private Modelo modelo;
    public ArmasUI vista;
    private Controlador controlador;

    public ControladorArmas(Modelo modelo, ArmasUI vista, Controlador controlador) {
        super(modelo.modeloArmas, vista);
        this.modelo = modelo;
        this.vista = vista;
        this.controlador = controlador;
    }

    @Override
    public Arma nuevoDatoVacio() {
        return new Arma();
    }


    @Override
    public void cargarDatos() {
        vista.nombreTextField.setText(datoPantalla.getNombre());
        vista.ataqueTextField.setText(String.valueOf(datoPantalla.getAtaque()));
        vista.rarezaComboBox.setSelectedItem(datoPantalla.getRareza());
        vista.personajesMultiCombo.setListItems(datoPantalla.getPersonajes());
    }

    public void cambioEnPersonajes(List<Personaje> personajes) {
        vista.personajesMultiCombo.setComboOptions(personajes);
        cargarLista(modeloCRUD.cogerTodo());
    }

    @Override
    public void datosCambiados() {
        controlador.controladorPersonajes.cambioEnArmas(modeloCRUD.cogerTodo());
    }

    @Override
    public Arma extraerDatos(Arma arma) {
        String textoNombre = vista.nombreTextField.getText();
        String textoAtaque = vista.ataqueTextField.getText();

        arma.setNombre(!textoNombre.isEmpty() ? textoNombre : "Sin nombre");
        arma.setAtaque(!textoAtaque.isEmpty() ? Integer.parseInt(textoAtaque) : 0);
        arma.setRareza((Arma.Rareza) vista.rarezaComboBox.getSelectedItem());
        propagarCambios(arma);

        return arma;
    }

    private void propagarCambios(Arma arma) {
        ArrayList<Personaje> antiguos = new ArrayList<>(arma.getPersonajes());
        ArrayList<Personaje> nuevos = (ArrayList<Personaje>) vista.personajesMultiCombo.getListItems();

        ArrayList<Personaje> paraBorrar = new ArrayList<>(antiguos);
        paraBorrar.removeAll(nuevos);
        for (Personaje personaje : paraBorrar) {
            personaje.getArmas().remove(arma);
            modelo.modeloPersonajes.guardar(personaje);
        }

        ArrayList<Personaje> paraGuardar = new ArrayList<>(nuevos);
        paraGuardar.removeAll(antiguos);
        for (Personaje personaje : paraGuardar) {
            personaje.getArmas().add(arma);
            modelo.modeloPersonajes.guardar(personaje);
        }

        arma.setPersonajes(nuevos);
    }

    @Override
    public void limpiarPantalla() {
        vista.nombreTextField.setText("");
        vista.ataqueTextField.setText("0");
        vista.rarezaComboBox.setSelectedIndex(0);
        vista.personajesMultiCombo.setListItems(new ArrayList<>());
    }
}
