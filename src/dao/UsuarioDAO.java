package dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import entidades.EMF;
import entidades.Evaluacion;
import entidades.Tematica;
import entidades.Trabajo;
import entidades.Usuario;    

public class UsuarioDAO extends BaseJpaDAO<Usuario, Integer> {

	private static UsuarioDAO usuarioDao;

	private UsuarioDAO(){
		super(Usuario.class,Integer.class);
	}

	public static UsuarioDAO getInstance() {
		if(usuarioDao == null)
			usuarioDao = new UsuarioDAO();
		return usuarioDao;
	}

	@Override
	public Usuario findById(Integer id) {
		EntityManager entityManager = EMF.createEntityManager();
		Usuario usuario = entityManager.find(Usuario.class, id);
		entityManager.close();
		return usuario;
	}

	public Usuario persist(Usuario usuario) {
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();
		entityManager.close();
		return usuario;
	}

	public void removeAll() {
		EntityManager entityManager = EMF.createEntityManager();
		Query query = entityManager.createQuery("DELETE FROM Usuario");
		entityManager.getTransaction().begin();
		query.executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public boolean persistMany(Set<Usuario> usuarios) {
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		for (Usuario u : usuarios) {
			entityManager.persist(u);	
		}
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}

	public boolean esExperto(Integer id) {
		EntityManager entityManager = EMF.createEntityManager();
		Usuario user = this.findById(id);
		if(user != null) {
			Query query = entityManager.createQuery(
					"SELECT t FROM Tematica t, Usuario u WHERE u.dni = :id AND t MEMBER OF u.temas");

			query.setParameter("id", id);
			List<Tematica> t = query.getResultList();
			for (int i = 0; i < t.size(); i++) {
				if (t.get(i).getEsExperto() == true) {
					return true;
				}
			}
		}
		return false;
	}

	public List<Trabajo> findAllTrabajosEnEvaluacion(Integer id){
		EntityManager entityManager = EMF.createEntityManager();
		Usuario user = this.findById(id);
		if(user != null) {
			Query query = entityManager.createQuery(
					"SELECT t FROM Trabajo t, Usuario u WHERE u.dni = :id AND t MEMBER OF u.trabajos_evaluacion");

			query.setParameter("id", id);
			if (!query.getResultList().isEmpty()) {
				return query.getResultList();
			}
		}
		return new ArrayList<Trabajo>();
	}

	public List<Trabajo> findAllTrabajosPendientes(Integer id){
		EntityManager entityManager = EMF.createEntityManager();
		Usuario user = this.findById(id);
		if(user != null) {
			Query query = entityManager.createQuery(
					"SELECT t FROM Trabajo t, Usuario u WHERE u.dni = :id AND t MEMBER OF u.trabajos_pendientes");

			query.setParameter("id", id);
			if (!query.getResultList().isEmpty()) {
				entityManager.close();
				return query.getResultList();
			}
		}
		entityManager.close();
		return new ArrayList<Trabajo>();
	}

	public List<Trabajo> findAllTrabajosAsignados(Integer id) {
		//Consideramos a todos los trabajos asignados como los trabajos a evaluar y los trabajos pendientes
		ArrayList<Trabajo> retorno = new ArrayList<Trabajo>();
		retorno.addAll(this.findAllTrabajosEnEvaluacion(id));
		retorno.addAll(this.findAllTrabajosPendientes(id));
		return retorno;
	}

	public List<Trabajo> findAllTrabajosEnviados(Integer id){
		EntityManager entityManager = EMF.createEntityManager();
		Usuario user = this.findById(id);
		if(user != null) {
			Query query = entityManager.createQuery(
					"SELECT t FROM Trabajo t, Usuario u WHERE u.dni = :id AND t MEMBER OF u.trabajos_investigacion");

			query.setParameter("id", id);
			if (!query.getResultList().isEmpty()) {
				return query.getResultList();
			}
		}
		return new ArrayList<Trabajo>();
	}

	public List<Usuario> findAll(){
		EntityManager entityManager = EMF.createEntityManager();
		List<Usuario>retorno = new ArrayList<Usuario>();
		Query query = entityManager.createQuery("SELECT u FROM Usuario u");
		if (!query.getResultList().isEmpty()) {
			retorno = query.getResultList();
			return retorno;
		}
		throw new UnsupportedOperationException();
	}

	public int getCantidadUsuarios(){
		return findAll().size()+0;
	}

	public Usuario getFirst(){
		return findAll().get(0);
	}

	public List<Trabajo> findAllTrabajosInvestigacionRevisorEnRango(Integer id, Calendar desde, Calendar hasta){
		EntityManager entityManager = EMF.createEntityManager();
		Usuario user = this.findById(id);
		if(user != null) {
			Query query = entityManager.createQuery(
					"SELECT e.trabajo FROM Evaluacion e WHERE e.evaluador = :user AND e.fecha >= :desde AND e.fecha <= :hasta");

			query.setParameter("user", user);
			query.setParameter("desde", desde);
			query.setParameter("hasta", hasta);
			if (!query.getResultList().isEmpty()) {
				return query.getResultList();
			}
		}
		return new ArrayList<Trabajo>();
	}

	public List<Trabajo> findAllTrabajosAutorRevisorTema(int idAutor, int idEvaluador, int idTematica) {
		EntityManager entityManager = EMF.createEntityManager();
		Usuario autor = this.findById(idAutor);
		Usuario evaluador = this.findById(idEvaluador);
		List<Trabajo>retorno = new ArrayList<Trabajo>();

		TematicaDAO daoT = TematicaDAO.getInstance();
		Tematica tema = daoT.findById(idTematica);
		if(autor != null && evaluador != null && tema !=null) {
//			Query query2 = entityManager.createNativeQuery(
//					"SELECT * FROM trabajo t "
//							+ "JOIN autor_trabajo aut ON t.id = aut.trabajo_id "
//							+ "JOIN evaluador_trabajo et ON t.id = et.trabajo_id "
//							+ "JOIN trabajo_tematica tt ON t.id = tt.Trabajo_id "
//							+ "WHERE aut.autor_id = :idAutor " 
//							+ "AND et.evaluador_id = :idEvaluador "
//							+ "AND tt.temas_id = :idTematica", Trabajo.class);

		// "SELECT e.trabajo FROM Evaluacion e WHERE e.evaluador = :user 
		//	AND e.fecha >= :desde AND e.fecha <= :hasta");
			
			//SELECT e.trabajo FROM Evaluacion e WHERE e.evaluador = :evaluador
			// AND e.trabajo.autor = :autor 
			
//SELECT t FROM Trabajo t, Evaluacion e, 
//WHERE e.evaluador = :evaluador
//AND t = e.trabajo
//AND :tema MEMBER of t.tematicas
//AND :autor MEMBER OF t.autores

//		"SELECT t FROM Tematica t, Usuario u 
		//WHERE u.dni = :id AND t MEMBER OF u.temas");

//  				Query query = entityManager.createQuery("SELECT t FROM "
//  						+ "Trabajo t JOIN t.autores at JOIN t.evaluadores et JOIN t.tematicas tpc" 
//  					+ "WHERE at.id = :autor AND et.id = :evaluador AND tpc.id = :tematica");
	
Query query = entityManager.createQuery("SELECT t FROM Evaluacion e "
		+ "JOIN e.trabajo t JOIN t.autores aut JOIN t.tematicas tem "
		+ "WHERE e.evaluador = :evaluador "
		//+ "AND :tema MEMBER of tem "
		+ "AND :autor MEMBER OF aut ");

			query.setParameter("autor", autor);
			query.setParameter("evaluador", evaluador);
			//query.setParameter("tema", tema);
			if (!query.getResultList().isEmpty()) {
				retorno = query.getResultList();
				return retorno;
			}
		}
		return new ArrayList<Trabajo>();
	}

	public boolean delete(int id) {
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("DELETE FROM Usuario u WHERE u.id = :id");
		query.setParameter("id", id);
		int deletedCount = query.executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.close();
		if(deletedCount > 0)
			return true;
		else
			return false;
	}

	public Usuario update(int id, Usuario entity) {
		EntityManager entityManager = EMF.createEntityManager();
		Usuario entityAux = entityManager.find(Usuario.class, id);
		if (entityAux == null) {
			entityManager.close();
			return null;
		} else {
			entityManager.getTransaction().begin();
			entityAux.setNombre(entity.getNombre());
			entityAux.setApellido(entity.getApellido());
			entityAux.setLugar(entity.getLugar());
			entityManager.getTransaction().commit();
			entityManager.close();
			return entityAux;
		}
	}

	public List<Tematica> conocimientosDeUnUsuario (Usuario usuario) {		
		int id = usuario.getDni();
		EntityManager entityManager = EMF.createEntityManager();	
		String jpql = "SELECT c FROM Usuario u JOIN u.temas c WHERE u.id = :id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		List<Tematica> result = query.getResultList();

		entityManager.close();
		return result;
	}

	public ArrayList<Evaluacion> getEvaluaciones(int dni) {
		Usuario evaluador = UsuarioDAO.getInstance().findById(dni);
		EntityManager entityManager = EMF.createEntityManager();
		String jpql = "SELECT e FROM Evaluacion e WHERE e.evaluador = :id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", evaluador);
		List<Evaluacion> resultados = query.getResultList();
		entityManager.close();

		return (ArrayList<Evaluacion>) resultados;
	}

	public Boolean addTrabajo(Integer id_usuario, Integer id_trabajo) {
		EntityManager entityManager = EMF.createEntityManager();
		Usuario usuario = entityManager.find(Usuario.class, id_usuario);
		Trabajo trabajo = entityManager.find(Trabajo.class,id_trabajo);

		if(trabajo == null) {
			entityManager.close();
			throw new IllegalArgumentException("El trabajo no existe");
		}	

		if(usuario == null) {
			entityManager.close();
			throw new IllegalArgumentException("El usuario no existe");	
		}

		if (!usuario.esEvaluadorApto(trabajo)) {
			entityManager.close();
			throw new UnsupportedOperationException("El revisor no es apto");	
		}

		entityManager.getTransaction().begin();
		usuario.addTrabajoPendiente(trabajo);
		entityManager.getTransaction().commit();
		entityManager.close();

		return true;
	}

}
