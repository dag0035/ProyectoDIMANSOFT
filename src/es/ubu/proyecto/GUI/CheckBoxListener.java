package es.ubu.proyecto.GUI;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;

public class CheckBoxListener implements ChangeListener<Boolean> {

	private List<Button> botones=null;

	
	public CheckBoxListener(List<Button> b) {
		botones=b;
	}
	
	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		for(Button b: botones) {
			b.setDisable(newValue);
		}
		
	}

}
