package controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.LugarDAO;
import entidades.Lugar;
import exceptions.RecursoDuplicado;
import exceptions.RecursoNoExiste;


@Path("/lugares")
public class LugarController {
	@GET
	@Produces(MediaType.APPLICATION_JSON) 
	public List<Lugar> getAllLugares() {
		return LugarDAO.getInstance().findAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Lugar getLugarById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Lugar lugar = LugarDAO.getInstance().findById(id);
		if(lugar!=null)
			return lugar;
		else
			throw new RecursoNoExiste(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createLugar(Lugar lugar) {
		Lugar result= LugarDAO.getInstance().persist(lugar);
		if(result==null) {
			throw new RecursoDuplicado(lugar.getId());
		}else {
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

}