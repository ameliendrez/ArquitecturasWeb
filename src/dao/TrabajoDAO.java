package dao;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import entidades.EMF;
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
		Query query = entityManager.createNativeQuery("DELETE FROM trabajo");
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
		Query query = entityManager.createNativeQuery("SELECT * FROM trabajo WHERE id = :id", Trabajo.class);
		query.setParameter("id", id);
		entityManager.close();
		return (Trabajo) query.getSingleResult();
	}
	
	@Override
	public List<Trabajo> findAll() {
		EntityManager entityManager = EMF.createEntityManager();
		List<Trabajo>retorno = new ArrayList<Trabajo>();
		Query query = entityManager.createNativeQuery("SELECT * FROM trabajo", Trabajo.class);
		if (!query.getResultList().isEmpty()) {
			retorno = query.getResultList();
			return retorno;
		}
		//System.out.println("La consulta no devolvio ningun resultado");
		throw new UnsupportedOperationException();
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
//		Trabajo trabajo = this.findById(id);		
//		if(trabajo != null) {
//			entityManager.remove(trabajo);
//			return true;
//		}
//		return false;
	}

	public Trabajo update(int id, Trabajo entity) {
		EntityManager entityManager = EMF.createEntityManager();
		Trabajo entityAux = entityManager.find(Trabajo.class, id);
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
