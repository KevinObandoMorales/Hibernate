package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Entrada {

	// id identifica que esta variable es una primary key
	@Id
	private int id;
	
	private String instruccion;

	// constructores
	public Entrada() {
	}

	public Entrada(int id, String instruccion) {
		this.id = id;
		this.instruccion = instruccion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInstruccion() {
		return instruccion;
	}

	public void setInstruccion(String instruccion) {
		this.instruccion = instruccion;
	}

}
