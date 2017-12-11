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
	private Stage ventana;

	
	public ListbuttonsListener(Button b, ListManager l, Stage stage) {
		boton=b;
		manager=l;
		ventana=stage;

	}

	
	@Override
	public void handle(MouseEvent event) {
		String id=boton.getId();
			switch(id) {
	
			case "buttonBorrar":
				this.borrarLinea();
				break;
			case "buttonAnadir":
				this.anadirLinea();
				break;
			case "buttonModificarComprado":
				this.modificarComprado();
				break;
			case "buttonModificarCantidad":
				this.modificarCantidad();
				break;
			case "buttonAnadirFavALista":
				this.anadirFavALista();
				break;
			case "buttonAnadirFav":
				
				break;
				
			case "buttonBorrarFav":
				break;
				
			default:
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Ha habido un error, contacta con el Administador.");
					alert.showAndWait();
					return;
				
		}		
	}


	/**
	 * Método que borra una línea si los datos son correctos.
	 */
	private void borrarLinea() {
		TextField input = (TextField) ventana.getScene().lookup("#inputBorrarLinea");
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
			Label lista = (Label)ventana.getScene().lookup("#listaLabel");
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
		TextField producto = (TextField) ventana.getScene().lookup("#inputProductoAnadirLinea");
		TextField c = (TextField) ventana.getScene().lookup("#inputCantidadAnadirLinea");
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
			manager.anadirLinea(producto.getText(), cantidad,false);
			producto.setText("");
			c.setText("");
			Label lista = (Label)ventana.getScene().lookup("#listaLabel");
			lista.setText(manager.imprimirLista());
			return;
		} else {
			producto.setText("");
			c.setText("");
		    return;
		}
	}
	
	private void modificarComprado() {
		TextField input = (TextField) ventana.getScene().lookup("#inputModificarComprado");
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
			Label lista = (Label)ventana.getScene().lookup("#listaLabel");
			lista.setText(manager.imprimirLista());
			return;
		} else {
			input.setText("");
		    return;
		}
	}
	
	private void modificarCantidad() {
		TextField l = (TextField) ventana.getScene().lookup("#inputLineaModificarCantidad");
		TextField c = (TextField) ventana.getScene().lookup("#inputCantidadModificarCantidad");
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
			Label lista = (Label)ventana.getScene().lookup("#listaLabel");
			lista.setText(manager.imprimirLista());
			return;
		} else {
			l.setText("");
			c.setText("");
		    return;
		}
	}
	
	private void anadirFavALista() {
		TextField l = (TextField) ventana.getScene().lookup("#inputFavAnadirFavALista");
		TextField c = (TextField) ventana.getScene().lookup("#inputCantidadAnadirFavALista");
		if (l.getText().isEmpty() || c.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("¡Has dejado algún campo vacío!");
			alert.showAndWait();
			return;
		}
		int linea = getInteger(l.getText());
		int cantidad = getInteger(c.getText());
		if(cantidad<1 || !checkFavSize(linea)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Comprueba los datos, es posible que la linea no exista o que la cantidad sea menor que 1");
			alert.showAndWait();
			return;
		}
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmación");
		alert.setHeaderText("¿Seguro que quieres añadirlo a la lista?" );
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			manager.anadirProducto(manager.getFavorito(linea), cantidad);
			l.setText("");
			c.setText("");
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
		if(indice>=0 && indice<(manager.getListaCompra().size())) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Método que calcula si un indice está dentro de los parametros de la lista.
	 * @param indice, el indice que quieres comprobar.
	 * @return True si es válido, false si no.
	 */
	private boolean checkFavSize(int indice) {
		if(indice>=0 && indice<(manager.getFavoritos().size())) {
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
