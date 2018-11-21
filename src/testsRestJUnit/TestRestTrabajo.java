package testsRestJUnit;

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
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import dao.UsuarioDAO;

public class TestRestTrabajo {

//	private static final int OK = 201;
//	private static final int ERROR = 500;
//	private static final int NOTFOUND = 404;

	public final String BASE_URL = "http://localhost:8080/TPE-ARQUITECTURAS/api";

	public final HttpClient client = HttpClientBuilder.create().build();

	@Test
	public void testRESTInterface() throws ClientProtocolException, IOException {
		crearTrabajos();
		getTrabajo();
		listarTrabajos();
		listarAutores();
		asignarEvaluadores();
		listarEvaluadores();
		listarTematicas();
//		listarEvaluaciones();

		//Borrar todas las entradas de la Base de Datos
		//deleteAll();
		
		//Borrar la Base de Datos
		//deleteDatabase();
	}

	public void crearTrabajos() throws ClientProtocolException, IOException {
		System.out.println("\n\nTrabajoTest-> Se crean trabajos");

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

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		String resultContent = getResultContent(response);
		System.out.println("Response Content : " + resultContent);

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

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		resultContent = getResultContent(response);
		System.out.println("Response Content : " + resultContent);
		
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

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		resultContent = getResultContent(response);
		System.out.println("Response Content : " + resultContent);
		
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

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		resultContent = getResultContent(response);
		System.out.println("Response Content : " + resultContent);

		
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

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		resultContent = getResultContent(response);
		System.out.println("Response Content : " + resultContent);

		
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

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		resultContent = getResultContent(response);
		System.out.println("Response Content : " + resultContent);

		
		//----------------------------------------------------------------------
		jsonObject = mapper.createObjectNode();
		jsonObject.put("nombre", "Metodos Agiles");
		jsonObject.putPOJO("tipoTrabajo", tipoTrabajo);
		jsonObject.putPOJO("autores", usuario);
		jsonObject.putPOJO("tematicas", tematica2);
		jsonObject.putPOJO("evaluadores", usuario2);
		//TODO
		
//		ArrayNode autores = jsonObject.putArray("autores");
//		autores.addPOJO(usuario);
//		jsonObject.putPOJO("autores", autores);


		jsonString = jsonObject.toString();
		post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		response = client.execute(post);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		resultContent = getResultContent(response);
		System.out.println("Response Content : " + resultContent);

		
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

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		resultContent = getResultContent(response);
		System.out.println("Response Content : " + resultContent);

		
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

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		resultContent = getResultContent(response);
		System.out.println("Response Content : " + resultContent);

		
		//----------------------------------------------------------------------Ultimo
		jsonObject = mapper.createObjectNode();
		jsonObject.put("nombre", "Go");
		jsonObject.putPOJO("tipoTrabajo", tipoTrabajo);
		jsonObject.putPOJO("autores", usuario2);
		jsonObject.putPOJO("tematicas", tematica2);


		jsonString = jsonObject.toString();
		post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		response = client.execute(post);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		resultContent = getResultContent(response);
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

	/**
	 * @throws ClientProtocolException
	 * @throws IOException
	 * Se muestran todos los trabajos existentes
	 */
	
	public void listarTrabajos() throws ClientProtocolException, IOException {
		System.out.println("\n\nTrabajoTest-> Se traen todos los trabajos");

		String url = BASE_URL + "/trabajos";

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
	 * Se trae un solo trabajo dado un id
	 */

	public void getTrabajo() throws ClientProtocolException, IOException {
		System.out.println("\nTrabajoTest-> Se trae un trabajo");

		String url = BASE_URL + "/trabajos/1";

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
	 * Se traen todos los autores dado un trabajo
	 */
	
	public void listarAutores() throws ClientProtocolException, IOException {
		System.out.println("\nTrabajoTest-> Se listan los autores de un trabajo");

		String url = BASE_URL + "/trabajos/1/autores";

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
	 * Se traen todos los evaluadores dado un trabajo
	 */

	public void listarEvaluadores() throws ClientProtocolException, IOException {
		System.out.println("\nTrabajoTest-> Se listan los evaluadores de un trabajo");

		String url = BASE_URL + "/trabajos/7/evaluadores";

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
	 * Se traen todas las tematicas dado un trabajo
	 */
	
	public void listarTematicas() throws ClientProtocolException, IOException {
		System.out.println("\nTrabajoTest-> Se listan las tematicas de un trabajo");

		String url = BASE_URL + "/trabajos/1/tematicas";

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
	 * Se traen todas las evaluaciones dado un trabajo
	 */

	public void listarEvaluaciones() throws ClientProtocolException, IOException {
		System.out.println("\nTrabajoTest-> Se listan las evaluaciones de un trabajo");

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
		System.out.println("\nTrabajoTest-> Se asignan evaluadores a un trabajo");
		
		String url = BASE_URL + "/usuarios/asignar/41313351/7";
		
		HttpPost post = new HttpPost(url);
		
		post.setEntity(new StringEntity("", ContentType.APPLICATION_JSON));
		
		HttpResponse response = client.execute(post);
		
		System.out.println("\nPOST " + url);

		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		String resultContent = getResultContent(response);

		System.out.println("Response Content : " + resultContent);
		
	}





	//-------------------------BORRAR--------------------------------
	private void deleteAll() {
		//------------------------Seteo Strings-----------------------------------

		String urlTrabajo = BASE_URL + "/trabajos/1";

		String urlUsuario1 = BASE_URL + "/usuarios/36626800";
		String urlUsuario2 = BASE_URL + "/usuarios/41313351";

		String urlTipoTrabajo1 = BASE_URL + "/tipoTrabajos/1";
		String urlTipoTrabajo2 = BASE_URL + "/tipoTrabajos/2";
		String urlTipoTrabajo3 = BASE_URL + "/tipoTrabajos/3";

		String urlLugar1 = BASE_URL + "/lugares/1";
		String urlLugar2 = BASE_URL + "/lugares/2";
		String urlLugar3 = BASE_URL + "/lugares/3";

		String urlTematica1 = BASE_URL + "/tematicas/1";
		String urlTematica2 = BASE_URL + "/tematicas/2";
		String urlTematica3 = BASE_URL + "/tematicas/3";
		String urlTematica4 = BASE_URL + "/tematicas/4";
		String urlTematica5 = BASE_URL + "/tematicas/5";

		//------------------------Seteo Http Requests-----------------------------------


		HttpDelete requestTrabajo = new HttpDelete(urlTrabajo);

		HttpDelete requestUsuario1 = new HttpDelete(urlUsuario1);
		HttpDelete requestUsuario2 = new HttpDelete(urlUsuario2);

		HttpDelete requestTipoTrabajo1 = new HttpDelete(urlTipoTrabajo1);
		HttpDelete requestTipoTrabajo2 = new HttpDelete(urlTipoTrabajo2);
		HttpDelete requestTipoTrabajo3 = new HttpDelete(urlTipoTrabajo3);

		HttpDelete requestLugar1 = new HttpDelete(urlLugar1);
		HttpDelete requestLugar2 = new HttpDelete(urlLugar2);
		HttpDelete requestLugar3 = new HttpDelete(urlLugar3);

		HttpDelete requestTematica1 = new HttpDelete(urlTematica1);
		HttpDelete requestTematica2 = new HttpDelete(urlTematica2);
		HttpDelete requestTematica3 = new HttpDelete(urlTematica3);
		HttpDelete requestTematica4 = new HttpDelete(urlTematica4);
		HttpDelete requestTematica5 = new HttpDelete(urlTematica5);


		//------------------------Ejecuto los Requests-----------------------------------

		try {
			client.execute(requestTrabajo);

			client.execute(requestUsuario1);
			client.execute(requestUsuario2);

			client.execute(requestTipoTrabajo1);
			client.execute(requestTipoTrabajo2);
			client.execute(requestTipoTrabajo3);

			client.execute(requestLugar1);
			client.execute(requestLugar2);
			client.execute(requestLugar3);

			client.execute(requestTematica1);
			client.execute(requestTematica2);
			client.execute(requestTematica3);
			client.execute(requestTematica4);
			client.execute(requestTematica5);

			System.out.println("\nEliminados correctamente todos los elementos");

		}  
		catch (Exception e) {
			System.out.println("\nError al borrar todos los elementos");
		}

	}

	public void deleteDatabase() {
		UsuarioDAO.getInstance().dropDatabaseCacic();

		System.out.println("\nBorrada la Base de Datos");
	}

}
