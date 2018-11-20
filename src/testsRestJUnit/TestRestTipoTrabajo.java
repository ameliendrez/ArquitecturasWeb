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

public class TestRestTipoTrabajo {
	public final String BASE_URL = "http://localhost:8080/TPE-ARQUITECTURAS/api";

	public final HttpClient client = HttpClientBuilder.create().build();

	@Test
	public void testRESTInterface() throws ClientProtocolException, IOException {
		crearTiposTrabajos();
		getTipoTrabajo();
		listarTipoTrabajos();
	}

	public void crearTiposTrabajos() throws ClientProtocolException, IOException {
		System.out.println("TipoTrabajoTest-> Se crean tipos de trabajo");

		String url = BASE_URL + "/tipoTrabajos";

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("nombre", "Poster");
		jsonObject.put("condicion", "False");
		String jsonString = jsonObject.toString();
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		HttpResponse response = client.execute(post);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		String resultContent = getResultContent(response);
		System.out.println("Response Content : " + resultContent);

		jsonObject = mapper.createObjectNode();
		jsonObject.put("nombre", "Articulo");
		jsonObject.put("condicion", "True");
		jsonString = jsonObject.toString();

		post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		response = client.execute(post);

		System.out.println("\nPOST "+url);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		resultContent = getResultContent(response);
		System.out.println("Response Content : " + resultContent);

		jsonObject = mapper.createObjectNode();
		jsonObject.put("nombre", "Resumen");
		jsonObject.put("condicion", "True");
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

	public void listarTipoTrabajos() throws ClientProtocolException, IOException {
		System.out.println("TipoTrabajoTest-> Se traen todos los tipos de trabajos");

		String url = BASE_URL + "/tipoTrabajos";

		HttpGet request = new HttpGet(url);

		HttpResponse response = client.execute(request);

		System.out.println("\nGET " + url);

		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		String resultContent = getResultContent(response);

		System.out.println("Response Content : " + resultContent);

	}

	public void getTipoTrabajo() throws ClientProtocolException, IOException {
		System.out.println("TipoTrabajoTest-> Se trae un tipo de trabajo");

		String url = BASE_URL + "/tipoTrabajos/1";

		HttpGet request = new HttpGet(url);

		HttpResponse response = client.execute(request);

		System.out.println("\nGET " + url);

		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		String resultContent = getResultContent(response);

		System.out.println("Response Content : " + resultContent);

	}

}
