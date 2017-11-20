package es.ubu.proyecto.storage;
import java.util.ArrayList;
import java.util.List;

import es.ubu.proyecto.model.*;
public class StorageFacade {
	
	private CsvStorage storage = new CsvStorage();
	
	public ListaCompra cargarListaCompra() {
		return storage.readListaCompra();
	}
	
	public List<Producto> cargarListaFavoritos() {
		return new ArrayList<Producto>();
	}
	
	public boolean guardarListaCompra(ListaCompra lista) {
		return storage.writeListaCompra(lista);
	}
	
	public boolean guardarListaFavoritos() {
	
		return false;
	}
	

}
