package es.ubu.proyecto.management;


import java.util.List;
import java.util.ArrayList;
import es.ubu.proyecto.model.*;


/**
 * El gestor de la lista de la compra. Permite añadir lineas, borrarlas y editarlas. 
 * También gestiona la lista de productos favoritos.
 * @author marcos
 *
 */
public class ListManager {
	
	private ListaCompra lista;
	private List<Producto> favoritos;
	
	private static ListManager instance = null;
	
	private ListManager() {
		lista= new ListaCompra();
		setFavoritos(new ArrayList<Producto>());
	}
	
	
	public boolean anadirLinea(String nombreProducto, int cantidad) {
		Producto p = new Producto(nombreProducto);
		Linea l = new Linea(p,cantidad);
		return lista.add(l);
	}
	
	public boolean eliminarLinea(int indice) {
		return lista.remove(indice);
		
	}

	public boolean cambiarCantidad(int indice, int cantidad) {
		return lista.editCantidad(indice, cantidad);
	}
	
	public boolean marcarLineaComprada(int indice) {
		return lista.editComprado(indice, true);
		
	}
	
	public boolean marcarLineaNoComprada(int indice) {
		return lista.editComprado(indice, false);
		
	}
	
	public String imprimirLista() {
		return lista.toString();
	}

	public ListaCompra getListaCompra() {
		return lista;
	}


	public void setListaCompra(ListaCompra lista) {
		this.lista=lista;
	}
	
	public List<Producto> getFavoritos() {
		return favoritos;
	}


	public void setFavoritos(List<Producto> favoritos) {
		this.favoritos = favoritos;
	}
	
	public static ListManager getInstance() {
		if (instance == null) {
			return new ListManager();
		}else {
			return instance;
		}
	}
}
