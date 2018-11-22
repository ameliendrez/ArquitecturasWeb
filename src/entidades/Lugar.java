package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Lugar {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String ciudad;


	public Lugar(String nombre, String ciudad) {
		this.nombre = nombre;
		this.ciudad = ciudad;
	}
	
	public Lugar() {
	}

	public boolean equals(Lugar l) {
		return (this.nombre.equals(l.getNombre()) && this.ciudad.equals(l.getCiudad()));
	}

	@Override
	public String toString() {
		String nombres = "";

		String retorno = "Lugar [id = " + this.id + ", nombre = " + this.nombre + ", ciudad = " + this.ciudad 
				+ nombres + "]";
		return retorno; 
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


}
