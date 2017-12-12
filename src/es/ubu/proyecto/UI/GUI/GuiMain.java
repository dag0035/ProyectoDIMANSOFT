package es.ubu.proyecto.UI.GUI;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.geometry.*;

import java.util.ArrayList;
import java.util.List;

import es.ubu.proyecto.management.*;
import es.ubu.proyecto.model.*;
import es.ubu.proyecto.storage.*;
import es.ubu.proyecto.UI.*;


public class GuiMain extends Application implements UI{
	
	private Stage ventana = null;
	private ListManager manager =  ListManager.getInstance();
	private Storage almacenamiento = StorageFacade.getInstace();
	private List<Button> botones= new ArrayList<Button>();
	private Scene sceneLista, sceneFavoritos;
	
	private final static String cssLayout = "-fx-border-color: black;\n" +
            "-fx-border-insets: 1;\n" +
            "-fx-border-width: 1;\n" +
            "-fx-border-style: solid;\n";
	
	public void execute() {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		manager.setListaCompra(almacenamiento.cargarListaCompra());
		manager.setFavoritos(almacenamiento.cargarListaFavoritos());
		ventana = primaryStage;
		ventana.setTitle("Lista de la compra");
		sceneLista=setupListScene();
		sceneFavoritos=setupFavScene();
		ventana.setScene(sceneLista);
		//Cuando se cierre la ventana queremos que se guarden los cambios.
		ventana.setOnCloseRequest(new EventHandler<WindowEvent>() {
	        @Override
	        public void handle(final WindowEvent event) {
				almacenamiento.guardarListaCompra(manager.getListaCompra());
				almacenamiento.guardarListaFavoritos(manager.getFavoritos());
	        }
		}
		);
		ventana.show();
	}

	

	
	public Scene setupListScene () {
			VBox root = new VBox ();
		    root.setSpacing (10.0);
		    root.setPadding (new Insets (10.0, 10.0, 10.0, 10.0));
		    Label saludoLabel = new Label ("¡Bienvenido! Esta es tu lista de la compra");
		    root.getChildren().addAll(saludoLabel);
		    
		    HBox contenido = new HBox ();
		    contenido.setSpacing (10.0);
		    contenido.setPadding (new Insets (10.0, 10.0, 10.0, 10.0));
		    contenido.setAlignment (Pos.TOP_LEFT);
		    Button manageFavorites = new Button("Favoritos");
		    manageFavorites.setId("manageFavorites");
		    manageFavorites.setOnAction((event) -> ventana.setScene(sceneFavoritos));
		    root.getChildren().addAll(contenido,manageFavorites);		   
		    
		    VBox controles = new VBox();
		    controles.getChildren().addAll(setupAnadirLinea(),setupModificarCantidad(),setupModificarComprado(),setupBorrarLinea(),
		    		setupSeguro());
		    
		    Label listaLabel = new Label ();
		    listaLabel.setPrefWidth (400.0);
		    	listaLabel.setId("listaLabel");
		    	if(imprimirListaCompra(manager.getListaCompra()).isEmpty()) {
		    		listaLabel.setText("Tu lista esta vacía, puedes añadir productos cuando quieras.");
		    	}else {
		    		listaLabel.setText(imprimirListaCompra(manager.getListaCompra()));
		    	}


		    contenido.getChildren ().addAll (listaLabel, controles);
		    
		    Scene scene = new Scene (root, 1000, 600);
		    return scene;
	}


	private HBox setupSeguro() {
		Label labelSeguro =new Label("Modo seguro: ");
		CheckBox seguro = new CheckBox();
		seguro.selectedProperty().addListener (new CheckBoxListener(botones));
		HBox layoutSeguro = new HBox();
		layoutSeguro.getChildren().addAll(labelSeguro,seguro);
		return layoutSeguro;
	}
	
	private Scene setupFavScene() {
		VBox root = new VBox ();
	    root.setSpacing (10.0);
	    root.setPadding (new Insets (10.0, 10.0, 10.0, 10.0));
	    Label saludoLabel = new Label ("¡Bienvenido! Aquí tienes tus favoritos:");
	    root.getChildren().addAll(saludoLabel);
	    
	    HBox contenido = new HBox ();
	    contenido.setSpacing (10.0);
	    contenido.setPadding (new Insets (10.0, 10.0, 10.0, 10.0));
	    contenido.setAlignment (Pos.TOP_LEFT);
	    Button manageList = new Button("Lista de la compra");
	    manageList.setId("manageList");
	    manageList.setOnAction((event) -> ventana.setScene(setupListScene()));
	    root.getChildren().addAll(contenido,manageList);
	    
	    VBox controles = new VBox();
	    controles.getChildren().addAll(setupAnadirFavALista(),setupAnadirFav(),setupBorrarFav());
	    
	    Label labelListaFavs = new Label ();
	    if(imprimirListaFavoritos(manager.getFavoritos()).isEmpty()) {
	    		labelListaFavs.setText("Parece que no tienes favoritos. Añade alguno.");
	    }else {
	    		labelListaFavs.setText(imprimirListaFavoritos(manager.getFavoritos()));
	    }
	    labelListaFavs.setId("labelListaFavs");
	    labelListaFavs.setPrefWidth (400.0);

	    contenido.getChildren ().addAll (labelListaFavs, controles);
		
		Scene scene2 = new Scene (root, 1000, 600);
	    return scene2;
	}


