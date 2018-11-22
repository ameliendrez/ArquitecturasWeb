package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import entidades.EMF;
import entidades.Tematica;

public class TematicaDAO extends BaseJpaDAO<Tematica, Integer> {

	private static TematicaDAO tematicaDao;

	private TematicaDAO(){
		super(Tematica.class,Integer.class);
	}

	public static TematicaDAO getInstance() {
		if(tematicaDao == null)
			tematicaDao = new TematicaDAO();
		return tematicaDao;
	}

	@Override
	public Tematica findById(Integer id) {
		EntityManager entityManager = EMF.createEntityManager();
		Tematica tematica = entityManager.find(Tematica.class, id);
		entityManager.close();
		return tematica;
	}

	public Tematica persist(Tematica tematica) {
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(tematica);
		entityManager.getTransaction().commit();
		entityManager.close();
		return tematica;
	}

	public void removeAll() {
		EntityManager entityManager = EMF.createEntityManager();
		Query query = entityManager.createQuery("DELETE FROM Tematica");
		entityManager.getTransaction().begin();
		query.executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public List<Tematica> findAll() {
		EntityManager entityManager = EMF.createEntityManager();
		List<Tematica>retorno = new ArrayList<Tematica>();
		Query query = entityManager.createQuery("SELECT t FROM Tematica t");
		if (!query.getResultList().isEmpty()) {
			retorno = query.getResultList();
			return retorno;
		}
		throw new UnsupportedOperationException();
	}

	public Tematica getFirst(){
		return findAll().get(0);
	}

	public int getCantidadTematicas() {
		return findAll().size()+0;
	}

	public boolean delete(int id) {
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("DELETE FROM Tematica t WHERE t.id = :id");
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
