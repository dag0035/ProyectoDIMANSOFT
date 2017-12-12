package es.ubu.proyecto.storage;

import es.ubu.proyecto.model.*;

public interface Storage {

	
	public ListaCompra cargarListaCompra();
	
	public ListaFavoritos cargarListaFavoritos();
	
	public boolean guardarListaCompra(ListaCompra lista);
	
	public boolean guardarListaFavoritos(ListaFavoritos favoritos);
	
	
}
