package es.ubu.proyecto.UI.GUI;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
/**
 * Clase observador para el listener de las checkbox.
 * @author Marcos Orive Izarra.
 *
 */
public class CheckBoxListener implements ChangeListener<Boolean> {
	/**
	 * Atributo con la lista botones.
	 */
	private List<Button> botones=null;
	/**
	 * Constructor de la clase
	 * @param b, la lista de botones.
	 */
	public CheckBoxListener(List<Button> b) {
		botones=b;
	}
	/**
	 * Método que se ejecuta cuando cambia el valor de la check box. 
	 * En este caso habilita o deshabilita los botones.
	 */
	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		for(Button b: botones) {
			b.setDisable(newValue);
		}
		
	}

}
