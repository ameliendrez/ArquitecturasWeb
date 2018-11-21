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

import dao.TematicaDAO;
import entidades.Tematica;
import exceptions.RecursoDuplicado;
import exceptions.RecursoNoExiste;

@Path("/tematicas")
public class TematicaController {
	@GET
	@Produces(MediaType.APPLICATION_JSON) 
	public List<Tematica> getAllTematicas() {
		return TematicaDAO.getInstance().findAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Tematica getTemaById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Tematica tema = TematicaDAO.getInstance().findById(id);
		if(tema!=null)
			return tema;
		else
			throw new RecursoNoExiste(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTema(Tematica tema) {
		Tematica result= TematicaDAO.getInstance().persist(tema);
		if(result==null) {
			throw new RecursoDuplicado(tema.getId());
		}else {
			return Response.status(201).entity(tema).build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTema(@PathParam("id") int id) {
		boolean wasDeleted = TematicaDAO.getInstance().delete(id);
		if(wasDeleted)
			return Response.status(200).build();
		else
			throw new RecursoNoExiste(id);
	}
	
}