package es.ubu.proyecto.management;


import es.ubu.proyecto.model.*;


/**
 * El gestor de la lista de la compra. Permite añadir lineas, borrarlas y editarlas. 
 * También gestiona la lista de productos favoritos.
 * @author marcos
 *
 */
public class ListManager {
	
	private ListaCompra lista;
	private ListaFavoritos favoritos;
	
	private static ListManager instance = null;
	
	private ListManager() {
		lista= new ListaCompra();
		favoritos = new ListaFavoritos();;
	}
	
	
	public boolean anadirLinea(String nombreProducto, int cantidad, boolean fav) {
		Producto p = new Producto(nombreProducto);
		p.setFavorito(fav);
		Linea l = new Linea(p,cantidad);
		return lista.add(l);
	}
	
	public boolean anadirProducto(Producto p,int cantidad) {
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
	
	public String imprimirFavs() {
		String res = "";
		int i=0;
		for(Producto p: favoritos.getFavoritos()) {
			res+=i + " - " + p.getNombre() + "\n";	
			i++;
		}
		return res;
	}

	public ListaCompra getListaCompra() {
		return lista;
	}


	public void setListaCompra(ListaCompra lista) {
		this.lista=lista;
	}
	
	public ListaFavoritos getFavoritos() {
		return favoritos;
	}


	public void setFavoritos(ListaFavoritos favoritos) {
		this.favoritos = favoritos;
	}
	
	public Producto getFavorito(int indice) {
		if(indice>=0 && indice<=favoritos.size()-1) {
			return favoritos.getFavorito(indice);
		}else {
			return null;
		}
			
	}
	
	public boolean borrarFav(int indice) {
		if((favoritos.remove(indice)) != null) {
			return true;
		}else {
			return false;
		}

	}
	
	public boolean anadirFav(String nombre) {
		return favoritos.add(nombre);
	}
	

	
	public static ListManager getInstance() {
		if (instance == null) {
			return new ListManager();
		}else {
			return instance;
		}
	}
}
