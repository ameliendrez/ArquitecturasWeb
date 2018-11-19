package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import entidades.EMF;
import entidades.TipoTrabajo;

public class TipoTrabajoDAO extends BaseJpaDAO<TipoTrabajo, Integer> {

	private static TipoTrabajoDAO tipoTrabajoDao;

	private TipoTrabajoDAO(){
		super(TipoTrabajo.class,Integer.class);
	}

	public static TipoTrabajoDAO getInstance() {
		if(tipoTrabajoDao == null)
			tipoTrabajoDao = new TipoTrabajoDAO();
		return tipoTrabajoDao;
	}

	@Override
	public TipoTrabajo findById(Integer id) {
		EntityManager entityManager = EMF.createEntityManager();
		TipoTrabajo tipoTrabajo = entityManager.find(TipoTrabajo.class, id);
		entityManager.close();
		return tipoTrabajo;
	}

	public TipoTrabajo persist(TipoTrabajo tipoTrabajo) {
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(tipoTrabajo);
		entityManager.getTransaction().commit();
		entityManager.close();
		return tipoTrabajo;
	}
	
	public void removeAll() {
		EntityManager entityManager = EMF.createEntityManager();
		Query query = entityManager.createNativeQuery("DELETE FROM tipotrabajo");
		entityManager.getTransaction().begin();
		query.executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public List<TipoTrabajo> findAll() {
		EntityManager entityManager = EMF.createEntityManager();
		List<TipoTrabajo>retorno = new ArrayList<TipoTrabajo>();
		Query query = entityManager.createNativeQuery("SELECT * FROM tipotrabajo", TipoTrabajo.class);
		if (!query.getResultList().isEmpty()) {
			retorno = query.getResultList();
			return retorno;
		}
		//System.out.println("La consulta no devolvio ningun resultado");
		throw new UnsupportedOperationException();
	}
	
	public TipoTrabajo getFirst(){
		return findAll().get(0);
	}
	
	public int getCantidadTiposTrabajo() {
		return findAll().size()+0;
	}
	
	public boolean delete(int id) {
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("DELETE FROM TipoTrabajo tt WHERE tt.id = :id");
		query.setParameter("id", id);
		int deletedCount = query.executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.close();
		if(deletedCount > 0)
			return true;
		else
			return false;
	}

	public TipoTrabajo update(int id, TipoTrabajo entity) {
		EntityManager entityManager = EMF.createEntityManager();
		TipoTrabajo entityAux = entityManager.find(TipoTrabajo.class, id);
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
