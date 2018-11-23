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
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.AfterClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import dao.UsuarioDAO;

public class TestRestTrabajo {

	private final int GETOK = 200;
	private final int OK = 201;
	private final int ERROR = 500;

	public final String BASE_URL = "http://localhost:8080/TPE-ARQUITECTURAS/api";

	public HttpClient client = HttpClientBuilder.create().build();

	private void resetHttpClient() {
		this.client = HttpClientBuilder.create().build();
	}

	@Test
	public void testRESTInterface() throws ClientProtocolException, IOException {
		crearTrabajos();
		getTrabajo();
		listarTrabajos();
		listarAutores();
		asignarEvaluadores();
		listarEvaluadores();
		asignarEvaluadoresErroneos();
		realizarRevision();
		getTrabajosRevisadosByRevisor();

	}

	public void crearTrabajos() throws ClientProtocolException, IOException {
		System.out.println("TrabajoTest-> Se crean 10 trabajos");

		HttpGet tipoTrabajoRequest = new HttpGet(BASE_URL + "/tipoTrabajos/1");
		HttpResponse responseTipoTrabajo = client.execute(tipoTrabajoRequest);
		String tipoTrabajo = getResultContent(responseTipoTrabajo);

		HttpGet tipoTrabajoRequest2 = new HttpGet(BASE_URL + "/tipoTrabajos/2");
		HttpResponse responseTipoTrabajo2 = client.execute(tipoTrabajoRequest2);
		String tipoTrabajo2 = getResultContent(responseTipoTrabajo2);


		HttpGet usuarioRequest = new HttpGet(BASE_URL + "/usuarios/36626800");
		HttpResponse responseUsuario = client.execute(usuarioRequest);
		String usuario = getResultContent(responseUsuario);


		HttpGet usuarioRequest2 = new HttpGet(BASE_URL + "/usuarios/41313351");
		HttpResponse responseUsuario2 = client.execute(usuarioRequest2);
		String usuario2 = getResultContent(responseUsuario2);

		HttpGet tematicaRequest = new HttpGet(BASE_URL + "/tematicas/1");
		HttpResponse responseTematica = client.execute(tematicaRequest);
		String tematica = getResultContent(responseTematica);


		HttpGet tematicaRequest2 = new HttpGet(BASE_URL + "/tematicas/2");
		HttpResponse responseTematica2 = client.execute(tematicaRequest2);
		String tematica2 = getResultContent(responseTematica2);


		String url = BASE_URL + "/trabajos";

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();


		//----------------------------------------------------------------------
		jsonObject.put("nombre", "Desmitificando la IA");
		jsonObject.putPOJO("tipoTrabajo", tipoTrabajo);
		jsonObject.putPOJO("autores", usuario);
		jsonObject.putPOJO("tematicas", tematica);

		String jsonString = jsonObject.toString();
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		HttpResponse response = client.execute(post);

		
		assertEquals(this.OK, response.getStatusLine().getStatusCode());

		this.resetHttpClient();

		//----------------------------------------------------------------------
		jsonObject = mapper.createObjectNode();
		jsonObject.put("nombre", "SOFia");
		jsonObject.putPOJO("tipoTrabajo", tipoTrabajo2);
		jsonObject.putPOJO("autores", usuario2);
		jsonObject.putPOJO("tematicas", tematica2);

		jsonString = jsonObject.toString();
		post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		response = client.execute(post);
		
		assertEquals(this.OK, response.getStatusLine().getStatusCode());

		this.resetHttpClient();

		//----------------------------------------------------------------------
		jsonObject = mapper.createObjectNode();
		jsonObject.put("nombre", "Python");
		jsonObject.putPOJO("tipoTrabajo", tipoTrabajo2);
		jsonObject.putPOJO("autores", usuario);
		jsonObject.putPOJO("tematicas", tematica);


		jsonString = jsonObject.toString();
		post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		response = client.execute(post);

		assertEquals(this.OK, response.getStatusLine().getStatusCode());

		this.resetHttpClient();

		//----------------------------------------------------------------------
		jsonObject = mapper.createObjectNode();
		jsonObject.put("nombre", "Java");
		jsonObject.putPOJO("tipoTrabajo", tipoTrabajo2);
		jsonObject.putPOJO("autores", usuario2);
		jsonObject.putPOJO("tematicas", tematica);


		jsonString = jsonObject.toString();
		post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		response = client.execute(post);

		assertEquals(this.OK, response.getStatusLine().getStatusCode());

		this.resetHttpClient();


		//----------------------------------------------------------------------
		jsonObject = mapper.createObjectNode();
		jsonObject.put("nombre", "Ruby");
		jsonObject.putPOJO("tipoTrabajo", tipoTrabajo2);
		jsonObject.putPOJO("autores", usuario);
		jsonObject.putPOJO("tematicas", tematica);


		jsonString = jsonObject.toString();
		post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		response = client.execute(post);

		assertEquals(this.OK, response.getStatusLine().getStatusCode());

		this.resetHttpClient();


		//----------------------------------------------------------------------
		jsonObject = mapper.createObjectNode();
		jsonObject.put("nombre", "MongoDB");
		jsonObject.putPOJO("tipoTrabajo", tipoTrabajo2);
		jsonObject.putPOJO("autores", usuario2);
		jsonObject.putPOJO("tematicas", tematica);


		jsonString = jsonObject.toString();
		post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		response = client.execute(post);

		assertEquals(this.OK, response.getStatusLine().getStatusCode());

		this.resetHttpClient();


		//----------------------------------------------------------------------
		jsonObject = mapper.createObjectNode();
		jsonObject.put("nombre", "Metodos Agiles");
		jsonObject.putPOJO("tipoTrabajo", tipoTrabajo);
		jsonObject.putPOJO("autores", usuario);
		jsonObject.putPOJO("tematicas", tematica2);

		jsonString = jsonObject.toString();
		post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		response = client.execute(post);

		assertEquals(this.OK, response.getStatusLine().getStatusCode());

		this.resetHttpClient();


		//----------------------------------------------------------------------
		jsonObject = mapper.createObjectNode();
		jsonObject.put("nombre", "Node Js");
		jsonObject.putPOJO("tipoTrabajo", tipoTrabajo);
		jsonObject.putPOJO("autores", usuario2);
		jsonObject.putPOJO("tematicas", tematica2);


		jsonString = jsonObject.toString();
		post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		response = client.execute(post);

		assertEquals(this.OK, response.getStatusLine().getStatusCode());

		this.resetHttpClient();


		//----------------------------------------------------------------------
		jsonObject = mapper.createObjectNode();
		jsonObject.put("nombre", "Angular Js");
		jsonObject.putPOJO("tipoTrabajo", tipoTrabajo);
		jsonObject.putPOJO("autores", usuario);
		jsonObject.putPOJO("tematicas", tematica2);


		jsonString = jsonObject.toString();
		post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		response = client.execute(post);

		assertEquals(this.OK, response.getStatusLine().getStatusCode());

		this.resetHttpClient();


		//----------------------------------------------------------------------
		jsonObject = mapper.createObjectNode();
		jsonObject.put("nombre", "Go");
		jsonObject.putPOJO("tipoTrabajo", tipoTrabajo);
		jsonObject.putPOJO("autores", usuario2);
		jsonObject.putPOJO("tematicas", tematica2);


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
	 * Se muestran todos los trabajos existentes
	 */

	public void listarTrabajos() throws ClientProtocolException, IOException {
		System.out.println("TrabajoTest-> Se traen todos los trabajos");

		String url = BASE_URL + "/trabajos";

		HttpGet request = new HttpGet(url);

		HttpResponse response = client.execute(request);

		assertEquals(200, response.getStatusLine().getStatusCode());

		this.resetHttpClient();

	}

	/**
	 * @throws ClientProtocolException
	 * @throws IOException
	 * Se trae un solo trabajo dado un id
	 */

	public void getTrabajo() throws ClientProtocolException, IOException {
		System.out.println("TrabajoTest-> Se trae un trabajo");

		String url = BASE_URL + "/trabajos/1";

		HttpGet request = new HttpGet(url);

		HttpResponse response = client.execute(request);

		assertEquals(200, response.getStatusLine().getStatusCode());

		this.resetHttpClient();

	}

	/**
	 * @throws ClientProtocolException
	 * @throws IOException
	 * Se traen todos los autores dado un trabajo
	 */

	public void listarAutores() throws ClientProtocolException, IOException {
		System.out.println("TrabajoTest-> Se listan los autores de un trabajo");
		
		String url = BASE_URL + "/trabajos/1/autores";
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		assertEquals(this.GETOK, response.getStatusLine().getStatusCode());
	}

	/**
	 * @throws ClientProtocolException
	 * @throws IOException
	 * Se traen todos los evaluadores dado un trabajo
	 */

	public void listarEvaluadores() throws ClientProtocolException, IOException {
		System.out.println("\nTrabajoTest-> Se listan los evaluadores de un trabajo");
		
		String url = BASE_URL + "/trabajos/7/evaluadores";
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		assertEquals(this.GETOK, response.getStatusLine().getStatusCode());
		this.resetHttpClient();
	}

	/**
	 * @throws ClientProtocolException
	 * @throws IOException
	 * Se traen todas las tematicas dado un trabajo
	 */

	public void listarTematicas() throws ClientProtocolException, IOException {
		System.out.println("TrabajoTest-> Se listan las tematicas de un trabajo");
		
		String url = BASE_URL + "/trabajos/1/tematicas";
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		assertEquals(this.GETOK, response.getStatusLine().getStatusCode());
		this.resetHttpClient();
	}

	/**
	 * @throws ClientProtocolException
	 * @throws IOException
	 * Se traen todas las evaluaciones dado un trabajo
	 */

	public void listarEvaluaciones() throws ClientProtocolException, IOException {
		System.out.println("TrabajoTest-> Se listan las evaluaciones de un trabajo");

		String url = BASE_URL + "/trabajos/7/evaluaciones";
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
	 * Se asignan evaluadores a un trabajo determinado
	 */

	public void asignarEvaluadores() throws ClientProtocolException, IOException {
		System.out.println("TrabajoTest-> Se asignan evaluadores a un trabajo");

		String url = BASE_URL + "/usuarios/asignar/41313351/7";
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity("", ContentType.APPLICATION_JSON));
		HttpResponse response = client.execute(post);
		assertEquals(this.OK, response.getStatusLine().getStatusCode());
		this.resetHttpClient();

	}

	private void asignarEvaluadoresErroneos() throws ClientProtocolException, IOException {
		System.out.println("TrabajoTest-> Se asignan evaluadores incorrectos a un trabajo");

		String url = BASE_URL + "/usuarios/asignar/27332662/1";
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity("", ContentType.APPLICATION_JSON));
		HttpResponse response = client.execute(post);
		assertEquals(this.ERROR, response.getStatusLine().getStatusCode());
		this.resetHttpClient();
	}
	
