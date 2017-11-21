package es.ubu.proyecto.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import es.ubu.proyecto.model.*;

public class CsvStorage {
	
	private static final String 	FICHEROLISTACOMPRA="files/savedList.csv";
	private static final String FICHEROLISTAFAVORITOS="files/savedFav.csv";
	
	
	public boolean writeListaCompra(ListaCompra lista) {
		try{
			BufferedWriter bufferEscritor = new BufferedWriter(new  FileWriter(FICHEROLISTACOMPRA));
			
			for(Linea l: lista.getLista()) {
				bufferEscritor.write(l.getProducto().getNombre()+";");
				bufferEscritor.write(l.getCantidad()+";");
				bufferEscritor.write(Boolean.toString(l.getComprado()));
				bufferEscritor.newLine();
			}
			
			bufferEscritor.close();
		}catch(IOException ex) {
			System.out.println("Ha habido un error con los archivos");
			return false;
         }

		
		return true;
	}
	
	public boolean writeFavoritos(List<Producto> favoritos) {
		try{

			BufferedWriter bufferEscritor = new BufferedWriter(new  FileWriter(FICHEROLISTAFAVORITOS));
			
			bufferEscritor.close();
		}catch(IOException ex) {
           
			return false;
         }

		
		return true;
	}
	
	public ListaCompra readListaCompra() {
		ListaCompra lista = new ListaCompra();
        try {
        		BufferedReader lector = new BufferedReader(new FileReader(FICHEROLISTACOMPRA));
			Scanner escaner = null;
			
			String linea=null;
			String nombreProducto;
			int cantidad;
			boolean comprado;
			while((linea = lector.readLine()) != null) {
				escaner= new Scanner(linea);
				escaner.useDelimiter(";");
				nombreProducto=escaner.next();
				cantidad=Integer.parseInt(escaner.next());
				comprado= Boolean.parseBoolean(escaner.next());
				Producto p = new Producto(nombreProducto);
				Linea l = new Linea(p,cantidad);
				l.setComprado(comprado);
				lista.add(l);
			}
			if(lector != null) {
				lector.close();
			}
			if(escaner != null){
				escaner.close();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("No se ha podido cargar la lista desde csvStorage");
			return lista;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return lista;
		
	}
	
	public List<Producto> readFavoritos() {
		
		return new ArrayList<Producto>();
		
	}

}
