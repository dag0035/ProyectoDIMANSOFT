package es.ubu.proyecto.storage;

import es.ubu.proyecto.model.*;

/**
 * Instancia que define como debería ser el funcionamiento básico del almacenamiento.
 * @author Marcos Orive Izarra.
 *
 */
public interface Storage {

	/**
	 * Para Obtener la lista de la compra.
	 * @return un objeto ListaCompra con la lista de la compra.
	 */
	public ListaCompra cargarListaCompra();
	
	/**
	 * Para Obtener la lista de favoritos.
	 * @return un objeto ListaFavoritos con la lista de la compra.
	 */
	public ListaFavoritos cargarListaFavoritos();
	
	/**
	 * Para guardar la lista de la compra.
	 * @param lista, el objeto ListaCompra que quieres guardar.
	 * @return true si ha podido guardarlo, false si no.
	 */
	public boolean guardarListaCompra(ListaCompra lista);
	
	/**
	 * Para guardar la lista de favoritos..
	 * @param favoritos, el objeto ListaFavoritos que quieres guardar.
	 * @return true si ha podido guardarlo, false si no.
	 */
	public boolean guardarListaFavoritos(ListaFavoritos favoritos);
	
	
}
