package es.ubu.proyecto.storage;


import es.ubu.proyecto.model.*;
public class StorageFacade {
	
	private CsvStorage storage;
	
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
		return storage.readListaCompra();
	}
	
	public ListaFavoritos cargarListaFavoritos() {
		return storage.readFavoritos();
	}
	
	public boolean guardarListaCompra(ListaCompra lista) {
		return storage.writeListaCompra(lista);
	}
	
	public boolean guardarListaFavoritos(ListaFavoritos favoritos) {
		return storage.writeFavoritos(favoritos);
	}
	

}
