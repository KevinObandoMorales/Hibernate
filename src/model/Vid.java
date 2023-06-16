package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//entity especifica que esta clase es una entidad
@Entity
public class Vid {

	// id identifica que esta variable es una primary key
	@Id
	private int id;

	private int cantidad;

	private int tipo_vid;

	// onetoone especifica que esta variable tiene una relacion muchoas a 1 o 1 a muchos y joincolumn especifica que esta columna se asocia con otra
	@ManyToOne
	@JoinColumn
	private Bodega bodega;

	// onetoone especifica que esta variable tiene una relacion muchoas a 1 o 1 a muchos y joincolumn especifica que esta columna se asocia con otra
	@ManyToOne
	@JoinColumn
	private Campo campo;

	// constructores
	public Vid() {
	}

	public Vid(int id, int cantidad, int tipo_vid, Campo campo) {
		this.id = id;
		this.cantidad = cantidad;
		this.tipo_vid = tipo_vid;
		this.campo = campo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getTipo_vid() {
		return tipo_vid;
	}

	public void setTipo_vid(int tipo_vid) {
		this.tipo_vid = tipo_vid;
	}

	public Bodega getBodega() {
		return bodega;
	}

	public void setBodega(Bodega bodega) {
		this.bodega = bodega;
	}

	public Campo getCampo() {
		return campo;
	}

	public void setCampo(Campo campo) {
		this.campo = campo;
	}

}
