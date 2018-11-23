package testsRestJUnit;


import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;


public class TestRestUsuario {
	
	private final int OK = 201;

	public final String BASE_URL = "http://localhost:8080/TPE-ARQUITECTURAS/api";

	public HttpClient client = HttpClientBuilder.create().build();
	
	private void resetHttpClient() {
		this.client = HttpClientBuilder.create().build();
	}

	@BeforeClass
	public static void cleanUserTable() {
		
	}
	
	@Test
	public void testRESTInterface() throws ClientProtocolException, IOException {
		crearUsuarios();
		getUsuario();
		listarUsuarios();
		updateUsuario();
		getTrabajos();
//		getEvaluaciones();
		getConocimientos();
	}

	public void crearUsuarios() throws ClientProtocolException, IOException {
		System.out.println("UsuarioTest-> Se crean usuarios");

		HttpGet lugarRequest = new HttpGet(BASE_URL + "/lugares/1");
		HttpResponse responseLugar = client.execute(lugarRequest);
		String lugar = getResultContent(responseLugar);

		lugarRequest = new HttpGet(BASE_URL + "/lugares/2");
		responseLugar = client.execute(lugarRequest);
		String lugar2 = getResultContent(responseLugar);
		
		HttpGet tematicaRequest = new HttpGet(BASE_URL + "/tematicas/1");
		HttpResponse responseTematica = client.execute(tematicaRequest);
		String tematica = getResultContent(responseTematica);

		HttpGet tematicaRequest2 = new HttpGet(BASE_URL + "/tematicas/2");
		HttpResponse responseTematica2 = client.execute(tematicaRequest2);
		String tematica2 = getResultContent(responseTematica2);

		String url = BASE_URL + "/usuarios";

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		//----------------------------------------------------------------
		jsonObject.put("dni", "41313351");
		jsonObject.put("apellido", "Rampoldi");
		jsonObject.put("nombre", "Santiago");
		jsonObject.putPOJO("lugar", lugar);

		ArrayNode temas = jsonObject.putArray("temas");
		temas.addPOJO(tematica2);
		jsonObject.putPOJO("temas", temas);

		String jsonString = jsonObject.toString();
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		HttpResponse response = client.execute(post);

		assertEquals(this.OK, response.getStatusLine().getStatusCode());

		this.resetHttpClient();

		//----------------------------------------------------------------------
		jsonObject = mapper.createObjectNode();
		jsonObject.put("dni", "27332662");
		jsonObject.put("apellido", "Ronaldo");
		jsonObject.put("nombre", "Cristiano");
		jsonObject.putPOJO("lugar", lugar2);

		jsonString = jsonObject.toString();

		post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		response = client.execute(post);

		assertEquals(this.OK, response.getStatusLine().getStatusCode());

		this.resetHttpClient();

		//--------------------------------------------------------------------
		jsonObject = mapper.createObjectNode();
		jsonObject.put("dni", "28003662");
		jsonObject.put("apellido", "Burton");
		jsonObject.put("nombre", "Tim");
		jsonObject.putPOJO("lugar", lugar);

		jsonString = jsonObject.toString();

		post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		response = client.execute(post);

		assertEquals(this.OK, response.getStatusLine().getStatusCode());

		this.resetHttpClient();

		//--------------------------------------------------------------------
		jsonObject = mapper.createObjectNode();
		jsonObject.put("dni", "36626800");
		jsonObject.put("apellido", "Meliendrez");
		jsonObject.put("nombre", "Agustin");
		jsonObject.putPOJO("lugar", lugar2);
		
		temas.addPOJO(tematica);
		jsonObject.putPOJO("temas", temas);

		jsonString = jsonObject.toString();

		post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		response = client.execute(post);

		assertEquals(this.OK, response.getStatusLine().getStatusCode());

		this.resetHttpClient();

		//--------------------------------------------------------------------
		jsonObject = mapper.createObjectNode();
		jsonObject.put("dni", "22003004");
		jsonObject.put("apellido", "Messi");
		jsonObject.put("nombre", "Lionel");
		jsonObject.putPOJO("lugar", lugar2);

		jsonString = jsonObject.toString();

		post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		response = client.execute(post);

		assertEquals(this.OK, response.getStatusLine().getStatusCode());

		this.resetHttpClient();

		//--------------------------------------------------------------------
		jsonObject = mapper.createObjectNode();
		jsonObject.put("dni", "32251631");
		jsonObject.put("apellido", "Zuckerberg");
		jsonObject.put("nombre", "Mark");
		jsonObject.putPOJO("lugar", lugar);

		jsonString = jsonObject.toString();

		post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		response = client.execute(post);

		assertEquals(this.OK, response.getStatusLine().getStatusCode());

		this.resetHttpClient();

		//--------------------------------------------------------------------
		jsonObject = mapper.createObjectNode();
		jsonObject.put("dni", "24516673");
		jsonObject.put("apellido", "Alan");
		jsonObject.put("nombre", "Turing");
		jsonObject.putPOJO("lugar", lugar);

		jsonString = jsonObject.toString();

		post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		response = client.execute(post);

		assertEquals(this.OK, response.getStatusLine().getStatusCode());

		this.resetHttpClient();

		//--------------------------------------------------------------------
		jsonObject = mapper.createObjectNode();
		jsonObject.put("dni", "23216742");
		jsonObject.put("apellido", "Cerf");
		jsonObject.put("nombre", "Vinton");
		jsonObject.putPOJO("lugar", lugar);

		jsonString = jsonObject.toString();

		post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		response = client.execute(post);

		assertEquals(this.OK, response.getStatusLine().getStatusCode());

		this.resetHttpClient();

		//--------------------------------------------------------------------
		jsonObject = mapper.createObjectNode();
		jsonObject.put("dni", "31555124");
		jsonObject.put("apellido", "Einstein");
		jsonObject.put("nombre", "Albert");
		jsonObject.putPOJO("lugar", lugar2);

		jsonString = jsonObject.toString();

		post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		response = client.execute(post);

		assertEquals(this.OK, response.getStatusLine().getStatusCode());

		this.resetHttpClient();

		//--------------------------------------------------------------------
		jsonObject = mapper.createObjectNode();
		jsonObject.put("dni", "23556165");
		jsonObject.put("apellido", "Hawking");
		jsonObject.put("nombre", "Stephen");
		jsonObject.putPOJO("lugar", lugar2);

		jsonString = jsonObject.toString();

		post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		response = client.execute(post);

		assertEquals(this.OK, response.getStatusLine().getStatusCode());

		this.resetHttpClient();

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

	/**
	 * @throws ClientProtocolException
	 * @throws IOException
	 * Se traen todos los usuarios
	 */

	public void listarUsuarios() throws ClientProtocolException, IOException {
		System.out.println("UsuarioTest-> Se traen todos los usuarios");

		String url = BASE_URL + "/usuarios";

		HttpGet request = new HttpGet(url);

		HttpResponse response = client.execute(request);

		assertEquals(200, response.getStatusLine().getStatusCode());

		this.resetHttpClient();

	}

	/**
	 * @throws ClientProtocolException
	 * @throws IOException
	 * Se trae un solo usuario dado un dni
	 */

	public void getUsuario() throws ClientProtocolException, IOException {
		System.out.println("UsuarioTest-> Se trae un usuario");

		String url = BASE_URL + "/usuarios/36626800";

		HttpGet request = new HttpGet(url);

		HttpResponse response = client.execute(request);

		assertEquals(200, response.getStatusLine().getStatusCode());

		this.resetHttpClient();

	}

	/**
	 * @throws ClientProtocolException
	 * @throws IOException
	 * Se traen todos los trabajos asignados a un usuario
	 */

	public void getTrabajos() throws ClientProtocolException, IOException {
		System.out.println("UsuarioTest-> Se traen los trabajos de un usuario");

		String url = BASE_URL + "/usuarios/36626800/trabajos";

		HttpGet request = new HttpGet(url);

		HttpResponse response = client.execute(request);

		assertEquals(200, response.getStatusLine().getStatusCode());

		this.resetHttpClient();

	}

	/**
	 * @throws ClientProtocolException
	 * @throws IOException
	 * Se traen todos los conocimientos o tematicas que posee un usuario
	 */

	public void getConocimientos() throws ClientProtocolException, IOException {
		System.out.println("UsuarioTest-> Se traen los conocimientos o tematicas de un usuario");

		String url = BASE_URL + "/usuarios/36626800/tematicas";

		HttpGet request = new HttpGet(url);

		HttpResponse response = client.execute(request);
		
		assertEquals(200, response.getStatusLine().getStatusCode());

		this.resetHttpClient();

	}

	/**
	 * @throws ClientProtocolException
	 * @throws IOException
	 * Se traen todas las evaluaciones realizadas por un usuario
	 */

	public void getEvaluaciones() throws ClientProtocolException, IOException {
		System.out.println("UsuarioTest-> Se traen las evaluaciones de un usuario");

		String url = BASE_URL + "/usuarios/36626800/evaluaciones";

		HttpGet request = new HttpGet(url);

		HttpResponse response = client.execute(request);

		System.out.println("\nGET " + url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		String resultContent = getResultContent(response);
		System.out.println("Response Content : " + resultContent);

	}

	/**
	 * @throws ClientProtocolException
	 * @throws IOException
	 * Se actualizan los datos de un usuario ya existente
	 */

	public void updateUsuario() throws ClientProtocolException, IOException {		
		System.out.println("UsuarioTest-> Se modifican los datos de un usuario");


		HttpGet lugarRequest = new HttpGet(BASE_URL + "/lugares/1");
		HttpResponse responseLugar = client.execute(lugarRequest);
		String lugar2 = getResultContent(responseLugar);

		String url = BASE_URL + "/usuarios/41313351";

		String dni = "41313351";

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("dni", dni);
		jsonObject.put("nombre", "Rodolfo");
		jsonObject.put("apellido", "Rampoldino");
		jsonObject.putPOJO("lugar", lugar2);

		String jsonString = jsonObject.toString();

		HttpPut request = new HttpPut(url);
		request.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		HttpResponse response = client.execute(request);

		assertEquals(200, response.getStatusLine().getStatusCode());

		this.resetHttpClient();
		
	}

}
