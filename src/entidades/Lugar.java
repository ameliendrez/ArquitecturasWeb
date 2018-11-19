package entidades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Lugar {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String ciudad;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn
	Set<Usuario>trabajadores;

	public Lugar(String nombre, String ciudad) {
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.trabajadores = new HashSet<Usuario>();
	}
	
	public Lugar() {
		this.trabajadores = new HashSet<Usuario>();
	}

	public boolean equals(Lugar l) {
		return (this.nombre.equals(l.getNombre()) && this.ciudad.equals(l.getCiudad()));
	}

	@Override
	public String toString() {
		String nombres = "";
		if (!this.trabajadores.isEmpty()) {
			nombres += ", trabajadores = ";
			for (Usuario usuario : this.trabajadores) {
				nombres += usuario.getNombre() + ", ";
				nombres += usuario.getApellido() + ".  ";
			}
		}
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

	public void setTrabajadores(HashSet<Usuario> trabajadores) {
		this.trabajadores = trabajadores;
	}

	public Set<Usuario> getTrabajadores() {
		return this.trabajadores;
	}

}
