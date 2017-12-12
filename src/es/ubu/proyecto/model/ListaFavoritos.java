package es.ubu.proyecto.model;

import java.util.ArrayList;
import java.util.List;

public class ListaFavoritos {
	
	private List<Producto> favoritos;
	
	public ListaFavoritos() {
		setFavoritos(new ArrayList<Producto>());
	}

	public List<Producto> getFavoritos() {
		return favoritos;
	}

	public void setFavoritos(List<Producto> favoritos) {
		this.favoritos = favoritos;
	}

	public Producto getFavorito(int index) {
		return favoritos.get(index);
	}
	
	public boolean add(String nombre) {
		Producto p = new Producto(nombre);
		p.setFavorito(true);
		return favoritos.add(p);
	}
	
	public int size() {
		return favoritos.size();
	}
	public Producto remove(int index) {
		return favoritos.remove(index);
	}
}
