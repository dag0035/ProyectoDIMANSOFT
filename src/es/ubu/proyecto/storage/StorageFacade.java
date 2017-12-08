package es.ubu.proyecto.storage;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<Producto> cargarListaFavoritos() {
		return storage.readFavoritos();
	}
	
	public boolean guardarListaCompra(ListaCompra lista) {
		return storage.writeListaCompra(lista);
	}
	
	public boolean guardarListaFavoritos(List<Producto> favoritos) {
		return storage.writeFavoritos(favoritos);
	}
	

}
