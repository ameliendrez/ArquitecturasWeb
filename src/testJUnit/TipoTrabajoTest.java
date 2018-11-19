package testJUnit;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.TipoTrabajoDAO;
import entidades.TipoTrabajo;

public class TipoTrabajoTest {
	
	private static TipoTrabajoDAO tipoTrabajoDAO;
	private static TipoTrabajo tipoTrabajo;
	private static TipoTrabajo tipoTrabajoBBDD;
	
	@BeforeClass
	public static void setUpEnviroment(){
		System.out.println("TipoTrabajoTest-> Comienza el test");
		System.out.println("TipoTrabajoTest-> Se prepara el ambiente");
		tipoTrabajoDAO = TipoTrabajoDAO.getInstance();
		tipoTrabajo = new TipoTrabajo("Articulo");
		assertNotNull(tipoTrabajo);
		tipoTrabajoDAO.persist(tipoTrabajo);
		tipoTrabajoBBDD = tipoTrabajoDAO.getFirst();
		assertNotNull(tipoTrabajoBBDD);
	}
	
	@Test
	public void crearNuevoTipoTrabajo(){
		System.out.println("TipoTrabajoTest-> Se crea un nuevo tipo de trabajo");
		TipoTrabajo nuevoTipoTrabajo = new TipoTrabajo("Poster");
		assertNotNull(nuevoTipoTrabajo);
		tipoTrabajoDAO.persist(nuevoTipoTrabajo);
		int cantidadTiposTrabajo = tipoTrabajoDAO.getCantidadTiposTrabajo();
		assertEquals(2, cantidadTiposTrabajo);
	}

	@Test
	public void testCompararTipoTrabajo() {
		System.out.println("TipoTrabajoTest-> Se compara que el tipo de trabajo sea correcto");
		assertEquals("Articulo", tipoTrabajoBBDD.getNombre());
	}
	
	@Test
	public void testCompararTipoTrabajoErroneo() {
		System.out.println("TipoTrabajoTest-> Se compara que el tipo de trabajo sea incorrecto");
		assertNotEquals("Poster", tipoTrabajoBBDD.getNombre());
	}
	
//	@AfterClass
//	public static void tearDown(){
//		System.out.println("LugarTest-> Se eliminan datos de la tabla tipotrabajo");
//		TipoTrabajoDAO.getInstance().removeAll();
//	}
//	

}
