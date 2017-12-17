package es.ubu.proyecto.storage;
import es.ubu.proyecto.model.*;

/**
 * Fachada para el sistema de almacenamiento.
 * @author Marcos Orive Izarra.
 *
 */
public class StorageFacade implements Storage {
	
	/**
	 * Atributo que contiene la clase para gestionar el almacenamiento.
	 */
	private CsvStorage storage;
	/**
	 * Atributo con la instancia de la fachada.
	 */
	private static StorageFacade instance=null;
	/**
	 * Consutructor privado de la clase. Para el Singleton.
	 */
	private StorageFacade () {
		 storage = new CsvStorage();
	}
	/**
	 * Devuelve la instancia de la clase.
	 * @return el objeto instancia de la clase.
	 */
	public static StorageFacade getInstace() {
		if (instance == null) {
			return new StorageFacade();
		}else {
			return instance;
		}
	}

	/**
	 * Para Obtener la lista de la compra.
	 * @return un objeto ListaCompra con la lista de la compra.
	 */
	public ListaCompra cargarListaCompra() {
		return storage.readListaCompra();
	}
	/**
	 * Para Obtener la lista de favoritos.
	 * @return un objeto ListaFavoritos con la lista de la compra.
	 */
	public ListaFavoritos cargarListaFavoritos() {
		return storage.readListaFavoritos();
	}
	/**
	 * Para guardar la lista de la compra.
	 * @param lista, el objeto ListaCompra que quieres guardar.
	 * @return true si ha podido guardarlo, false si no.
	 */
	public boolean guardarListaCompra(ListaCompra lista) {
		return storage.writeListaCompra(lista);
	}
	/**
	 * Para guardar la lista de favoritos..
	 * @param favoritos, el objeto ListaFavoritos que quieres guardar.
	 * @return true si ha podido guardarlo, false si no.
	 */
	public boolean guardarListaFavoritos(ListaFavoritos favoritos) {
		return storage.writeListaFavoritos(favoritos);
	}
	

}
