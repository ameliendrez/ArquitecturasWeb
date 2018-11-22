package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import dao.UsuarioDAO;
import entidades.Evaluacion;
import entidades.Tematica;
import entidades.Trabajo;
import entidades.Usuario;
import exceptions.RecursoDuplicado;
import exceptions.RecursoNoExiste;


@Path("/usuarios")
public class UsuarioController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON) 
	public List<Usuario> getAllUsuarios() {
		return UsuarioDAO.getInstance().findAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getUsuarioById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Usuario usuario = UsuarioDAO.getInstance().findById(id);
		if(usuario != null)
			return usuario;
		else
			throw new RecursoNoExiste(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUsuario(Usuario usuario) {
		Usuario result = UsuarioDAO.getInstance().persist(usuario);
		if(result == null) {
			throw new RecursoDuplicado(usuario.getDni());
		}else {
			return Response.status(201).entity(usuario).build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUsuario(@PathParam("id") int id) {
		boolean wasDeleted = UsuarioDAO.getInstance().delete(id);
		if(wasDeleted)
			return Response.status(200).build();
		else
			throw new RecursoNoExiste(id);
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUsuario(@PathParam("id") int id, Usuario usuario) {
		Usuario result = UsuarioDAO.getInstance().update(id, usuario);
		if(result == null) {
			throw new RecursoNoExiste(id);
		}else {
			return Response.status(200).entity(usuario).build();
		}
	}

	@GET
	@Path("/{id}/trabajos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Trabajo> getTrabajosDeUsuarioById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		List<Trabajo>trabajos = UsuarioDAO.getInstance().findAllTrabajosAsignados(id);
		if(trabajos != null) {
			return trabajos;

		}else
			throw new RecursoNoExiste(id);
	}
	
	@GET
	@Path("/trabajosEnviados/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Trabajo> getTrabajosEnviadosDeUsuarioById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		List<Trabajo>trabajos = UsuarioDAO.getInstance().findAllTrabajosEnviados(id);
		if(trabajos != null) {
			return trabajos;

		}else
			throw new RecursoNoExiste(id);
	}

	@GET
	@Path("/{id}/evaluaciones")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Evaluacion> getEvaluacionesDeUsuarioById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Usuario usuario = UsuarioDAO.getInstance().findById(id);
		if(usuario != null)
			return UsuarioDAO.getInstance().getEvaluaciones(id);
		else
			throw new RecursoNoExiste(id);
	}

	@GET
	@Path("/{id}/tematicas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Tematica> getConocimientosDeUsuarioById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Usuario usuario = UsuarioDAO.getInstance().findById(id);
		if(usuario != null)
			return UsuarioDAO.getInstance().conocimientosDeUnUsuario(usuario);
		else
			throw new RecursoNoExiste(id);
	}

	@POST
	@Path("/asignar/{idUsuario}/{idTrabajo}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response asignarTrabajoRevisor(
			@PathParam("idUsuario") Integer idUsuario, @PathParam("idTrabajo") Integer idTrabajo) throws Exception {
		Boolean result = UsuarioDAO.getInstance().addTrabajo(idUsuario, idTrabajo);
		if (result) {
			Usuario usuario = UsuarioDAO.getInstance().findById(idUsuario);
			return Response.status(201).entity(usuario).build();
		}
		throw new Exception("Ocurrio un problema durante la asignacion del Trabajo con id: " + idTrabajo + " al usuario con id: " + idUsuario);
	}
	
	@GET
	@Path("/{id}/{from}/{to}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Trabajo> findTrabajosRevisadosEnRangoDeUsuarioById(@PathParam("id") String msg, @PathParam("from") String dateFrom,
			@PathParam("to") String dateTo) {
		
		int idUser = Integer.valueOf(msg);
		Calendar calendarFrom;
		Calendar calendarTo;
		calendarTo = Calendar.getInstance();
		calendarFrom= Calendar.getInstance();

		System.out.println(idUser);
		System.out.println(dateFrom);
		System.out.println(dateTo);

		try {
			java.util.Date fechaTo = new SimpleDateFormat("yyyy-MM-dd").parse(dateTo);
			java.util.Date fechaFrom = new SimpleDateFormat("yyyy-MM-dd").parse(dateFrom);
			calendarTo.setTime(fechaTo);
			calendarFrom.setTime(fechaFrom); 
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println(calendarFrom);
		System.out.println(calendarTo);

		List<Trabajo>revisiones = UsuarioDAO.getInstance().findAllTrabajosInvestigacionEnRango(idUser, calendarFrom, calendarTo);
		if(revisiones != null) {
			System.out.println(revisiones);
			return revisiones;
		}
		else {
			throw new RecursoNoExiste(idUser);	
		}
	}

}