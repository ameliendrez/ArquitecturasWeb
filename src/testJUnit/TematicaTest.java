package testJUnit;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import dao.TematicaDAO;
import entidades.Tematica;

public class TematicaTest {
	
	private static TematicaDAO tematicaDAO;
	private static Tematica tematica;
	private static Tematica tematicaBBDD;
	
	@BeforeClass
	public static void setUpEnviroment(){
		System.out.println("TematicaTest-> Comienza el test");
		System.out.println("TematicaTest-> Se prepara el ambiente");
		tematicaDAO = TematicaDAO.getInstance();
		tematica = new Tematica("Inteligencia Artifical", true);
		assertNotNull(tematica);
		tematicaDAO.persist(tematica);
		tematicaBBDD = tematicaDAO.getFirst();
		assertNotNull(tematicaBBDD);
	}
	
	@Test
	public void crearNuevaTematica(){
		System.out.println("TematicaTest-> Se crea una nueva tematica");
		Tematica nuevaTematica = new Tematica("HTML", false);
		assertNotNull(nuevaTematica);
		tematicaDAO.persist(nuevaTematica);
		int cantidadTematicas = tematicaDAO.getCantidadTematicas();
		assertEquals(2, cantidadTematicas);
	}

	@Test
	public void testCompararNombreLugar() {
		System.out.println("TematicaTest-> Se compara que el nombre sea correcto");
		assertEquals("Inteligencia Artifical", tematicaBBDD.getNombre());
	}
	
	@Test
	public void testCompararNombreLugarErroneo() {
		System.out.println("TematicaTest-> Se compara que el nombre sea incorrecto");
		assertNotEquals("PHP", tematicaBBDD.getNombre());
	}
	
	@Test
	public void testEsConocimientoExperto() {
		System.out.println("TematicaTest-> Se comprueba si es conocimiento experto");
		assertTrue(tematicaBBDD.getEsExperto());
	}
	
	@Test
	public void testNoEsConocimientoExperto() {
		System.out.println("TematicaTest-> Se comprueba si no es conocimiento experto");
		assertFalse(!tematicaBBDD.getEsExperto());
	}
	
//	@AfterClass
//	public static void tearDown(){
//		System.out.println("LugarTest-> Se eliminan datos de la tabla tematica");
//		TematicaDAO.getInstance().removeAll();
//	}
	
}
