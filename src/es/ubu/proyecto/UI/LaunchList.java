package es.ubu.proyecto.UI;

import es.ubu.proyecto.UI.GUI.*;
import es.ubu.proyecto.UI.textUI.*;

public class LaunchList {

	public static void main(String[] args) {
		UI ui = new TextMain();
		ui = new GuiMain();
		ui.execute();

	}

}
