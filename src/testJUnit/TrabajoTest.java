package testJUnit;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.EvaluacionDAO;
import dao.LugarDAO;
import dao.UsuarioDAO;
import dao.TematicaDAO;
import dao.TipoTrabajoDAO;
import dao.TrabajoDAO;
import entidades.Lugar;
import entidades.Tematica;
import entidades.TipoTrabajo;
import entidades.Trabajo;
import entidades.Usuario;
import entidades.Evaluacion;

public class TrabajoTest {
	
	private static LugarDAO lugarDAO;
	private static UsuarioDAO usuarioDAO;
	private static TematicaDAO tematicaDAO;
	private static TipoTrabajoDAO tipoTrabajoDAO;
	private static TrabajoDAO trabajoDAO;
	private static EvaluacionDAO evaluacionDAO;
	private static Lugar uncpba;
	private static Lugar unlp;
	private static Lugar uba;
	private static Usuario usuario1;
	private static Usuario usuario2;
	private static Usuario usuario3;
	private static Usuario usuario4;
	private static Usuario usuario5;
	private static Usuario usuario6;
	private static Usuario usuario7;
	private static Usuario usuario8;
	private static Usuario usuario9;
	private static Usuario usuario10;
	private static TipoTrabajo articulo;
	private static TipoTrabajo resumen;
	private static TipoTrabajo poster;
	private static Tematica java;
	private static Tematica html;
	private static Tematica css;
	private static Tematica javascript;
	private static Tematica php;
	private static Tematica mysql;
	private static Tematica bigData;
	private static Tematica ia;
	private static Tematica machineLearning;
	private static Tematica deepLearning;
	private static Trabajo trabajo;
	private static Trabajo trabajoBBDD;
	
