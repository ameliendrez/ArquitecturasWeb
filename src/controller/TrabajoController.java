package controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.TrabajoDAO;
import entidades.Evaluacion;
import entidades.Tematica;
import entidades.Trabajo;
import entidades.Usuario;
import exceptions.RecursoDuplicado;
import exceptions.RecursoNoExiste;


@Path("/trabajos")
public class TrabajoController {
	@GET
	@Produces(MediaType.APPLICATION_JSON) 
	public List<Trabajo> getAllTrabajos() {
		return TrabajoDAO.getInstance().findAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Trabajo getTrabajoById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Trabajo trabajo = TrabajoDAO.getInstance().findById(id);
		if(trabajo!=null)
			return trabajo;
		else
			throw new RecursoNoExiste(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTrabajo(Trabajo trabajo) {
		Trabajo result = TrabajoDAO.getInstance().persist(trabajo);
		if(result == null) {
			throw new RecursoDuplicado(trabajo.getId());
		}else {
			return Response.status(201).entity(trabajo).build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTrabajo(@PathParam("id") int id) {
		boolean wasDeleted = TrabajoDAO.getInstance().delete(id);
		if(wasDeleted)
			return Response.status(200).build();
		else
			throw new RecursoNoExiste(id);
	}
	
	@GET
	@Path("/{id}/evaluadores")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> getEvaluadoresDeTrabajoById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Trabajo trabajo = TrabajoDAO.getInstance().findById(id);
		if(trabajo != null)
			return TrabajoDAO.getInstance().getEvaluadores(id);
		else
			throw new RecursoNoExiste(id);
	}
	
	@GET
	@Path("/{id}/autores")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> getAutoresDeTrabajoById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Trabajo trabajo = TrabajoDAO.getInstance().findById(id);
		if(trabajo != null)
			return TrabajoDAO.getInstance().getAutores(id);
		else
			throw new RecursoNoExiste(id);
	}
	
	@GET
	@Path("/{id}/tematicas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Tematica> getTemasDeTrabajoById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Trabajo trabajo = TrabajoDAO.getInstance().findById(id);
		if(trabajo != null)
			return TrabajoDAO.getInstance().getTemas(id);
		else
			throw new RecursoNoExiste(id);
	}
	
	@GET
	@Path("/{id}/evaluaciones")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Evaluacion> getEvaluacionesDeUnTrabajoById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Trabajo trabajo = TrabajoDAO.getInstance().findById(id);
		if(trabajo != null)
			return TrabajoDAO.getInstance().getEvaluaciones(id);
		else
			throw new RecursoNoExiste(id);
	}
	
}