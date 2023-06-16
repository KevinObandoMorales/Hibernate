package model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

//entity especifica que esta clase es una entidad
@Entity
public class Campo {

	// id identifica que esta variable es una primary key
	@Id
	private int id;
	
	// onetoone especifica que esta variable solo tiene una relacion 1 a 1 y joincolumn especifica que esta columna se asocia con otra
	@OneToOne
	@JoinColumn
	private Bodega bodega;
	
	//transient especifica que la propiedad o el campo no es persistente
	@Transient
	private ArrayList<Vid> listaVids = new ArrayList<Vid>();
	
	//Constructores de la clase
	public Campo() {}
	
	public Campo(int id, Bodega bodega) {
		this.id = id;
		this.bodega = bodega;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Bodega getBodega() {
		return bodega;
	}

	public void setBodega(Bodega bodega) {
		this.bodega = bodega;
	}

	public ArrayList<Vid> getListaVids() {
		return listaVids;
	}

	public void setListaVids(ArrayList<Vid> listaVids) {
		this.listaVids = listaVids;
	}
	
}
