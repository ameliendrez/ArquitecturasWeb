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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Trabajo {

	//--------------Atributos de clase--------------

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String nombre;

	@ManyToOne
	@JoinColumn(nullable = false)
	private TipoTrabajo tipo;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "trabajosInvestigacion")
	@Column(nullable = false)
	Set<Usuario>autores;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "trabajosEvaluacion")
	@Column(nullable = false)
	Set<Usuario>evaluadores;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	Set<Tematica>temas;

	//--------------Constructor--------------

	public Trabajo(String nombre, TipoTrabajo tipo, Set<Usuario> autores, Set<Tematica> tematicas) {
		if (autores.size() <= 3) {
			this.autores = autores;	
		}
		else {
			this.autores = new HashSet<Usuario>();
		}
		this.nombre = nombre;
		this.tipo = tipo;
		this.temas = tematicas;
		this.evaluadores = new HashSet<Usuario>();
	}

	public Trabajo(String nombre, TipoTrabajo tipo) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.temas = new HashSet<Tematica>();
		this.autores = new HashSet<Usuario>();
		this.evaluadores = new HashSet<Usuario>();
	}

	public Trabajo() {
		this.temas = new HashSet<Tematica>();
		this.autores = new HashSet<Usuario>();
		this.evaluadores = new HashSet<Usuario>();
	}

	//--------------toString--------------

	@Override
	public String toString() {
		String a = "";
		if (!this.autores.isEmpty()) {
			for (Usuario usuario : this.autores) {
				a += usuario.getNombre() + ", ";
				a += usuario.getApellido() + ".  ";
			}	
		}
		String t = "";
		if (!this.temas.isEmpty()) {
			for (Tematica tematica : this.temas) {
				t += tematica.getNombre() + ".  ";
			}	
		}

		String retorno = "Trabajo [id = " + this.id + ", nombre = " + this.nombre + ", tipo = " + this.tipo.getNombre() 
		+ ", autores = " + a + ", tematicas = " + t + "]";
		return retorno;
	}

	//--------------Getters y setters--------------

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoTrabajo getTipo() {
		return tipo;
	}

	public void setTipo(TipoTrabajo tipo) {
		this.tipo = tipo;
	}

	public Set<Usuario> getAutores() {
		return autores;
	}

	public void setAutor(Usuario autor) {
		if (this.autores.size() <= 2) {
			this.autores.add(autor);	
		}
	}

	public Set<Tematica> getTemas() {
		return temas;
	}

	public void setTemas(Tematica tema) {
		this.temas.add(tema);
	}

	public int getId() {
		return id;
	}
	
	public int getCantidadAutores() {
		return autores.size();
	}
	
	public int getCantidadTematicas() {
		return temas.size();
	}

}
