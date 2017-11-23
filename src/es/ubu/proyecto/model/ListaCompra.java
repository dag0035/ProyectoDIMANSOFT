package es.ubu.proyecto.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que modela la lista de la compra. Utiliza una List de java para guardas líneas de la lista.
 * @author Marcos Orive Izarra.
 *
 */
public class ListaCompra {
	
	/**
	 * Lista que almacena las líneas de la lista.
	 */
	private List<Linea> lista;
	
	/**
	 * Constructor de la clase. Inicializa la lista a un ArrayList.
	 */
	public ListaCompra() {
		lista=new ArrayList<Linea>();
	}
	
	/**
	 * Método para acceder a la lista.
	 * @return un List con las líneas.
	 */
	public List<Linea> getLista() {
		return lista;
	}

	/**
	 * Setter para la lista. 
	 * @param lista, la lista que queremos asignar.
	 */
	public void setLista(List<Linea> lista) {
		this.lista = lista;
	}
	
	/**
	 * Método para añadir una línea a la lista.
	 * @param l, la línea a añadir. 
	 */
	public boolean add(Linea l) {
		return lista.add(l);
	}

	/**
	 * Método que calcula si una linea está en la lista.
	 * @param l la línea que queremos saber si está en la lista.
	 * @return True si está en la lista, False si no está. 
	 */
	public boolean containsLinea(Linea l) {
		return lista.contains(l);
	}
	
	/**
	 * Método que eliminar una línea. 
	 * @param l la línea a eliminar.
	 * @return True si se ha pododido eliminar, false si no.
	 */
	public boolean remove(int indice) {
		if(indice>lista.size()-1 || indice<0) return false;
		if (lista.remove(indice) != null) return true;
		return false;
	}
	/**
	 * Devuelve el índice de la línea.
	 * @param l la línea cuyo indice queremos conocer.
	 * @return el índice de la linea. Si no existe devuelve -1. 
	 */
	public int indexOf(Linea l) {
		return lista.indexOf(l);
	}

	/**
	 * Cambia el producto de una linea.
	 * @param index, el índice de la linea.
	 * @param p, el producto a cambiar.
	 * @return True si ha podido cambiarla, False si no. 
	 */
	public boolean editProducto(int index,Producto p) {
		if(index>lista.size()-1 || index<0) {
			return false;
		}
		Linea l = lista.get(index);
		if(l != null) {
			l.setProducto(p);
			lista.remove(index);
			lista.add(index, l);
			return true;
		}
		return false;
	}
	
	/**
	 * Cambia la cantidad de una línea. 
	 * @param index, el índice de la linea.
	 * @param cantidad, la cantidad a cambiar.
	 * @return True si ha podido cambiarla, False si no. 
	 */
	public boolean editCantidad(int index,int cantidad) {
		if(index>lista.size()-1 || index<0) {
			return false;
		}
		Linea l = lista.get(index);
		if(l != null) {
			l.setCantidad(cantidad);
			lista.remove(index);
			lista.add(index, l);
			return true;
		}

		return false;
	}

	/**
	 * Método para editar el estado de Comprado de una línea.
	 * @param index, el indice de la linea.
	 * @param comp, el estado a cambiar.
	 * @return True si ha podido cambiarla, False si no. 
	 */
	public boolean editComprado(int index,boolean comp) {
		if(index>lista.size()-1 || index<0) {
			return false;
		}
		Linea l = lista.get(index);
		if(l != null) {
			l.setComprado(comp);
			lista.remove(index);
			lista.add(index, l);
			return true;
		}
		return false;
	}
	
	public String toString() {
		String res="";
		for(Linea i: lista) {
			res+=lista.indexOf(i);
			res+=" - \t";
			res+=i.toString();
			res+="\n";
		}
		return res;
	}
	
}
