package testJUnit;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import dao.LugarDAO;
import dao.UsuarioDAO;
import dao.TematicaDAO;
import entidades.Lugar;
import entidades.Usuario;
import entidades.Tematica;


@FixMethodOrder(MethodSorters.JVM)
public class UsuarioTest {
	
	private static LugarDAO lugarDAO;
	private static Lugar uncpba;
	private static Lugar unlp;
	private static TematicaDAO tematicaDAO;
	private static UsuarioDAO usuarioDAO;
	private static Usuario usuario;
	private static Usuario usuarioBBDD;
	
	@BeforeClass
	public static void setUpEnviroment(){
		System.out.println("UsuarioTest-> Comienza el test");
		System.out.println("UsuarioTest-> Se prepara el ambiente");

		lugarDAO = LugarDAO.getInstance();
		tematicaDAO = TematicaDAO.getInstance();
		uncpba = new Lugar("UNCPBA", "Tandil");
		assertNotNull(uncpba);
		unlp = new Lugar("UNLP", "La Plata");
		assertNotNull(unlp);
		usuarioDAO = UsuarioDAO.getInstance();
		usuario = new Usuario(36626800, "Agustin", "Meliendrez", uncpba);
		assertNotNull(usuario);
		
		Tematica tema = new Tematica("Inteligencia Artificial", true);
		usuario.setTema(tema);
	
		
		tematicaDAO.persist(tema);
		lugarDAO.persist(uncpba);
		lugarDAO.persist(unlp);
		usuarioDAO.persist(usuario);
		usuarioBBDD = usuarioDAO.getFirst();
		assertNotNull(usuarioBBDD);
	}
	
	@Test
	public void testCrearNuevoUsuario(){
		System.out.println("UsuarioTest-> Se crea nuevo usuario");

		Usuario nuevoUsuario = new Usuario(41313351, "Rampoldi", "Santiago", uncpba);
		assertNotNull(nuevoUsuario);
		usuarioDAO.persist(nuevoUsuario);
		int cantidadUsuarios = usuarioDAO.getCantidadUsuarios();
		assertEquals(2, cantidadUsuarios);
	}

	@Test
	public void testCompararNombreUsuario() {
		System.out.println("UsuarioTest-> Se compara el nombre correcto del usuario");
		assertEquals("Agustin", usuarioBBDD.getNombre());
	}
	
	@Test
	public void testCompararNombreUsuarioErroneo() {
		System.out.println("UsuarioTest-> Se compara el nombre incorrecto del usuario");
		assertNotEquals("Jorge", usuarioBBDD.getNombre());
	}
	
	@Test
	public void testCompararApellidoUsuario() {
		System.out.println("UsuarioTest-> Se compara el apellido correcto del usuario");
		assertEquals("Meliendrez", usuarioBBDD.getApellido());
	}
	
	@Test
	public void testCompararApellidoErroneo() {
		System.out.println("UsuarioTest-> Se compara el apellido incorrecto del usuario");
		assertNotEquals("Rodriguez", usuarioBBDD.getApellido());
	}
	
	@Test
	public void testCompararDniUsuario() {
		System.out.println("UsuarioTest-> Se compara el DNI correcto del usuario");
		assertEquals(36626800, usuarioBBDD.getDni());
	}
	
	@Test
	public void testCompararDniUsuarioErroneo() {
		System.out.println("UsuarioTest-> Se compara el DNI incorrecto del usuario");
		assertNotEquals(11222333, usuarioBBDD.getDni());
	}
	
	@Test
	public void testCompararLugarUsuario() {
		System.out.println("UsuarioTest-> Se compara el lugar correcto del usuario");
		assertEquals("UNCPBA", usuarioBBDD.getLugar().getNombre());
	}
	
	@Test
	public void testCompararLugarUsuarioErroneo() {
		System.out.println("UsuarioTest-> Se compara el lugar incorrecto del usuario");
		assertNotEquals("UNLP", usuarioBBDD.getLugar().getNombre());
	}
	
	@Test
	public void esUsuarioExperto(){
		System.out.println("UsuarioTest-> Se comprueba si es un usuario experto");
		assertTrue(usuarioDAO.esExperto(36626800));
	}
	
	@Test
	public void esUsuarioEspertoErroneo(){
		System.out.println("UsuarioTest-> Se comprueba que no sea un usuario experto");

		assertFalse(!usuarioDAO.esExperto(36626800));
	}
	
	@Test
	public void testMostrarDatosDeUsuario(){
		System.out.println("UsuarioTest-> Se imprime los datos completos de un usuario");
		Usuario u = usuarioDAO.findById(36626800);
		System.out.println(u.toString());
	}
	
	@Test
	public void testCrearDiezUsuarios(){
		System.out.println("UsuarioTest-> Se crean diez usuarios");
		
		Usuario usuario1 = new Usuario(22736482, "Tim", "Berners-Lee", uncpba);
		assertNotNull(usuario1);
		Usuario usuario2 = new Usuario(39887658, "Vinton", "Cerf", unlp);
		assertNotNull(usuario2);
		Usuario usuario3 = new Usuario(10679754, "Albert", "Einsten", uncpba);
		assertNotNull(usuario3);
		Usuario usuario4 = new Usuario(16784638, "Steve", "Jobs", unlp);
		assertNotNull(usuario4);
		Usuario usuario5 = new Usuario(14273648, "Bill", "Gates", uncpba);
		assertNotNull(usuario5);
		Usuario usuario6 = new Usuario(20837465, "Mark", "Zuckerberg", unlp);
		assertNotNull(usuario6);
		Usuario usuario7 = new Usuario(15682783, "Stephen", "Hawking", unlp);
		assertNotNull(usuario7);
		Usuario usuario8 = new Usuario(27937478, "Alan", "Turing", uncpba);
		assertNotNull(usuario8);

		usuarioDAO.persist(usuario2);
		usuarioDAO.persist(usuario3);
		usuarioDAO.persist(usuario4);
		usuarioDAO.persist(usuario5);
		usuarioDAO.persist(usuario6);
		usuarioDAO.persist(usuario7);
		usuarioDAO.persist(usuario8);
		usuarioDAO.persist(usuario1);

		int cantidadUsuarios = usuarioDAO.getCantidadUsuarios();
		assertEquals(10, cantidadUsuarios);
	}
	
	@AfterClass
	public static void tearDown(){
//		System.out.println("LugarTest-> Se eliminan datos de la tabla usuario y datos utilizados");
//		TematicaDAO.getInstance().removeAll();
//		LugarDAO.getInstance().removeAll();
//		UsuarioDAO.getInstance().removeAll();
//		
//		System.out.println("Test-> Se eliminan la base de datos cacic2018db");
//		UsuarioDAO.getInstance().dropDatabaseCacic();
//		System.out.println("Test-> Finaliza test");
	}
}