	@BeforeClass
	public static void setUpEnviroment(){
		System.out.println("TrabajoTest-> Comienza el test");
		System.out.println("TrabajoTest-> Se prepara el ambiente");
		System.out.println("TrabajoTest-> Se crean diez trabajos");
		
		lugarDAO = LugarDAO.getInstance();
		usuarioDAO = UsuarioDAO.getInstance();
		tematicaDAO = TematicaDAO.getInstance();
		tipoTrabajoDAO = TipoTrabajoDAO.getInstance();
		trabajoDAO = TrabajoDAO .getInstance();
		evaluacionDAO = EvaluacionDAO.getInstance();
		
		uncpba = new Lugar("UNCPBA", "Tandil");
		assertNotNull(uncpba);
		unlp = new Lugar("UNLP", "La Plata");
		assertNotNull(unlp);
		uba = new Lugar ("UBA", "Buenos Aires");
		
		usuario1 = new Usuario(41313352, "Santiago", "Rampoldi", uncpba);
		assertNotNull(usuario1);
		usuario2 = new Usuario(36626803, "Agustin", "Meliendrez", uncpba);
		assertNotNull(usuario2);
		usuario3 = new Usuario(10679753, "Albert", "Einsten", unlp);
		assertNotNull(usuario3);
		usuario4 = new Usuario(16784639, "Steve", "Jobs", unlp);
		assertNotNull(usuario4);
		usuario5 = new Usuario(14273640, "Bill", "Gates", uncpba);
		assertNotNull(usuario5);
		usuario6 = new Usuario(20837464, "Mark", "Zuckerberg", unlp);
		assertNotNull(usuario6);
		usuario7 = new Usuario(15682781, "Stephen", "Hawking", unlp);
		assertNotNull(usuario7);
		usuario8 = new Usuario(27937477, "Alan", "Turing", uncpba);
		assertNotNull(usuario8);
		usuario9 = new Usuario(22736483, "Tim", "Berners-Lee", unlp);
		assertNotNull(usuario9);
		usuario10 = new Usuario(39887656, "Vinton", "Cerf", unlp);
		assertNotNull(usuario10);

		articulo = new TipoTrabajo("Articulo");
		assertNotNull(articulo);
		resumen = new TipoTrabajo("Resumen");
		assertNotNull(resumen);
		poster = new TipoTrabajo("Poster");
		assertNotNull(poster);

		java = new Tematica("Java", false);
		assertNotNull(java);
		html = new Tematica("HTML", false);
		assertNotNull(html);
		css = new Tematica("CSS", false);
		assertNotNull(css);
		javascript = new Tematica("Javascript", false);
		assertNotNull(javascript);
		php = new Tematica("PHP", false);
		assertNotNull(php);
		mysql = new Tematica("MySQL", false);
		assertNotNull(mysql);
		bigData = new Tematica("Big Data", true);
		assertNotNull(bigData);
		ia = new Tematica("Inteligencia Artificial", true);
		assertNotNull(ia);
		machineLearning = new Tematica("Machine Learning", true);
		assertNotNull(machineLearning);
		deepLearning = new Tematica("Deep Learning", true);
		assertNotNull(deepLearning);

		Set<Usuario> autores = new HashSet<Usuario>(); 
		Set <Usuario> autores2 = new HashSet<Usuario>();
		Set <Usuario> autores3 = new HashSet<Usuario>();
		Set <Usuario> autores4 = new HashSet<Usuario>();
		Set<Tematica> tematicas = new HashSet<Tematica>(); 
		Set <Tematica> tematicas2 = new HashSet<Tematica>();
		Set <Tematica> tematicas3 = new HashSet<Tematica>();
		Set <Tematica> tematicas4 = new HashSet<Tematica>();

		
		autores.add(usuario1);
		autores.add(usuario2);
		autores2.add(usuario4);
		autores2.add(usuario9);
		autores3.add(usuario5);
		autores3.add(usuario8);
		autores3.add(usuario10);
		autores4.add(usuario3);
		autores4.add(usuario6);
		autores4.add(usuario7);

		assertNotNull(autores);
		assertNotNull(autores2);
		assertNotNull(autores3);
		assertNotNull(autores4);

		tematicas.add(ia);
		tematicas.add(bigData);
		tematicas.add(machineLearning);
		tematicas.add(deepLearning);
		tematicas2.add(java);
		tematicas3.add(html);
		tematicas3.add(css);
		tematicas3.add(javascript);
		tematicas4.add(php);
		tematicas4.add(mysql);

		assertNotNull(tematicas);
		assertNotNull(tematicas2);
		assertNotNull(tematicas3);
		assertNotNull(tematicas4);

		trabajo = new Trabajo("Desmitificando la Ia", articulo, autores, tematicas);		
		Trabajo trabajo2 = new Trabajo("Python Aceptado", articulo);
		Trabajo trabajo3 = new Trabajo("Python Postergado", poster);
		Trabajo trabajo4 = new Trabajo("Los inicios de facebook", resumen);
		Trabajo trabajo5 = new Trabajo("El surgimiento de la web", articulo);
		Trabajo trabajo6 = new Trabajo("PythonEnRango", articulo, autores2, tematicas);
		Trabajo trabajo7 = new Trabajo("PhpExperto", articulo, autores3, tematicas2);
		Trabajo trabajo8 = new Trabajo("java", resumen, autores2, tematicas);
		Trabajo trabajo9 = new Trabajo("java", poster, autores4, tematicas3);
		Trabajo trabajo10 = new Trabajo("java", resumen, autores4, tematicas4);

		usuario1.addTrabajoInvestigacion(trabajo);
		usuario2.addTrabajoInvestigacion(trabajo);
		
		usuario4.addTrabajoInvestigacion(trabajo6);
		usuario4.addTrabajoInvestigacion(trabajo8);
		usuario9.addTrabajoInvestigacion(trabajo6);
		usuario9.addTrabajoInvestigacion(trabajo8);
		
		usuario5.addTrabajoInvestigacion(trabajo7);
		usuario8.addTrabajoInvestigacion(trabajo7);
		usuario10.addTrabajoInvestigacion(trabajo7);

		usuario3.addTrabajoInvestigacion(trabajo9);
		usuario6.addTrabajoInvestigacion(trabajo9);
		usuario7.addTrabajoInvestigacion(trabajo9);
		usuario3.addTrabajoInvestigacion(trabajo10);
		usuario6.addTrabajoInvestigacion(trabajo10);
		usuario7.addTrabajoInvestigacion(trabajo10);

		usuario7.addTrabajoEvaluacion(trabajo);
		Evaluacion evaluacion = new Evaluacion(trabajo, usuario7, "Excelente trabajo");

		usuario2.addTrabajoPendiente(trabajo10);
		usuario10.addTrabajoEvaluacion(trabajo);
		
		tipoTrabajoDAO.persist(articulo);
		tipoTrabajoDAO.persist(resumen);
		tipoTrabajoDAO.persist(poster);
		
		lugarDAO.persist(uncpba);
		lugarDAO.persist(unlp);
		lugarDAO.persist(uba);
		
		tematicaDAO.persist(java);
		tematicaDAO.persist(html);
		tematicaDAO.persist(css);
		tematicaDAO.persist(javascript);
		tematicaDAO.persist(php);
		tematicaDAO.persist(mysql);
		tematicaDAO.persist(bigData);
		tematicaDAO.persist(ia);
		tematicaDAO.persist(machineLearning);
		tematicaDAO.persist(deepLearning);
				
		trabajoDAO.persist(trabajo);
		trabajoDAO.persist(trabajo2);
		trabajoDAO.persist(trabajo3);
		trabajoDAO.persist(trabajo4);
		trabajoDAO.persist(trabajo5);
		trabajoDAO.persist(trabajo6);
		trabajoDAO.persist(trabajo7);
		trabajoDAO.persist(trabajo8);
		trabajoDAO.persist(trabajo9);
		trabajoDAO.persist(trabajo10);
		
		usuarioDAO.persist(usuario1);
		usuarioDAO.persist(usuario2);
		usuarioDAO.persist(usuario3);
		usuarioDAO.persist(usuario4);
		usuarioDAO.persist(usuario5);
		usuarioDAO.persist(usuario6);
		usuarioDAO.persist(usuario7);
		usuarioDAO.persist(usuario8);
		usuarioDAO.persist(usuario9);
		usuarioDAO.persist(usuario10);
		
		evaluacionDAO.persist(evaluacion);

		
		trabajoBBDD = trabajoDAO.getFirst();
		assertNotNull(trabajoBBDD);
	}
	
