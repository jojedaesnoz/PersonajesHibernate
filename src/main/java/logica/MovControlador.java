package logica;

import pojos.Movimiento;
import ui.BotonesCRUD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class MovControlador extends ControladorCRUD<Movimiento> {
    

    public MovControlador(BotonesCRUD botonesCRUD, JList<Movimiento> lista, Controlador controlador) {
        super(botonesCRUD, lista, controlador);
    }

    @Override
    void clickEnLista(MouseEvent e, JList<Movimiento> lista) {
                
    }

    @Override
    void clickEnNuevo(ActionEvent evento) {

    }

    @Override
    void clickEnGuardar(ActionEvent evento) {

    }

    @Override
    void clickEnModificar(ActionEvent evento) {

    }

    @Override
    void clickEnCancelar(ActionEvent evento) {

    }

    @Override
    void clickEnEliminar(ActionEvent evento) {

    }

    @Override
    void clickEnDeshacer(ActionEvent evento) {

    }

    @Override
    void clickEnEliminarTodo(ActionEvent evento) {

    }
}