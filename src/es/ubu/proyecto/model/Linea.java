package es.ubu.proyecto.model;

/**
 * Clase que modela una linea de la lista de la compra.
 * @author Marcos Orive Izarra.
 *
 */
public class Linea {
	
	/**
	 * El producto de la línea. 
	 */
	private Producto producto;
	/**
	 * La cantidad de ese producto que se quiere comprar.
	 */
	private int cantidad;
	/**
	 * Booleano para indicar si está comprado o no.
	 */
	private Boolean comprado;
	
	/**
	 * Constructor de la clase. Inicilia el producto y cantidad con los pasados. Comprado a false.
	 * @param p, producto de la línea.
	 * @param cantidad, cantidad de la linea.
	 */
	public Linea(Producto p, int cantidad) {
		producto=p;
		this.cantidad=cantidad;
		comprado=false;
	}
	
	/**
	 * Getter del producto.
	 * @return El objeto producto.
	 */
	public Producto getProducto() {
		return producto;
	}
	/**
	 * Setter del producto.
	 * @param producto, el producto a asignar.
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	/**
	 * Getter de la cantidad.
	 * @return int con la cantidad.
	 */
	public int getCantidad() {
		return cantidad;
	}
	/**
	 * Setter de la cantidad. 
	 * @param cantidad, la cantidad a asignar.
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	/**
	 * Getter de comprado.
	 * @return true si está comprado, false si no.
	 */
	public Boolean getComprado() {
		return comprado;
	}
	/**
	 * Setter de comprado.
	 * @param comprado el booleano a asignar.
	 */
	public void setComprado(Boolean comprado) {
		this.comprado = comprado;
	}
	
	/**
	 * Equals pra línea. Si tiene el mismo producto, cantidad y estado de compra, son iguales.
	 * @param l, línea a comprar.
	 * @return True si son iguales, false si no.
	 */
	public boolean equals (Linea l) {
		if(this.getProducto().getNombre() == l.getProducto().getNombre() 
			&& this.getCantidad() == l.getCantidad()
			&& this.getComprado() == l.getComprado()) {
			return true;
		}else {
			return false;
		}
	}
	
	public String toString() {
		String res="";
		res+=this.getProducto().getNombre();
		res+="\t\t";
		res+=Integer.toString(this.getCantidad());
		res+="\t\t";
		if(this.getComprado()) {
			res+="Sí";
		}else {
			res+="No";
		}
		return res;
	}

}
