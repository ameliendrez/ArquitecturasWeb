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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.TipoTrabajoDAO;
import entidades.TipoTrabajo;


@Path("/tipoTrabajos")
public class TipoTrabajoController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TipoTrabajo> getAllTipoTrabajos() {
		System.out.println("traigo todos los tipoTrabajos");
		return TipoTrabajoDAO.getInstance().findAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public TipoTrabajo getTipoTrabajoById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		TipoTrabajo tipoTrabajo = TipoTrabajoDAO.getInstance().findById(id);
		if(tipoTrabajo!= null)
			return tipoTrabajo;
		else
			throw new RecursoNoExiste(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTipoTrabajo(TipoTrabajo tipoTrabajo) {
		TipoTrabajo result = TipoTrabajoDAO.getInstance().persist(tipoTrabajo);
		if(result == null) {
			throw new RecursoDuplicado(tipoTrabajo.getId());
		}
		else {
			return Response.status(201).entity(tipoTrabajo).build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTipoTrabajo(@PathParam("id") int id) {
		boolean wasDeleted = TipoTrabajoDAO.getInstance().delete(id);
		if(wasDeleted)
			return Response.status(200).build();
		else
			throw new RecursoNoExiste(id);
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateTipoTrabajo(@PathParam("id") int id,TipoTrabajo tipoTrabajo) {
		TipoTrabajo result = TipoTrabajoDAO.getInstance().update(id, tipoTrabajo);
		if(result==null) {
			throw new RecursoNoExiste(id);
		}else {
			return Response.status(200).entity(tipoTrabajo).build();
		}
	}

	public class RecursoDuplicado extends WebApplicationException {
		public RecursoDuplicado(int id) {
			super(Response.status(Response.Status.CONFLICT)
					.entity("El recurso con ID "+id+" ya existe").type(MediaType.TEXT_PLAIN).build());
		}
	}

	public class RecursoNoExiste extends WebApplicationException {
		public RecursoNoExiste(int id) {
			super(Response.status(Response.Status.NOT_FOUND)
					.entity("El recurso con id "+id+" no fue encontrado").type(MediaType.TEXT_PLAIN).build());
		}
	}
}