	private void realizarRevision() throws ClientProtocolException, IOException{
		System.out.println("TrabajoTest-> Se acepta una revision de un trabajo");

		HttpGet trabajoRequest = new HttpGet(BASE_URL + "/trabajos/7");
		HttpResponse responseTrabajo = client.execute(trabajoRequest);
		String trabajo = getResultContent(responseTrabajo);
		
		HttpGet usuarioRequest = new HttpGet(BASE_URL + "/usuarios/41313351");
		HttpResponse responseUsuario = client.execute(usuarioRequest);
		String usuario = getResultContent(responseUsuario);
		
		String url = BASE_URL + "/usuarios/aceptar/41313351/7";

		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();

		jsonObject.put("observacion", "Excelente trabajo");

		String jsonString = jsonObject.toString();
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		HttpResponse response = client.execute(post);
		
		assertEquals(this.OK, response.getStatusLine().getStatusCode());
	}

	private void getTrabajosRevisadosByRevisor() throws ClientProtocolException, IOException{
		System.out.println("TrabajoTest-> Se comprueba los trabajos que posee un revisor en fechas");

		HttpGet trabajoRequest = new HttpGet(BASE_URL + "/usuarios/revisados/41313351/2-10-2018/2-2-2019");
		HttpResponse responseTrabajo = client.execute(trabajoRequest);
		String trabajo = getResultContent(responseTrabajo);

		assertEquals(this.GETOK, responseTrabajo.getStatusLine().getStatusCode());
	}

	@AfterClass
	public static void deleteDatabase() {
		System.out.println("Test-> Se elimina la base de datos");
		UsuarioDAO.getInstance().dropDatabaseCacic();
	}
}