	private  Node setupBorrarLinea() {
		HBox borrarLinea1 = new HBox();
		Label labelBorrarLinea = new Label ("Introduce el número de línea:   ");
		TextField inputBorrarLinea = new TextField ();
		inputBorrarLinea.setId("inputBorrarLinea");
		inputBorrarLinea.setPrefWidth(40);
		borrarLinea1.getChildren().addAll(labelBorrarLinea,inputBorrarLinea);
		Button buttonBorrar = new Button("Borrar línea");
		buttonBorrar.setId("buttonBorrar");
		buttonBorrar.addEventFilter (MouseEvent.MOUSE_CLICKED, new ListbuttonsListener(buttonBorrar,manager,ventana));
		botones.add(buttonBorrar);
		VBox borrarLinea = new VBox();
		borrarLinea.getChildren().addAll(borrarLinea1,buttonBorrar);
		borrarLinea.setStyle(cssLayout);
		borrarLinea.setPadding(new Insets (10.0, 10.0, 10.0, 10.0));
		return borrarLinea;
	}


	private  Node setupModificarComprado() {
		HBox modificarComprado1 = new HBox();
		Label labelModificarComprado = new Label ("Introduce el número de línea:   ");
		TextField inputModificarComprado = new TextField ();
		inputModificarComprado.setId("inputModificarComprado");
		inputModificarComprado.setPrefWidth(40);
		modificarComprado1.getChildren().addAll(labelModificarComprado,inputModificarComprado);
		Button buttonModificarComprado = new Button("Modificar Comprado");
		buttonModificarComprado.setId("buttonModificarComprado");
		buttonModificarComprado.addEventFilter (MouseEvent.MOUSE_CLICKED, new ListbuttonsListener(buttonModificarComprado,manager,ventana));
		botones.add(buttonModificarComprado);
		VBox modificarComprado = new VBox();
		modificarComprado.getChildren().addAll(modificarComprado1,buttonModificarComprado);
		modificarComprado.setStyle(cssLayout);
		modificarComprado.setPadding(new Insets (10.0, 10.0, 10.0, 10.0));
		return modificarComprado;
	}


	private Node setupModificarCantidad() {
		HBox modificarCantidad1 = new HBox();
		Label labelLineaModificarCantidad = new Label("Introduce el número de línea:   ");
		TextField inputLineaModificarCantidad = new TextField ();
		inputLineaModificarCantidad.setId("inputLineaModificarCantidad");
		inputLineaModificarCantidad.setPrefWidth(40);
		modificarCantidad1.getChildren().addAll(labelLineaModificarCantidad,inputLineaModificarCantidad);
		HBox modificarCantidad2 = new HBox();
		Label labelCantidadModificarCantidad = new Label("Introduce la cantidad:   ");
		TextField inputCantidadModificarCantidad = new TextField ();
		inputCantidadModificarCantidad.setId("inputCantidadModificarCantidad");
		inputCantidadModificarCantidad.setPrefWidth(40);
		modificarCantidad2.getChildren().addAll(labelCantidadModificarCantidad,inputCantidadModificarCantidad);
		Button buttonModificarCantidad = new Button("Modificar cantidad");
		botones.add(buttonModificarCantidad);
		buttonModificarCantidad.setId("buttonModificarCantidad");
		buttonModificarCantidad.addEventFilter (MouseEvent.MOUSE_CLICKED, new ListbuttonsListener(buttonModificarCantidad,manager,ventana));
		VBox modificarCantidad = new VBox();
		modificarCantidad.getChildren().addAll(modificarCantidad1,modificarCantidad2,buttonModificarCantidad);
		modificarCantidad.setStyle(cssLayout);
		modificarCantidad.setPadding(new Insets (10.0, 10.0, 10.0, 10.0));
		return modificarCantidad;
	}


	private Node setupAnadirLinea() {
		HBox anadirLinea1 = new HBox();
		Label labelProductoAnadirLinea = new Label("Introduce el producto a añadir: ");
		TextField inputProductoAnadirLinea = new TextField ();
		inputProductoAnadirLinea.setId("inputProductoAnadirLinea");
		inputProductoAnadirLinea.setPrefWidth(200);
		anadirLinea1.getChildren().addAll(labelProductoAnadirLinea,inputProductoAnadirLinea);
		HBox anadirLinea2 = new HBox();
		Label labelCantidadAnadirLinea = new Label("Introduce la cantidad a añadir: ");
		TextField inputCantidadAnadirLinea = new TextField ();
		inputCantidadAnadirLinea.setId("inputCantidadAnadirLinea");
		inputCantidadAnadirLinea.setPrefWidth(40);
		anadirLinea2.getChildren().addAll(labelCantidadAnadirLinea,inputCantidadAnadirLinea);
		Button buttonAnadir = new Button("Añadir linea");	
		buttonAnadir.addEventFilter (MouseEvent.MOUSE_CLICKED, new ListbuttonsListener(buttonAnadir,manager,ventana));
		buttonAnadir.setId("buttonAnadir");
		botones.add(buttonAnadir);
		VBox anadirLinea = new VBox();
		anadirLinea.getChildren().addAll(anadirLinea1,anadirLinea2,buttonAnadir);
		anadirLinea.setStyle(cssLayout);
		anadirLinea.setPadding(new Insets (10.0, 10.0, 10.0, 10.0));
		return anadirLinea;
	}
	
