package es.ubu.proyecto.textui;

import es.ubu.proyecto.management.*;
import es.ubu.proyecto.model.ListaCompra;
import es.ubu.proyecto.storage.StorageFacade;
import java.util.Scanner;

public class TextMain {
	
	private static Scanner teclado = new Scanner(System.in);
	private static final String msgErrorEntrada=("Opcion no valida, por favor introduce una opcion válida\n");
	private static StorageFacade almacenamiento = new StorageFacade();
	private static final String msgOpciones=(""
			+ "0 - Guardar y salir\n"
			+ "1 - Añadir una linea nueva\n"
			+ "2 - Eliminar una Linea\n"
			+ "3 - Cambiar la cantidad del producto\n"
			+ "4 - Marcar como comprado\n");
	

	public static void main(String[] args) {
		
		
		Boolean ejecucion=true;
		ListManager manager= new ListManager();
		int opcion=0;
		int linea=-1;
		int cantidad=0;
		String nombreProducto=""; 
		manager.setListaCompra(almacenamiento.cargarListaCompra());
		System.out.println("Bienvenido a tu lista de la compra.\n");

		while(ejecucion) {
			System.out.println("\n\n\n\n\n\nA continuación te monstramos tu lista.");
			System.out.println("\tNombre\t\tCantidad\t\tComprado");
			System.out.println(manager.imprimirLista());
			System.out.println("¿Qué quieres hacer?");
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
					System.out.println("Introduce el nombre del producto");
					teclado.nextLine();
					nombreProducto=teclado.nextLine();
					System.out.println("Introduce la cantidad");
					cantidad=teclado.nextInt();
					if(manager.anadirLinea(nombreProducto, cantidad)) {
						System.out.println("Linea añadida correctamente");
					}else {
						System.out.println("No se ha podido añadir la linea");
					}
					break;
					
				case 2:
					System.out.println("¿Qué linea quieres eliminar?");
					linea=teclado.nextInt();
					if(manager.eliminarLinea(linea)) {
						System.out.println("La línea se ha eliminado");
					}else {
						System.out.println("No se ha eliminado la linea. Es posible que no exista");
					}
					break;
					
				case 3:
					
					System.out.println("¿Qué linea quieres modificar?");
					linea=teclado.nextInt();
					System.out.println("¿Qué cantidad?");
					cantidad=teclado.nextInt();
					if(manager.cambiarCantidad(linea, cantidad)) {
						System.out.println("La línea se ha cambiado correctamente");
					}else {
						System.out.println("No se ha modificado la linea. Es posible que no exista");
					}
					break;
					
				case 4:
					System.out.println("¿Qué linea quieres marcar como comprada?");
					linea=teclado.nextInt();
					if(manager.marcarLineaComprada(linea)) {
						System.out.println("La línea se ha cambiado correctamente");
					}else {
						System.out.println("No se ha modificado la linea. Es posible que no exista");
					}
					break;
					
				default:
					System.out.println("Algo ha salido mal. Disculpa las molestias");
			}
		}
		
		teclado.close();

	}
	
	private static int leerOpcionTeclado() {
		int opcion=-1;
		Boolean correcto=true;
		while(correcto) {
			try{	
				opcion = teclado.nextInt();
			}catch(java.util.InputMismatchException e) {
				System.out.println("Debes introducir un número");
				teclado = new Scanner(System.in);
			}
			if(opcion>=0 && opcion<=4) {
				correcto=false;
			}else {
				System.out.println(msgErrorEntrada);
				System.out.println(msgOpciones);
			}
		}
		return opcion;
	}

}
