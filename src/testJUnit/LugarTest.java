package testJUnit;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.LugarDAO;
import entidades.Lugar;

public class LugarTest {
	
	private static LugarDAO lugarDAO;
	private static Lugar lugar;
	private static Lugar lugarBBDD;
	
	@BeforeClass
	public static void setUpEnviroment(){
		System.out.println("LugarTest-> Comienza el test");
		System.out.println("LugarTest-> Se prepara el ambiente");

		lugarDAO = LugarDAO.getInstance();
		lugar = new Lugar("UNCPBA", "Tandil");
		assertNotNull(lugar);
		lugarDAO.persist(lugar);
		lugarBBDD = lugarDAO.getFirst();
		assertNotNull(lugarBBDD);
	}
	
	@Test
	public void crearNuevoLugar(){
		System.out.println("LugarTest-> se crea un nuevo lugar");

		Lugar nuevoLugar = new Lugar("UBA", "Buenos Aires");
		assertNotNull(nuevoLugar);
		lugarDAO.persist(nuevoLugar);
		int cantidadLugares = lugarDAO.getCantidadLugares();
		assertEquals(2, cantidadLugares);
	}

	@Test
	public void testCompararNombreLugar() {
		System.out.println("LugarTest-> Se comprueba que el nombre sea correcto");
		assertEquals("UNCPBA", lugarBBDD.getNombre());
	}
	
	@Test
	public void testCompararNombreLugarErroneo() {
		System.out.println("LugarTest-> Se comprueba que el nombre sea incorrecto");
		assertNotEquals("UNLP", lugarBBDD.getNombre());
	}
	
	@Test
	public void testCompararNombreCiudad() {
		System.out.println("LugarTest-> Se comprueba que la ciudad sea correcto");
		assertEquals("Tandil", lugarBBDD.getCiudad());
	}
	
	@Test
	public void testCompararNombreCiudadErroneo() {
		System.out.println("LugarTest-> Se comprueba que la ciudad sea incorrecto");
		assertNotEquals("La plata", lugarBBDD.getCiudad());
	}
	
	@AfterClass
	public static void tearDown(){
		System.out.println("LugarTest-> Se eliminan datos de la tabla lugar");
		LugarDAO.getInstance().removeAll();
	}
	
}
