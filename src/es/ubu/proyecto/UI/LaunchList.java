package es.ubu.proyecto.UI;

import es.ubu.proyecto.UI.GUI.*;
import es.ubu.proyecto.UI.textUI.*;
/**
 * Clase para lanzar la aplicación. Ejecuta la interfaz gráfica que inicialices. 
 * @author Marcos Orive Izarra.
 *
 */
public class LaunchList {

	/**
	 * Lanza la aplicación. Si se pone 'texto' al ejecutarlo en el terminal, se lanza con la UI textual.
	 * En caso de no poner nada, con la interfaz gráfica. 
	 * @param args, los argumentos del terminal.
	 */
	public static void main(String[] args) {
		UI ui;
		if(args.length == 0) {
			ui = new GuiMain();
		}
		else if(args[0].equals("texto")) {
			ui = new TextMain();
		}else {
			ui = new GuiMain();
		}
		ui.execute();
	}

}