	@Test
	public void testGetNombreTrabajo(){
		System.out.println("TrabajoTest-> Se comprueba el nombre correcto del primer trabajo");
		assertEquals("Desmitificando la Ia", trabajoBBDD.getNombre());
	}

	@Test
	public void testGetNombreTrabajoErroneo(){
		System.out.println("TrabajoTest-> Se comprueba el nombre incorrecto del primer trabajo");
		assertNotEquals("La IA", trabajoBBDD.getNombre());
	}
	
	@Test
	public void getTipoTrabajo(){
		System.out.println("TrabajoTest-> Se comprueba el tipo de trabajo correcto del primero");
		assertEquals("Articulo", trabajoBBDD.getTipo().getNombre());
	}
	
	@Test
	public void getTipoTrabajoErroneo(){
		System.out.println("TrabajoTest-> Se comprueba el tipo de trabajo incorrecto del primero");
		assertNotEquals("Poster", trabajoBBDD.getTipo().getNombre());
	}
	
	@Test
	public void testGetCantidadAutores(){
		System.out.println("TrabajoTest-> Se comprueba la cantidad correcta de autores del primero");
		assertEquals(2, trabajoBBDD.getCantidadAutores());
	}
	
	@Test
	public void testGetCantidadAutoresErroneo(){
		System.out.println("TrabajoTest-> Se comprueba la cantidad incorrecta de autores del primero");
		assertNotEquals(6, trabajoBBDD.getCantidadAutores());
	}

	@Test
	public void testGetCantidadTemas(){
		System.out.println("TrabajoTest-> Se comprueba la cantidad correcta de temas del primero");
		assertEquals(4, trabajoBBDD.getCantidadTematicas());
	}
	
	@Test
	public void testGetCantidadTemasErroneo(){
		System.out.println("TrabajoTest-> Se comprueba la cantidad incorrecta de temas del primero");
		assertNotEquals(2, trabajoBBDD.getCantidadTematicas());
	}
	
