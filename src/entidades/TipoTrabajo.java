package entidades;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_trabajo")
public class TipoTrabajo {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (nullable = false)
	private String nombre;
	
	@Column(nullable = false)
	private boolean cond_evaluacion;
	//Si es poster(0), el evaluador solo debe evaluar un tema. En el caso contrario (1) todos los temas.

	public TipoTrabajo(String nombre) {
		this.nombre = nombre;
	}
	
	public TipoTrabajo() {
	}
	
	@Override
	public String toString() {
		return "TipoTrabajo [id = " + id + ", nombre = " + nombre + "]";
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
	
	public boolean getCondicion(){
		return this.cond_evaluacion;
	}
	
	public void setCondicion(boolean condicion) {
		this.cond_evaluacion = condicion;
	}

}
