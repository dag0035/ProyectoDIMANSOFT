package es.ubu.proyecto.GUI;

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
import es.ubu.proyecto.storage.StorageFacade;


public class GuiMain extends Application{
	
	private Stage ventana = null;
	private ListManager manager = null;
	private StorageFacade almacenamiento = null;
	private List<Button> botones=null;
	private static Scene sceneLista, sceneFavoritos;
	
	private final String cssLayout = "-fx-border-color: black;\n" +
            "-fx-border-insets: 1;\n" +
            "-fx-border-width: 1;\n" +
            "-fx-border-style: solid;\n";

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		manager = ListManager.getInstance();
		almacenamiento = StorageFacade.getInstace();
		manager.setListaCompra(almacenamiento.cargarListaCompra());
		botones= new ArrayList<Button>();
		this.ventana = primaryStage;
		this.ventana.setTitle("Lista de la compra");
		sceneLista=setupListScene();
		this.ventana.setScene(sceneLista);
		//Cuando se cierre la ventana queremos que se guarden los cambios.
		ventana.setOnCloseRequest(new EventHandler<WindowEvent>() {
	        @Override
	        public void handle(final WindowEvent event) {
				almacenamiento.guardarListaCompra(manager.getListaCompra());
				almacenamiento.guardarListaFavoritos(manager.getFavoritos());
	        }
		}
		);
		this.ventana.show();
	}

	
	public static void main(String []args) {
		launch(args);
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
		    sceneFavoritos=setupFavScene();
		    manageFavorites.setOnAction(e -> ventana.setScene(sceneFavoritos));
		    root.getChildren().addAll(contenido,manageFavorites);		   
		    
		    VBox controles = new VBox();
		    controles.getChildren().addAll(setupAnadirLinea(),setupModificarCantidad(),setupModificarComprado(),setupBorrarLinea(),
		    		setupSeguro());
		    
		    Label listaLabel = new Label (manager.imprimirLista());
		    	listaLabel.setId("listaLabel");
		    listaLabel.setPrefWidth (400.0);

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
	    manageList.setOnAction(e -> ventana.setScene(setupListScene()));
	    root.getChildren().addAll(contenido,manageList);
		
		Scene scene = new Scene (root, 1000, 600);
	    return scene;
	}


	private VBox setupBorrarLinea() {
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


	private VBox setupModificarComprado() {
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


	private VBox setupModificarCantidad() {
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


	private VBox setupAnadirLinea() {
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
	

}