	private Node setupAnadirFavALista() {
		HBox anadirFav1 = new HBox();
		Label labelFavoritoAnadirFav = new Label("Introduce el Nº de favorito:	");
		TextField inputFavAnadirFavALista = new TextField ();
		inputFavAnadirFavALista.setId("inputFavAnadirFavALista");
		inputFavAnadirFavALista.setPrefWidth(40);
		anadirFav1.getChildren().addAll(labelFavoritoAnadirFav,inputFavAnadirFavALista);
		HBox anadirFav2 = new HBox();
		Label labelCantidadAnadirFavALista = new Label("Introduce la cantidad:		");
		TextField inputCantidadAnadirFavALista = new TextField ();
		inputCantidadAnadirFavALista.setId("inputCantidadAnadirFavALista");
		inputCantidadAnadirFavALista.setPrefWidth(40);
		anadirFav2.getChildren().addAll(labelCantidadAnadirFavALista,inputCantidadAnadirFavALista);
		Button buttonAnadirFavALista = new Button("Añadir favoritos a lista de la compra");
		buttonAnadirFavALista.setId("buttonAnadirFavALista");
		buttonAnadirFavALista.addEventFilter(MouseEvent.MOUSE_CLICKED, new ListbuttonsListener(buttonAnadirFavALista,manager,ventana));
		VBox anadirFav = new VBox();
		anadirFav.getChildren().addAll(anadirFav1,anadirFav2,buttonAnadirFavALista);
		anadirFav.setStyle(cssLayout);
		anadirFav.setPadding(new Insets (10.0, 10.0, 10.0, 10.0));
		return anadirFav;
	}
	
	private Node setupAnadirFav() {
		HBox anadirFav1 = new HBox();
		Label labelNombreAnadirFav = new Label("Introduce el nombre del producto:	");
		TextField inputNombreAnadirFav = new TextField ();
		inputNombreAnadirFav.setId("inputNombreAnadirFav");
		inputNombreAnadirFav.setPrefWidth(200);
		anadirFav1.getChildren().addAll(labelNombreAnadirFav,inputNombreAnadirFav);
		Button buttonAnadirFav = new Button("Añadir favorito");
		buttonAnadirFav.addEventFilter (MouseEvent.MOUSE_CLICKED, new ListbuttonsListener(buttonAnadirFav,manager,ventana));
		buttonAnadirFav.setId("buttonAnadirFav");
		VBox anadirFav = new VBox();
		anadirFav.getChildren().addAll(anadirFav1,buttonAnadirFav);
		anadirFav.setStyle(cssLayout);
		anadirFav.setPadding(new Insets (10.0, 10.0, 10.0, 10.0));
		return anadirFav;
	}

	
	private Node setupBorrarFav() {
		HBox borrarFav1 = new HBox();
		Label labelNombreBorrarFav = new Label("Introduce el Nº del producto:	");
		TextField inputNumBorrarFav = new TextField ();
		inputNumBorrarFav.setId("inputNumBorrarFav");
		inputNumBorrarFav.setPrefWidth(40);
		borrarFav1.getChildren().addAll(labelNombreBorrarFav,inputNumBorrarFav);
		Button buttonBorrarFav = new Button("Borrar favorito");
		buttonBorrarFav.addEventFilter (MouseEvent.MOUSE_CLICKED, new ListbuttonsListener(buttonBorrarFav,manager,ventana));
		buttonBorrarFav.setId("buttonBorrarFav");
		VBox borrarFav = new VBox();
		borrarFav.getChildren().addAll(borrarFav1,buttonBorrarFav);
		borrarFav.setStyle(cssLayout);
		borrarFav.setPadding(new Insets (10.0, 10.0, 10.0, 10.0));
		return borrarFav;
	}
	
	private String imprimirListaCompra(ListaCompra lista) {
		int i =0;
		String res="";
		for(Linea l: lista.getLista()) {
			String comprado="No";
			if(l.getComprado()) {
				comprado="Sí";
			}
			res += i + "- " + l.getProducto().getNombre() + " - " + l.getCantidad() + " - " + comprado + "\n";
			i++;
		}
		return res;
	}
	
	private String imprimirListaFavoritos(ListaFavoritos lista) {
		String res="";
		int i=0;
		for(Producto p: lista.getFavoritos()) {
			res+=i +  " - " + p.getNombre() + "\n";
			i++;
		}
		return res;
	}
	
}
