package dao;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import entidades.EMF;
import entidades.Evaluacion;
import entidades.Tematica;
import entidades.Trabajo;
import entidades.Usuario;    

public class TrabajoDAO extends BaseJpaDAO<Trabajo, Integer> {

	private static TrabajoDAO trabajoDao;

	private TrabajoDAO(){
		super(Trabajo.class,Integer.class);
	}

	public static TrabajoDAO getInstance() {
		if(trabajoDao == null)
			trabajoDao = new TrabajoDAO();
		return trabajoDao;
	}

	@Override
	public Trabajo findById(Integer id) {
		EntityManager entityManager = EMF.createEntityManager();
		Trabajo trabajo = entityManager.find(Trabajo.class, id);
		entityManager.close();
		return trabajo;
	}

	public Trabajo persist(Trabajo trabajo) {
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(trabajo);
		entityManager.getTransaction().commit();
		entityManager.close();
		return trabajo;
	}
	
	public void removeAll() {
		EntityManager entityManager = EMF.createEntityManager();
		Query query = entityManager.createQuery("DELETE FROM Trabajo");
		entityManager.getTransaction().begin();
		query.executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public Set<Usuario> evaluadoresAsignables(Trabajo t) {
		Set<Usuario> retorno = new HashSet<Usuario>();
		//Falta implementar la evaluacion de que usuarios son asignables para los trabajos
		return retorno;
	}

	public Trabajo getTrabajo(Integer id) {
		EntityManager entityManager = EMF.createEntityManager();
		Query query = entityManager.createQuery("SELECT t FROM Trabajo t WHERE id = :id");
		query.setParameter("id", id);
		entityManager.close();
		return (Trabajo) query.getSingleResult();
	}
	
	@Override
	public List<Trabajo> findAll() {
		EntityManager entityManager = EMF.createEntityManager();
		List<Trabajo>retorno = new ArrayList<Trabajo>();
		Query query = entityManager.createQuery("SELECT t FROM Trabajo t");
		if (!query.getResultList().isEmpty()) {
			retorno = query.getResultList();
			return retorno;
		}
		return new ArrayList<Trabajo>();
	}
	
	public List<Usuario> getEvaluadores(Integer id) {
		EntityManager entityManager = EMF.createEntityManager();
		List<Usuario>retorno = new ArrayList<Usuario>();
		String jpql = "SELECT u FROM Trabajo t, Usuario u WHERE t.id = :id AND u MEMBER OF t.evaluadores";
//		String jpql = "SELECT u FROM Evaluacion e JOIN e.evaluador u WHERE e.trabajo.id = :id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);

		if (!query.getResultList().isEmpty()) {
			retorno = query.getResultList();
			return retorno;
		}
		return new ArrayList<Usuario>();
	}
	
	public List<Usuario> getAutores(int id) {
		EntityManager entityManager = EMF.createEntityManager();
		List<Usuario>retorno = new ArrayList<Usuario>();
		String jpql = "SELECT u FROM Trabajo t, Usuario u WHERE t.id = :id AND u MEMBER OF t.autores";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);

		if (!query.getResultList().isEmpty()) {
			retorno = query.getResultList();
			return retorno;
		}
		return new ArrayList<Usuario>();
	}
	
	public List<Tematica> getTemas(int id) {
		EntityManager entityManager = EMF.createEntityManager();
		List<Tematica>retorno = new ArrayList<Tematica>();
		String jpql = "SELECT te FROM Trabajo t, Tematica te WHERE t.id = :id AND te MEMBER OF t.tematicas";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);

		if (!query.getResultList().isEmpty()) {
			retorno = query.getResultList();
			return retorno;
		}
		return new ArrayList<Tematica>();
	}
	
	public List<Evaluacion> getEvaluaciones(int id) {
		EntityManager entityManager = EMF.createEntityManager();
		List<Evaluacion>retorno = new ArrayList<Evaluacion>();
		String jpql = "SELECT e FROM Evaluacion e, Trabajo t WHERE t.id = :id AND t MEMBER OF e.trabajo";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);

		if (!query.getResultList().isEmpty()) {
			retorno = query.getResultList();
			return retorno;
		}
		return new ArrayList<Evaluacion>();
	}
		
	public int getCantidadTrabajos() {
		return findAll().size() + 0;
	}
	
	public Trabajo getFirst(){
		return findAll().get(0);
	}

	public boolean delete(Integer id) {
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("DELETE FROM Trabajo t WHERE t.id = :id");
		query.setParameter("id", id);
		int deletedCount = query.executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.close();
		if(deletedCount > 0)
			return true;
		else
			return false;
	}

}
