package entidades;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;


@Entity
public class Evaluacion {

	//--------------Atributos de clase--------------

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	@JoinColumn(nullable = false)
	private Trabajo trabajo;

	@OneToOne
	@JoinColumn(nullable = false)
	private Usuario evaluador;

	@Column(nullable = false)
	@CreationTimestamp
	private Calendar fecha;

	@Column(nullable = true)
	private String observacion;

	//--------------Constructor--------------

	public Evaluacion() {
	}

	public Evaluacion(Trabajo trabajo, Usuario evaluador, String observacion) {
		this.trabajo = trabajo;
		this.evaluador = evaluador;
		this.fecha = Calendar.getInstance();	//Suponemos que el momento en que se registra la evaluacion es cuando fue realizada
		this.observacion = observacion;
	}

	//--------------Getters y Setters--------------

	@Override
	public String toString() {
		return "Evaluacion [id = " + this.id + ", trabajo = " + this.trabajo.getNombre() 
		+ ", evaluador = " + this.evaluador.getNombre() + ", " + this.evaluador.getApellido() +
		", fecha = " + this.fecha.getTime() + ", observacion = " + this.observacion + "]";
	}

	public Trabajo getTrabajo() {
		return trabajo;
	}

	public void setTrabajo(Trabajo trabajo) {
		this.trabajo = trabajo;
	}

	public Usuario getEvaluador() {
		return evaluador;
	}

	public void setEvaluador(Usuario evaluador) {
		this.evaluador = evaluador;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public int getId() {
		return id;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

}

