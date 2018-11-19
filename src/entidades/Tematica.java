package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tematica {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (nullable = false)
	private String nombre;
	
	@Column (nullable = false)
	private Boolean esExperto;

	public Tematica(String nombre, Boolean esExperto) {
		this.nombre = nombre;
		this.esExperto = esExperto;
	}
	
	public Tematica() {
	}

	@Override
	public String toString() {
		String s = "";
		
		if (esExperto) { s = "Conocimiento experto"; }
		else { s = "Conocimiento general"; }
		
		return "Tematica [id = " + this.id + ", nombre = " + this.nombre + ", esExperto = " + s + "]";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getEsExperto() {
		return esExperto;
	}

	public void setEsExperto(Boolean esExperto) {
		this.esExperto = esExperto;
	}

	public int getId() {
		return id;
	}

}