	@Test
	public void testGetCantidadTrabajos(){
		System.out.println("TrabajoTest-> Se comprueba la cantidad correcta de trabajos");
		assertEquals(10, trabajoDAO.getCantidadTrabajos());
	}
	
	@Test
	public void testGetCantidadTrabajosErroneos(){
		System.out.println("TrabajoTest-> Se comprueba la cantidad incorrecta de trabajos");
		assertNotEquals(2, trabajoDAO.getCantidadTrabajos());
	}
	
	
	@Test
	public void testObtenerTrabajosAsignadosRevisor(){
		System.out.println("TrabajoTest-> Se comprueba la cantidad correcta de trabajos asignados a un evaluador");
		assertEquals(1, usuarioDAO.findAllTrabajosAsignados(15682781).size());
	}
	
	@Test
	public void testObtenerTrabajosAsignadosRevisorErroneo(){
		System.out.println("TrabajoTest-> Se comprueba la cantidad incorrecta de trabajos asignados a un evaluador");
		assertNotEquals(10, usuarioDAO.findAllTrabajosAsignados(15682781).size());
	}
	
	@Test
	public void testObtenerTrabajosRevisadosEnFechas(){
		System.out.println("TrabajoTest-> Se comprueba la cantidad correcta de trabajos revisados por un evaluador entre fechas");
		Calendar desde = Calendar.getInstance();
		desde.set(2018, 0, 1);
		Calendar hasta = Calendar.getInstance();
		hasta.set(2018, 11, 31);
		assertEquals(1, usuarioDAO.findAllTrabajosInvestigacionEnRango(15682781, desde, hasta).size());
	}
	
	@Test
	public void testObtenerTrabajosRevisadosEnFechasErroneo(){
		System.out.println("TrabajoTest-> Se comprueba la cantidad incorrecta de trabajos revisados por un evaluador entre fechas");
		Calendar desde = Calendar.getInstance();
		desde.set(2018, 0, 1);
		Calendar hasta = Calendar.getInstance();
		hasta.set(2018, 11, 31);
		assertNotEquals(7, usuarioDAO.findAllTrabajosInvestigacionEnRango(15682781, desde, hasta).size());
	}
	
	@Test
	public void testObtenerTrabajosEnviados() {
		System.out.println("TrabajoTest-> Se comprueba la cantidad correcta de trabajos presentados por un autor");
		assertEquals(2, usuarioDAO.findAllTrabajosEnviados(10679753).size());
	}
	
	@Test
	public void testObtenerTrabajosEnviadosErroneo() {
		System.out.println("TrabajoTest-> Se comprueba la cantidad incorrecta de trabajos presentados por un autor");
		assertNotEquals(9, usuarioDAO.findAllTrabajosEnviados(10679753).size());
	}
	
	@Test
	public void testObtenerTrabajosSegunAutorRevisorTema(){
		System.out.println("TrabajoTest-> Se comprueba cantidad de trabajos correcta segun autor, evaluador y tematica");
		assertEquals(1, usuarioDAO.findAllTrabajosAutorRevisorTema(36626803, 39887656, ia.getId()).size());
	}
	
	@Test
	public void testObtenerTrabajosSegunAutorRevisorTemaErroneo(){
		System.out.println("TrabajoTest-> Se comprueba cantidad de trabajos incorrecta segun autor, evaluador y tematica");
		assertNotEquals(2, usuarioDAO.findAllTrabajosAutorRevisorTema(36626803, 39887656, ia.getId()).size());
	}
	
	@AfterClass
	public static void tearDown(){
		System.out.println("LugarTest-> Se eliminan datos de la tabla trabajo y datos utilizados");

		TematicaDAO.getInstance().removeAll();
		LugarDAO.getInstance().removeAll();
		TipoTrabajoDAO.getInstance().removeAll();
		TrabajoDAO.getInstance().removeAll();
		UsuarioDAO.getInstance().removeAll();
		EvaluacionDAO.getInstance().removeAll();
	}
	
}
