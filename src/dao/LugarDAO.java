package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import entidades.EMF;
import entidades.Lugar;

public class LugarDAO extends BaseJpaDAO<Lugar, Integer> {

	private static LugarDAO lugarDao;

	private LugarDAO(){
		super(Lugar.class,Integer.class);
	}

	public static LugarDAO getInstance() {
		if(lugarDao == null)
			lugarDao = new LugarDAO();
		return lugarDao;
	}

	@Override
	public Lugar findById(Integer id) {
		EntityManager entityManager = EMF.createEntityManager();
		Lugar lugar = entityManager.find(Lugar.class, id);
		entityManager.close();
		return lugar;
	}

	public Lugar persist(Lugar lugar) {
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(lugar);
		entityManager.getTransaction().commit();
		entityManager.close();
		return lugar;
	}

	public void removeAll() {
		EntityManager entityManager = EMF.createEntityManager();
		Query query = entityManager.createNativeQuery("DELETE FROM lugar");
		entityManager.getTransaction().begin();
		query.executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public List<Lugar> findAll() {
		EntityManager entityManager = EMF.createEntityManager();
		List<Lugar>retorno = new ArrayList<Lugar>();
		Query query = entityManager.createNativeQuery("SELECT * FROM lugar", Lugar.class);
		if (!query.getResultList().isEmpty()) {
			retorno = query.getResultList();
			return retorno;
		}
		//System.out.println("La consulta no devolvio ningun resultado");
		throw new UnsupportedOperationException();
	}

	public Lugar getFirst(){
		return findAll().get(0);
	}

	public int getCantidadLugares() {
		return findAll().size()+0;
	}

	public boolean delete(int id) {
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("DELETE FROM Lugar l WHERE l.id = :id");
		query.setParameter("id", id);
		int deletedCount = query.executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.close();
		if(deletedCount > 0)
			return true;
		else
			return false;
	}

	public Lugar update(int id, Lugar entity) {
		EntityManager entityManager = EMF.createEntityManager();
		Lugar entityAux = entityManager.find(Lugar.class, id);
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
