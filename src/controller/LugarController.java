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

import dao.LugarDAO;
import entidades.Lugar;


@Path("/lugars")
public class LugarController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Lugar> getAllLugars() {
		System.out.println("traigo todos los lugars");
		return LugarDAO.getInstance().findAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Lugar getLugarById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Lugar lugar = LugarDAO.getInstance().findById(id);
		if(lugar!= null)
			return lugar;
		else
			throw new RecursoNoExiste(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createLugar(Lugar lugar) {
		Lugar result = LugarDAO.getInstance().persist(lugar);
		if(result == null) {
			throw new RecursoDuplicado(lugar.getId());
		}
		else {
			return Response.status(201).entity(lugar).build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteLugar(@PathParam("id") int id) {
		boolean wasDeleted = LugarDAO.getInstance().delete(id);
		if(wasDeleted)
			return Response.status(200).build();
		else
			throw new RecursoNoExiste(id);
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateLugar(@PathParam("id") int id,Lugar lugar) {
		Lugar result = LugarDAO.getInstance().update(id, lugar);
		if(result==null) {
			throw new RecursoNoExiste(id);
		}else {
			return Response.status(200).entity(lugar).build();
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