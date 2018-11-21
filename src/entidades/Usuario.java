package entidades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Usuario {

	//--------------Atributos de clase--------------

	@Id
	private int dni;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String apellido;

	@ManyToOne
	@JoinColumn
	private Lugar lugar;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn
	Set<Tematica>temas;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "evaluador_trabajo",
			joinColumns = { @JoinColumn(name = "evaluador_id") },
			inverseJoinColumns = { @JoinColumn(name = "trabajo_id") }
			)
	private Set<Trabajo> trabajos_evaluacion;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "evaluador_trabajo_pendiente",
			joinColumns = { @JoinColumn(name = "evaluador_id") },
			inverseJoinColumns = { @JoinColumn(name = "trabajo_pendiente_id") }
			)
	private Set<Trabajo> trabajos_pendientes;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn
	private Set<Evaluacion> evaluaciones;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "autor_trabajo",
			joinColumns = { @JoinColumn(name = "autor_id") },
			inverseJoinColumns = { @JoinColumn(name = "trabajo_id") }
			)
	private Set<Trabajo> trabajos_investigacion;

	//--------------Constructor--------------

	public Usuario(int dni, String nombre, String apellido, Lugar lugar) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.lugar = lugar;
		this.temas = new HashSet<Tematica>();
		this.trabajos_investigacion = new HashSet<Trabajo>();
		this.trabajos_pendientes = new HashSet<Trabajo>();
		this.trabajos_pendientes = new HashSet<Trabajo>();
	}

	public Usuario() {
		this.temas = new HashSet<Tematica>();
		this.trabajos_investigacion = new HashSet<Trabajo>();
		this.trabajos_pendientes = new HashSet<Trabajo>();
		this.trabajos_pendientes = new HashSet<Trabajo>();
	}

	//--------------toString--------------

	@Override
	public String toString() {
		String tr = "";
		if (!this.trabajos_investigacion.isEmpty()) {
			tr += ", trabajos de investigacion = ";
			for (Trabajo trabajo : this.trabajos_investigacion) {
				tr += trabajo.getNombre() + ", ";
				tr += trabajo.getTipoTrabajo().getNombre() + ".  ";
			}	
		}

		String te = "";
		if (!this.temas.isEmpty()) {
			te += ", temas = ";
			for (Tematica tematica : this.temas) {
				te += tematica.getNombre() + ".  ";
			}	
		}
		String retorno = "Usuario [dni = " + this.dni + ", nombre = " + this.nombre + ", apellido = " + this.apellido +
				", lugar = " + this.lugar.getNombre() + te + tr + "]"; 
		return retorno;
	}

	//--------------Getters y setters--------------

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

	public Set<Trabajo> getTrabajosInvestigacion() {
		return this.trabajos_investigacion;
	}

	public Set<Trabajo> getTrabajosEvaluacion() {
		return this.trabajos_pendientes;
	}

	public Set<Trabajo> getTrabajosPendientes() {
		return this.trabajos_pendientes;
	}

	public void setTema(Tematica t) {
		this.temas.add(t);
	}

	public Set<Tematica> getTemas() {
		return this.temas;
	}

	//--------------Controles y metodos de clase--------------

	public boolean addTrabajoInvestigacion(Trabajo trabajo) {
		if(!this.trabajos_pendientes.contains(trabajo) && !this.trabajos_pendientes.contains(trabajo)) {
			trabajo.setAutores(this);
			this.trabajos_investigacion.add(trabajo);
			return true;
		}
		return false;
	}

	public boolean addTrabajoEvaluacion(Trabajo trabajo) {
		if(this.trabajos_pendientes.size() >= 3 ) {
			return this.addTrabajoPendiente(trabajo);
		}
		else {
			this.trabajos_pendientes.add(trabajo);
			return true;
		}
	}

	public boolean addTrabajoPendiente(Trabajo trabajo) {
		if (this.esEvaluadorApto(trabajo)) {
			this.trabajos_pendientes.add(trabajo);
			return true;
		}
		return false;
	}

	public boolean aceptarTrabajo(Trabajo trabajo) {
		if(this.trabajos_pendientes.contains(trabajo)) {
			this.trabajos_pendientes.remove(trabajo);			
			return this.addTrabajoEvaluacion(trabajo);
		}
		return false;
	}

	public void rechazarTrabajo(Trabajo trabajo) {
		this.trabajos_pendientes.remove(trabajo);
	}

	public boolean calificarTrabajo(Trabajo trabajo, String observacion) {
		if(this.trabajos_pendientes.contains(trabajo)) {
			new Evaluacion(trabajo, this, observacion);
			return true;
		}
		return false;
	}

	public boolean esEvaluadorApto(Trabajo t) {
		if	(!this.trabajos_investigacion.contains(t)) {
			boolean mismoLugarTrabajo = false;
			for(Usuario u: t.getAutores()) {
				if(u.getLugar().equals(this.lugar))
					mismoLugarTrabajo = true;
			}
			if(!mismoLugarTrabajo) {
				Set<Tematica> clavesTrabajo = t.getTematicas();
				if(t.getTipoTrabajo().getCondicion()) 
					return this.temas.containsAll(clavesTrabajo);
				else {
					for(Tematica e: clavesTrabajo) {
						if(this.temas.contains(e)) 
							return true;
					}
				}
			}
		}
		return false;
	}

}
