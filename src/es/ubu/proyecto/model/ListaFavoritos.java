package es.ubu.proyecto.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que modela la lista de favoritos.
 * @author Marcos Orive Izarra
 *
 */
public class ListaFavoritos {
	
	/**
	 * Atributo que guarda la lista de favoritos.
	 */
	private List<Producto> favoritos;
	
	/**
	 * Constructor de la clase. Inicializa la lista.
	 */
	public ListaFavoritos() {
		setFavoritos(new ArrayList<Producto>());
	}
	/**
	 * Getter de la lista.
	 * @return la lista de favoritos.
	 */
	public List<Producto> getFavoritos() {
		return favoritos;
	}

	/**
	 * Setter de la lista de favoritos.
	 * @param favoritos, la lista que quieres asignar.
	 */
	public void setFavoritos(List<Producto> favoritos) {
		this.favoritos = favoritos;
	}
	/**
	 * Devuelve un favorito de la lista.
	 * @param index, el indice del favorito.
	 * @return un objeto producto de la lista.
	 */
	public Producto getFavorito(int index) {
		return favoritos.get(index);
	}
	
	/**
	 * Añade un producto a la lista de favoritos.
	 * @param nombre, el nombre del producto.
	 * @return true si ha podido añadirlo, false si no.
	 */
	public boolean add(String nombre) {
		Producto p = new Producto(nombre);
		p.setFavorito(true);
		return favoritos.add(p);
	}
	/**
	 * Devuelve el tamaño de la lista.
	 * @return Un int con el tamaño de la lista.
	 */
	public int size() {
		return favoritos.size();
	}
	/**
	 * Elimina un favorito de la lista.
	 * @param index, el indice del favorito a eliminar,
	 * @return devuelve el Producto eliminado. Null si no ha podido eliminarlo.
	 */
	public Producto remove(int index) {
		return favoritos.remove(index);
	}
}
