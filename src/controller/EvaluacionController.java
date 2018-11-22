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

import dao.EvaluacionDAO;
import entidades.Evaluacion;
import exceptions.RecursoDuplicado;
import exceptions.RecursoNoExiste;


@Path("/evaluaciones")
public class EvaluacionController {
	@GET
	@Produces(MediaType.APPLICATION_JSON) 
	public List<Evaluacion> getAllEvaluacions() {
		return EvaluacionDAO.getInstance().findAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Evaluacion getEvaluacionById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Evaluacion evaluacion = EvaluacionDAO.getInstance().findById(id);
		if(evaluacion != null)
			return evaluacion;
		else
			throw new RecursoNoExiste(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createEvaluacion(Evaluacion evaluacion) {
		Evaluacion result = EvaluacionDAO.getInstance().persist(evaluacion);
		if(result == null) {
			throw new RecursoDuplicado(evaluacion.getId());
		}else {
			return Response.status(201).entity(evaluacion).build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteEvaluacion(@PathParam("id") int id) {
		boolean wasDeleted = EvaluacionDAO.getInstance().delete(id);
		if(wasDeleted)
			return Response.status(200).build();
		else
			throw new RecursoNoExiste(id);
	}
		
}