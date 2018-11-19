package dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import entidades.EMF;
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
		Query query = entityManager.createNativeQuery("DELETE FROM usuario");
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
			Query query = entityManager.createNativeQuery(
					"SELECT t.* FROM tematica t "
							+ "JOIN usuario_tematica u ON t.id = u.temas_id "
							+ "WHERE u.usuario_dni = :id", Tematica.class);
			query.setParameter("id", id);
			List<Tematica> t = query.getResultList();
			for (int i = 0; i < t.size(); i++) {
				if (t.get(i).getEsExperto() == true) {
					//					System.out.println(t.get(i).getId());
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
			Query query = entityManager.createNativeQuery(
					"SELECT t.* FROM trabajo t "
							+ "JOIN evaluador_trabajo et ON t.id = et.trabajo_id "
							+ "WHERE et.evaluador_id = :id", Trabajo.class);
			query.setParameter("id", id);
			if (!query.getResultList().isEmpty()) {
				return query.getResultList();
			}
		}
		//System.out.println("La consulta no devolvio ningun resultado");
		return new ArrayList<Trabajo>();
	}

	public List<Trabajo> findAllTrabajosPendientes(Integer id){
		EntityManager entityManager = EMF.createEntityManager();
		Usuario user = this.findById(id);
		if(user != null) {
			Query query = entityManager.createNativeQuery(
					"SELECT t.* FROM trabajo t "
							+ "JOIN evaluador_trabajoPendiente etp ON t.id = etp.trabajoPendiente_id "
							+ "WHERE etp.evaluador_id = :id", Trabajo.class);
			query.setParameter("id", id);
			if (!query.getResultList().isEmpty()) {
				entityManager.close();
				return query.getResultList();
			}
		}
		//System.out.println("La consulta no devolvio ningun resultado");
		entityManager.close();
		return new ArrayList<Trabajo>();
	}

	public List<Trabajo> findAllTrabajosAsignados(Integer id){
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
			Query query = entityManager.createNativeQuery(
					"SELECT t.* FROM trabajo t "
							+ "JOIN autor_trabajo et ON t.id = et.trabajo_id "
							+ "WHERE et.autor_id = :id", Trabajo.class);
			query.setParameter("id", id);
			if (!query.getResultList().isEmpty()) {
				return query.getResultList();
			}
		}
		//System.out.println("La consulta no devolvio ningun resultado");
		return new ArrayList<Trabajo>();
	}

	public List<Usuario> findAllUsuarios(){
		EntityManager entityManager = EMF.createEntityManager();
		List<Usuario>retorno = new ArrayList<Usuario>();
		Query query = entityManager.createNativeQuery("SELECT * FROM usuario", Usuario.class);
		if (!query.getResultList().isEmpty()) {
			retorno = query.getResultList();
			return retorno;
		}
		//System.out.println("La consulta no devolvio ningun resultado");
		throw new UnsupportedOperationException();
	}

	public int getCantidadUsuarios(){
		return findAllUsuarios().size()+0;
	}

	public Usuario getFirst(){
		return findAllUsuarios().get(0);
	}

	public List<Trabajo> findAllTrabajosInvestigacionEnRango(Integer id, Calendar desde, Calendar hasta){
		EntityManager entityManager = EMF.createEntityManager();
		Usuario user = this.findById(id);
		if(user != null) {
			Query query = entityManager.createNativeQuery(
					"SELECT t.* FROM trabajo t "
							+ "JOIN evaluacion e ON t.id = e.trabajo_id "
							+ "WHERE e.evaluador_dni = :id AND e.fecha >= :desde "
							+ "AND e.fecha <= :hasta", Trabajo.class);
			query.setParameter("id", id);
			query.setParameter("desde", desde);
			query.setParameter("hasta", hasta);
			if (!query.getResultList().isEmpty()) {
				return query.getResultList();
			}
		}
		//System.out.println("La consulta no devolvio ningun resultado");
		return new ArrayList<Trabajo>();
	}

	public List<Trabajo> findAllTrabajosAutorRevisorTema(int idAutor, int idEvaluador, int idTematica) {
		EntityManager entityManager = EMF.createEntityManager();
		Usuario autor = this.findById(idAutor);
		Usuario evaluador = this.findById(idEvaluador);
		List<Trabajo>retorno = new ArrayList<Trabajo>();

		TematicaDAO daoT = TematicaDAO.getInstance();
		Tematica tema = daoT .findById(idTematica);
		if(autor != null && evaluador != null && tema !=null) {
			Query query = entityManager.createNativeQuery(
					"SELECT * FROM trabajo t "
							+ "JOIN autor_trabajo aut ON t.id = aut.trabajo_id "
							+ "JOIN evaluador_trabajo et ON t.id = et.trabajo_id "
							+ "JOIN trabajo_tematica tt ON t.id = tt.Trabajo_id "
							+ "WHERE aut.autor_id = :idAutor " 
							+ "AND et.evaluador_id = :idEvaluador "
							+ "AND tt.temas_id = :idTematica", Trabajo.class);
			query.setParameter("idAutor", idAutor);
			query.setParameter("idEvaluador", idEvaluador);
			query.setParameter("idTematica", idTematica);
			if (!query.getResultList().isEmpty()) {
				retorno = query.getResultList();
				return retorno;
			}
		}
		//System.out.println("La consulta no devolvio ningun resultado");
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
			//TO DO
			entityManager.getTransaction().commit();
			entityManager.close();
			return entityAux;
		}
	}
}
