package es.ubu.proyecto.management;


import es.ubu.proyecto.model.*;


/**
 * El gestor de la lista de la compra. Permite añadir lineas, borrarlas y editarlas. 
 * También gestiona la lista de productos favoritos.
 * @author marcos
 *
 */
public class ListManager {
	
	/**
	 * Atributo con la lista de la compra.
	 */
	private ListaCompra lista;
	/**
	 * Atrinuto con la lista de favoritos.
	 */
	private ListaFavoritos favoritos;
	/**
	 * Atributo con la instancia del singleton.
	 */
	private static ListManager instance = null;
	/**
	 * Constructor privado. Inicializa los atributos.
	 */
	private ListManager() {
		lista= new ListaCompra();
		favoritos = new ListaFavoritos();;
	}
	
	/**
	 * Añade una linea a la lista de la compra.
	 * @param nombreProducto, el nombre del producto.
	 * @param cantidad, la cantidad del producto.
	 * @param fav, si el producto es favotio o no.
	 * @return true si ha podido añadirlo, false si no.
	 */
	public boolean anadirLinea(String nombreProducto, int cantidad, boolean fav) {
		Producto p = new Producto(nombreProducto);
		p.setFavorito(fav);
		Linea l = new Linea(p,cantidad);
		return lista.add(l);
	}
	/**
	 * Añade una linea a la lista de la compra.
	 * @param p, el producto ya encapsulado.
	 * @param cantidad, la canidad.
	 * @return  true si ha podido añadirlo, false si no.
	 */
	public boolean anadirProducto(Producto p,int cantidad) {
		Linea l = new Linea(p,cantidad);
		return lista.add(l);
	}
	
	/**
	 * Elimina una línea de la lista de compra
	 * @param indice, el indice de la linea.
	 * @return  true si ha podido eliminarlo, false si no.
	 */
	public boolean eliminarLinea(int indice) {
		return lista.remove(indice);
		
	}
	/**
	 * Cambia la cantidad de una linea de la lista de la compra.
	 * @param indice, el indice de la linea.
	 * @param cantidad,la nueva cantidad.
	 * @return  true si ha podido cambiarlo, false si no.
	 */
	public boolean cambiarCantidad(int indice, int cantidad) {
		return lista.editCantidad(indice, cantidad);
	}
	
	/**
	 * Marca una linea como comprada.
	 * @param indice, el indice de la linea.
	 * @return  true si ha podido cambiarlo, false si no.
	 */
	public boolean marcarLineaComprada(int indice) {
		return lista.editComprado(indice, true);
		
	}
	/**
	 * Marca una linea como no comprada.
	 * @param indice, el indice de la linea.
	 * @return  true si ha podido cambiarlo, false si no.
	 */
	public boolean marcarLineaNoComprada(int indice) {
		return lista.editComprado(indice, false);
		
	}
	/**
	 * Devuelve una string legible de la lista.
	 * @return String con la lista.
	 */
	public String imprimirLista() {
		return lista.toString();
	}
	/**
	 * Devuelve una String con la lista de favoritos.
	 * @return una String con la lista de favoritos.
	 */
	public String imprimirFavs() {
		String res = "";
		int i=0;
		for(Producto p: favoritos.getFavoritos()) {
			res+=i + " - " + p.getNombre() + "\n";	
			i++;
		}
		return res;
	}
	/**
	 * Getter de la lista de la compra.
	 * @return la lista de la compra.
	 */
	public ListaCompra getListaCompra() {
		return lista;
	}

	/**
	 * Setter de la lista de la compra.
	 * @param lista, la lista de la compra que quieres asignar.
	 */
	public void setListaCompra(ListaCompra lista) {
		this.lista=lista;
	}
	/**
	 * Getter de la lista de favoritos.
	 * @return la lista de la favoritos.
	 */
	public ListaFavoritos getFavoritos() {
		return favoritos;
	}

	/**
	 * Setter de la lista de favoritos.
	 * @param lista, la lista de favoritos que quieres asignar.
	 */
	public void setFavoritos(ListaFavoritos favoritos) {
		this.favoritos = favoritos;
	}
	
	/**
	 * Dado un indice de la lista de favoritos devuelve el objeto Producto asociado.
	 * @param indice, el indice de la lista de favoritos.
	 * @return el producto de esa linea.
	 */
	public Producto getFavorito(int indice) {
		if(indice>=0 && indice<=favoritos.size()-1) {
			return favoritos.getFavorito(indice);
		}else {
			return null;
		}
			
	}
	
	/**
	 * Borra una linea de la lista de favoritos
	 * @param indice, el indice de la linea.
	 * @return  true si ha podido eliminarlo, false si no.	
	 */
	public boolean borrarFav(int indice) {
		if((favoritos.remove(indice)) != null) {
			return true;
		}else {
			return false;
		}

	}
	
	/**
	 * Añade un favoritio.
	 * @param nombre, el nombre del favorito.
	 * @return  true si ha podido añadirlo, false si no.
	 */
	public boolean anadirFav(String nombre) {
		return favoritos.add(nombre);
	}
	

	/**
	 * Método para obtener la instancia del singleton.
	 * @return la instancia ListManager del singleton.
	 */
	public static ListManager getInstance() {
		if (instance == null) {
			return new ListManager();
		}else {
			return instance;
		}
	}
}
