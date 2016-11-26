package server;
import java.util.ArrayList;

import character.*;

public class ListaPC {
	private ArrayList<PC>listaPC;
	
	public ListaPC() {
		listaPC = new ArrayList<PC>();
	}

	public ArrayList<PC> getListaPC() {
		return listaPC;
	}

	public void setListaPC() {
		this.listaPC = new ArrayList<PC>();
	}
}
