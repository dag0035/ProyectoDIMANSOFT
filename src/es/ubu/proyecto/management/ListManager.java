package es.ubu.proyecto.management;


import java.util.List;
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
}
