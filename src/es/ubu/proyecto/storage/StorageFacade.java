package es.ubu.proyecto.storage;


import es.ubu.proyecto.model.*;
public class StorageFacade implements Storage {
	
	private Storage storage;
	
	private static StorageFacade instance=null;
	
	private StorageFacade () {
		 storage = new CsvStorage();
	}
	
	public static StorageFacade getInstace() {
		if (instance == null) {
			return new StorageFacade();
		}else {
			return instance;
		}
	}
	
	public ListaCompra cargarListaCompra() {
		return storage.cargarListaCompra();
	}
	
	public ListaFavoritos cargarListaFavoritos() {
		return storage.cargarListaFavoritos();
	}
	
	public boolean guardarListaCompra(ListaCompra lista) {
		return storage.guardarListaCompra(lista);
	}
	
	public boolean guardarListaFavoritos(ListaFavoritos favoritos) {
		return storage.guardarListaFavoritos(favoritos);
	}
	

}
