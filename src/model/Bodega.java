package model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

// entity especifica que esta clase es una entidad
@Entity
public class Bodega {

	// id identifica que esta variable es una primary key
	@Id
	private int id;
	
	private String nombre;

	//transient especifica que la propiedad o el campo no es persistente
	@Transient
	private ArrayList<Vid> listaVids = new ArrayList<Vid>();

	// constructores
	public Bodega() {
	}

	public Bodega(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Vid> getListaVids() {
		return listaVids;
	}

	public void setListaVids(ArrayList<Vid> listaVids) {
		this.listaVids = listaVids;
	}
	
}
