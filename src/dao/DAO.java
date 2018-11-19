package dao;

import java.io.Serializable;
import java.util.List;

public interface DAO<Entity, ID extends Serializable> {

	public Entity persist(Entity entity);

//	public Entity update(ID id,Entity newEntityValues);

	public Entity findById(ID id);

	public List<Entity> findAll();
	
//	public boolean delete(ID id);
	
}
