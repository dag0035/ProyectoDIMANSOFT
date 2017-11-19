package es.ubu.proyecto.model;

/**
 * Clase que modela un producto.
 * @author Marcos Orive Izarra.
 *
 */
public class Producto {
	
	/**
	 * Nombre del producto.
	 */
	private String nombre;
	/**
	 * Indica si un producto es favorito o no.
	 */
	private Boolean favorito;
	
	/**
	 * Constructor de clase. Pide un nombre y da NO favorito por defecto.
	 * @param nombre, el nombre del producto.
	 */
	public Producto(String nombre)	{
		this.nombre=nombre;
		this.favorito=false;
	}
	
	/**
	 * Getter del nombre.
	 * @return String con el nombre;
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Setter del nombre.
	 * @param nombre que queremos asignar.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Getter de favorito.
	 * @return Boolean, true si es favorito, false si no.
	 */
	public Boolean getFavorito() {
		return favorito;
	}
	
	/**
	 * Setter  de favorito.
	 * @param favorito boolean a asignar.
	 */
	public void setFavorito(Boolean favorito) {
		this.favorito = favorito;
	}
	
	/**
	 * Equals para procuto. Dos productos son iguales si su nombre es igual.
	 * @param p el producto a comparar. 
	 * @return True si son iguales, false si no.
	 */
	public boolean equals(Producto p) {
		if(this.getNombre() == p.getNombre() ) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Un producto se diferencia de otros por su nombre, de ahí que el hashcode sea simplemente
	 * el hascode de la string del nombre.
	 */
	@Override
	public int hashCode() {
		return this.getNombre().hashCode();
	}
	
	/**
	 * Convierte el objeto en una String
	 */
	public String toString() {
		String res="";
		res+=this.getNombre();
		if(this.getFavorito()) {
			res+="\tSí";
		}else {
			res+="\tNo";
		}
		return res;
	}

}
