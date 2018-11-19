package testsRestJUnit;

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
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class TestRestTrabajo {
	
	public final String BASE_URL = "http://localhost:8080/TPE-ARQUITECTURAS/api";

	public final HttpClient client = HttpClientBuilder.create().build();

	@Test
	public void testRESTInterface() throws ClientProtocolException, IOException {
		crearTrabajos();
		getTrabajo();
		listarTrabajos();
		//updateTematica();
		//deleteTematica();
	}

	public void crearTrabajos() throws ClientProtocolException, IOException {
		System.out.println("TrabajoTest-> Se crean trabajos");
		
		HttpGet tipoTrabajoRequest = new HttpGet(BASE_URL + "/tipoTrabajos/1");
		HttpResponse responseTipoTrabajo = client.execute(tipoTrabajoRequest);
		String tipoTrabajo = getResultContent(responseTipoTrabajo);
		
		System.out.println(tipoTrabajo);
		
		HttpGet tipoTrabajoRequest2 = new HttpGet(BASE_URL + "/tipoTrabajos/2");
		HttpResponse responseTipoTrabajo2 = client.execute(tipoTrabajoRequest2);
		String tipoTrabajo2 = getResultContent(responseTipoTrabajo2);
		
		System.out.println(tipoTrabajo2);

		
		HttpGet usuarioRequest = new HttpGet(BASE_URL + "/usuarios/36626800");
		HttpResponse responseUsuario = client.execute(usuarioRequest);
		String usuario = getResultContent(responseUsuario);
		
		System.out.println(usuario);

		
		HttpGet usuarioRequest2 = new HttpGet(BASE_URL + "/usuarios/41313351");
		HttpResponse responseUsuario2 = client.execute(usuarioRequest2);
		String usuario2 = getResultContent(responseUsuario2);
		
		System.out.println(usuario2);
		
		HttpGet tematicaRequest = new HttpGet(BASE_URL + "/tematicas/1");
		HttpResponse responseTematica = client.execute(tematicaRequest);
		String tematica = getResultContent(responseTematica);
		
		System.out.println(tematica);
		
		HttpGet tematicaRequest2 = new HttpGet(BASE_URL + "/tematicas/2");
		HttpResponse responseTematica2 = client.execute(tematicaRequest2);
		String tematica2 = getResultContent(responseTematica2);

		System.out.println(tematica2);

		
		String url = BASE_URL + "/trabajos";

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();

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

//		jsonObject = mapper.createObjectNode();
//		jsonObject.put("nombre", "Programando con phyton");
//		jsonObject.putPOJO("tipoTrabajo", tipoTrabajo2);
//		jsonObject.putPOJO("autores", usuario2);
//		jsonObject.putPOJO("tematicas", tematica2);
//		jsonString = jsonObject.toString();
//
//		post = new HttpPost(url);
//		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
//		response = client.execute(post);
//
//		System.out.println("\nPOST "+url);
//		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
//		resultContent = getResultContent(response);
//		System.out.println("Response Content : " + resultContent);
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

	public void listarTrabajos() throws ClientProtocolException, IOException {
		System.out.println("TrabajoTest-> Se traen todos los trabajos");

		String url = BASE_URL + "/trabajos";

		HttpGet request = new HttpGet(url);

		HttpResponse response = client.execute(request);

		System.out.println("\nGET " + url);

		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		String resultContent = getResultContent(response);

		System.out.println("Response Content : " + resultContent);

	}

	public void getTrabajo() throws ClientProtocolException, IOException {
		System.out.println("TrabajoTest-> Se trae un trabajo");

		String url = BASE_URL + "/trabajos/1";

		HttpGet request = new HttpGet(url);

		HttpResponse response = client.execute(request);

		System.out.println("\nGET " + url);

		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		String resultContent = getResultContent(response);

		System.out.println("Response Content : " + resultContent);

	}

}
