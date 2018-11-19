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

import dao.UsuarioDAO;
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
		if(usuario!=null)
			return usuario;
		else
			throw new RecursoNoExiste(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUsuario(Usuario usuario) {
		Usuario result= UsuarioDAO.getInstance().persist(usuario);
		if(result==null) {
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
		Usuario result= UsuarioDAO.getInstance().update(id, usuario);
		if(result==null) {
			throw new RecursoNoExiste(id);
		}else {
			return Response.status(200).entity(usuario).build();
		}
	}	
}