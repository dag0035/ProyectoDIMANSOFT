package es.ubu.proyecto.textui;

import es.ubu.proyecto.management.*;
import es.ubu.proyecto.model.*;
import es.ubu.proyecto.storage.*;
import java.util.Scanner;

public class TextMain {
	

	private final String msgErrorEntrada=("Opcion no valida, por favor introduce una opcion válida\n");
	private final String msgOpciones=(""
			+ "0 - Guardar y salir\n"
			+ "1 - Añadir una linea nueva\n"
			+ "2 - Eliminar una Linea\n"
			+ "3 - Cambiar la cantidad del producto\n"
			+ "4 - Marcar como comprado\n"
			+ "5 - Añadir un favorito a la lista de la compra\n"
			+ "6 - Añadir un producto a favoritos\n"
			+ "7 - Eliminar un producto de favoritos\n");
	
	private Storage almacenamiento = StorageFacade.getInstace();
	private Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
		
		TextMain t = new TextMain();
		t.execution();

	}

	private void execution() {
		Boolean ejecucion=true;
		ListManager manager= ListManager.getInstance();
		int opcion=0;
		manager.setListaCompra(almacenamiento.cargarListaCompra());
		manager.setFavoritos(almacenamiento.cargarListaFavoritos());
		System.out.println("\n\nBienvenido a tu lista de la compra.");

		while(ejecucion) {
			System.out.println("\nA continuación te monstramos tu lista.");
			System.out.println(" - Nombre - Cantidad - Comprado");
			imprimirListaCompra(manager.getListaCompra());
			System.out.println("\n Estos son tus favoritos: ");
			imprimirListaFavoritos(manager.getFavoritos());
			System.out.println("\n¿Qué quieres hacer?");
			System.out.println(msgOpciones);
			
			opcion=leerOpcionTeclado();
			switch(opcion) {
				case 0:
					if(almacenamiento.guardarListaCompra(manager.getListaCompra())) {
						System.out.println("Se ha guardado tu lista. ¡Hasta pronto!");
					}else {
						System.out.println("No se ha podido guardar, sentimos los molestias");
					}
					ejecucion=false;
					break;
				case 1:
					anadirLinea(manager);
					break;
					
				case 2:
				borrarLinea(manager);
					break;
					
				case 3:
					cambiarCantidad(manager);
					break;
					
				case 4:
					cambiarComprado(manager);
					break;
					
				case 5:
					anadirFavoritoALista(manager);
					break;
					
				case 6:
				anadirFavorito(manager);
					break;
					
				case 7:
				borrarFavorito(manager);
					break;
				default:
					System.out.println("Algo ha salido mal. Disculpa las molestias");
			
			}
		}
		

	}

	private void borrarFavorito(ListManager manager) {
		int linea;
		System.out.println("Introduce número de favorito a borrar: ");
		linea=leerEnteroPositivoTeclado();
		if(linea<=manager.getFavoritos().size()-1) {
			manager.borrarFav(linea);
			System.out.println("Eliminado correctamente");
		}else {
			System.out.println("No se ha podido borrar ese favorito. Es probable que no exista");
		}
	}

	private void anadirFavorito(ListManager manager) {
		String nombreProducto;
		System.out.println("Introduce nombre del producto que quieres añadir: ");
		nombreProducto=leerStringTeclado();
		if(!nombreProducto.isEmpty()) {
			manager.anadirFav(nombreProducto);
			System.out.println("Añadido correctamente");
		}else {
			System.out.println("No se ha podido introducir ese producto en favoritos.");
		}
	}

	private void anadirFavoritoALista(ListManager manager) {
		int linea;
		int cantidad;
		System.out.println("Introduce el número favorito que quieres añadir");
		linea=leerEnteroPositivoTeclado();
		if(linea<=manager.getFavoritos().size()-1) {
			System.out.println("¿Qué cantidad?");
			cantidad=leerEnteroPositivoTeclado();
			manager.anadirProducto(manager.getFavorito(linea), cantidad);
		}else {
			System.out.println("Parece que ese favorito no existe");
		}
	}


	private void cambiarComprado(ListManager manager) {
		int linea;
		System.out.println("¿Qué linea quieres marcar como comprada?");
		linea=leerEnteroPositivoTeclado();
		if(manager.marcarLineaComprada(linea)) {
			System.out.println("La línea se ha cambiado correctamente");
		}else {
			System.out.println("No se ha modificado la linea. Es posible que no exista");
		}
	}

	private void cambiarCantidad(ListManager manager) {
		int linea;
		int cantidad;
		System.out.println("¿Qué linea quieres modificar?");
		linea=leerEnteroPositivoTeclado();
		System.out.println("¿Qué cantidad?");
		cantidad=leerEnteroPositivoTeclado();
		if(manager.cambiarCantidad(linea, cantidad)) {
			System.out.println("La línea se ha cambiado correctamente");
		}else {
			System.out.println("No se ha modificado la linea. Es posible que no exista");
		}
	}

	private void borrarLinea(ListManager manager) {
		int linea;
		System.out.println("¿Qué linea quieres eliminar?");
		linea=leerEnteroPositivoTeclado();
		if(manager.eliminarLinea(linea)) {
			System.out.println("La línea se ha eliminado");
		}else {
			System.out.println("No se ha eliminado la linea. Es posible que no exista");
		}
	}

	private void anadirLinea(ListManager manager) {
		int cantidad;
		String nombreProducto;
		System.out.println("Introduce el nombre del producto");
		nombreProducto=leerStringTeclado();
		System.out.println("Introduce la cantidad");
		cantidad=leerEnteroPositivoTeclado();
		if(manager.anadirLinea(nombreProducto, cantidad,false)) {
			System.out.println("Linea añadida correctamente");
		}else {
			System.out.println("No se ha podido añadir la linea");
		}
	}
	
	private int leerEnteroPositivoTeclado() {
		int entero=-1;
		Boolean correcto=true;
		while(correcto) {
			try{	
				entero = teclado.nextInt();
			}catch(java.util.InputMismatchException e) {
				System.out.println("Opción no válida, debes introducir un número");
				teclado = new Scanner(System.in);
			}
			if(entero>-1) {
				
				correcto=false;
			}else {
				System.out.println("Debes introducir un número positivo");
			}
		}

		return entero;
	}
	
	private int leerOpcionTeclado() {
		int opcion = leerEnteroPositivoTeclado();
		while(opcion>8) {
			System.out.println(msgErrorEntrada);
			System.out.println(msgOpciones);
			opcion = leerEnteroPositivoTeclado();
		}
		return opcion;
	}
	
	private void imprimirListaCompra(ListaCompra lista) {
		int i =0;
		for(Linea l: lista.getLista()) {
			String comprado="No";
			if(l.getComprado()) {
				comprado="Sí";
			}
			
			System.out.println(i + " - " + l.getProducto().getNombre() + " - " + l.getCantidad() + " - " + comprado);
			i++;
		}
	}
	
	private void imprimirListaFavoritos(ListaFavoritos lista) {
		int i=0;
		for(Producto p: lista.getFavoritos()) {
			System.out.println(i +  " - " + p.getNombre());
			i++;
		}
	}
	
	private String leerStringTeclado() {

		 teclado.nextLine();
		 String nombre = teclado.nextLine();
		 return nombre;
	}

}
