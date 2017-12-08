package es.ubu.proyecto.GUI;


import java.util.Optional;
import es.ubu.proyecto.management.ListManager;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ListbuttonsListener implements EventHandler<MouseEvent> {
	
	private Button boton;
	private ListManager  manager;
	private static Stage stage;
	
	public ListbuttonsListener(Button b, ListManager l, Stage s) {
		boton=b;
		manager=l;
		stage=s;	
	}

	
	@Override
	public void handle(MouseEvent event) {
		String id=boton.getId();
		if(id.equals("buttonBorrar")) {
			this.borrarLinea();
		}else if(id.equals("buttonAnadir")){
			this.anadirLinea();
		}else if(id.equals("buttonModificarComprado")) {
			this.modificarComprado();
		}else if(id.equals("buttonModificarCantidad")) {
			this.modificarCantidad();
		}
		
	}
	
	/**
	 * Método que borra una línea si los datos son correctos.
	 */
	private void borrarLinea() {
		TextField input = (TextField) stage.getScene().lookup("#inputBorrarLinea");
		if(input.getText().isEmpty() ) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("¡No has introducido nada!");
			alert.showAndWait();
			return;
		}
		int linea = getInteger(input.getText());
		if(!checkListSize(linea)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("El número introducido no está en la lista.");
			alert.showAndWait();
			return;
		}
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmación");
		alert.setHeaderText("¿Seguro que quieres eliminar la linea?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			manager.eliminarLinea(linea);
			input.setText("");
			Label lista = (Label)stage.getScene().lookup("#listaLabel");
			lista.setText(manager.imprimirLista());
			return;
		} else {
			input.setText("");
		    return;
		}
	}
	
	/**
	 * Método que añade una línea si los datos son correctos.
	 */
	private void anadirLinea() {
		TextField producto = (TextField) stage.getScene().lookup("#inputProductoAnadirLinea");
		TextField c = (TextField) stage.getScene().lookup("#inputCantidadAnadirLinea");
		if (producto.getText().isEmpty() || c.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("¡Has dejado algún campo vacío!");
			alert.showAndWait();
			return;
		}
		int cantidad = getInteger(c.getText());
		if(cantidad<1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("¡No puedes añadir una cantidad menor que uno!");
			alert.showAndWait();
			return;
		}
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmación");
		alert.setHeaderText("¿Seguro que quieres añadir " + cantidad + " unidades de " + producto.getText() + " a la lista?" );
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			manager.anadirLinea(producto.getText(), cantidad);
			producto.setText("");
			c.setText("");
			Label lista = (Label)stage.getScene().lookup("#listaLabel");
			lista.setText(manager.imprimirLista());
			return;
		} else {
			producto.setText("");
			c.setText("");
		    return;
		}
	}
	
	private void modificarComprado() {
		TextField input = (TextField) stage.getScene().lookup("#inputModificarComprado");
		if(input.getText().isEmpty() ) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("¡No has introducido nada!");
			alert.showAndWait();
			return;
		}
		int linea = getInteger(input.getText());
		if(!checkListSize(linea)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("El número introducido no está en la lista.");
			alert.showAndWait();
			return;
		}
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmación");
		alert.setHeaderText("¿Seguro que quieres marcar como comprada la línea?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			manager.marcarLineaComprada(linea);
			input.setText("");
			Label lista = (Label)stage.getScene().lookup("#listaLabel");
			lista.setText(manager.imprimirLista());
			return;
		} else {
			input.setText("");
		    return;
		}
	}
	
	private void modificarCantidad() {
		TextField l = (TextField) stage.getScene().lookup("#inputLineaModificarCantidad");
		TextField c = (TextField) stage.getScene().lookup("#inputCantidadModificarCantidad");
		if (l.getText().isEmpty() || c.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("¡Has dejado algún campo vacío!");
			alert.showAndWait();
			return;
		}
		int linea = getInteger(l.getText());
		int cantidad = getInteger(c.getText());
		if(cantidad<1 || !checkListSize(linea)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Comprueba los datos, es posible que la linea no exista o que la cantidad sea menor que 1");
			alert.showAndWait();
			return;
		}
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmación");
		alert.setHeaderText("¿Seguro que quieres modificar " + cantidad + " unidades de la linea" + linea + "?" );
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			manager.cambiarCantidad(linea, cantidad);
			l.setText("");
			c.setText("");
			Label lista = (Label)stage.getScene().lookup("#listaLabel");
			lista.setText(manager.imprimirLista());
			return;
		} else {
			l.setText("");
			c.setText("");
		    return;
		}
	}
	
	/**
	 * Método que calcula si un número pasado es un elemento de la lista o no.
	 * @param indice, el numero a chequear.
	 * @return True si es válido, false si no. 
	 */
	private boolean checkListSize(int indice) {
		if(indice>0 && indice<(manager.getListaCompra().size())) {
			return true;
		}else {
			return false;
		}
	}

	private static int getInteger(String s) {
		int res;
		try {
			res = Integer.parseInt(s);
		}catch(NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Has introducido letras donde eran números.");
			alert.showAndWait();	
			res=-1;
		}
		
		return res;
	}

}
