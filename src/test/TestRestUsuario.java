package test;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import dao.LugarDAO;
import dao.TematicaDAO;
import dao.UsuarioDAO;
import entidades.Lugar;
import entidades.Tematica;
import entidades.Usuario;

@FixMethodOrder(MethodSorters.JVM)
public class TestRestUsuario {

	private static LugarDAO lugarDAO;
	private static Lugar uncpba;
	private static Lugar unlp;
	private static TematicaDAO tematicaDAO;
	private static UsuarioDAO usuarioDAO;
	private static Usuario usuario;
	private static Usuario usuarioBBDD;

	public final String BASE_URL = "http://localhost:8080/Cacic2018/api";

	public final HttpClient client = HttpClientBuilder.create().build();

	@BeforeClass
	public static void setUpEnviroment(){
		System.out.println("UsuarioTest-> Comienza el test");
		System.out.println("UsuarioTest-> Se prepara el ambiente");

		lugarDAO = LugarDAO.getInstance();
		tematicaDAO = TematicaDAO.getInstance();
		uncpba = new Lugar("UNCPBA", "Tandil");
		assertNotNull(uncpba);
		unlp = new Lugar("UNLP", "La Plata");
		assertNotNull(unlp);
		usuarioDAO = UsuarioDAO.getInstance();
		usuario = new Usuario(36626800, "Agustin", "Meliendrez", uncpba);
		assertNotNull(usuario);

		Tematica tema = new Tematica("Inteligencia Artificial", true);
		usuario.setTema(tema);


		tematicaDAO.persist(tema);
		lugarDAO.persist(uncpba);
		lugarDAO.persist(unlp);
		usuarioDAO.persist(usuario);
		usuarioBBDD = usuarioDAO.getFirst();
		assertNotNull(usuarioBBDD);
	}

	@Test
	public void testRESTInterface() throws ClientProtocolException, IOException {
		//		crearUsuarios();
		getUsuario();
		//		listarUsuarios();
		//		updateUsuario();
		//		deleteUsuario();
	}


	public void crearUsuarios() throws ClientProtocolException, IOException {
		System.out.println("UsuarioTest-> Se crea un nuevo usuario");

		String url = BASE_URL + "/usuarios";

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("dni", "41313351");
		jsonObject.put("apellido", "Rampoldi");
		jsonObject.put("nombre", "Santiago");
		//		jsonObject.put("nombre", "Roque");
		String jsonString = jsonObject.toString();

		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		HttpResponse response = client.execute(post);

		System.out.println("\nPOST " + url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		String resultContent = getResultContent(response);
		System.out.println("Response Content : " + resultContent);

	}


	private String getResultContent(HttpResponse response) throws IOException {
		HttpEntity entity = response.getEntity();
		if(entity != null) {
			BufferedReader rd = new BufferedReader(new InputStreamReader(entity.getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			return result.toString();
		}
		else {
			return "";
		}
	}

	public void getUsuario() throws ClientProtocolException, IOException {
		System.out.println("UsuarioTest-> Se trae un usuario");

		String url = BASE_URL + "/usuarios/36626800";

		HttpGet request = new HttpGet(url);

		HttpResponse response = client.execute(request);

		System.out.println("\nGET " + url);

		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		String resultContent = getResultContent(response);

		System.out.println("Response Content : " + resultContent);

	}



	public void listarUsuarios() throws ClientProtocolException, IOException {
		System.out.println("UsuarioTest-> Se traen todos los usuarios");

		String url = BASE_URL + "/usuarios";

		HttpGet request = new HttpGet(url);

		HttpResponse response = client.execute(request);

		System.out.println("\nGET " + url);

		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		String resultContent = getResultContent(response);

		System.out.println("Response Content : " + resultContent);

	}

	public void updateUsuario() throws ClientProtocolException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("nombre", "Roque");
		jsonObject.put("raza", "Callejero");
		jsonObject.put("edad", 8);
		String jsonString = jsonObject.toString();

		String url = BASE_URL + "/perros/1";
		HttpPut request = new HttpPut(url);
		request.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		HttpResponse response = client.execute(request);

		System.out.println("\nPUT "+url);

		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		String resultContent = getResultContent(response);

		System.out.println("Response Content : " + resultContent);

	}

	public void deleteUsuario() throws ClientProtocolException, IOException {

		String url = BASE_URL + "/perros/2";

		HttpDelete request = new HttpDelete(url);

		HttpResponse response = client.execute(request);

		System.out.println("\nDELETE "+url);

		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		String resultContent = getResultContent(response);

		System.out.println("Response Content : " + resultContent);

	}

}
