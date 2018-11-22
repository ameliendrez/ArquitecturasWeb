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
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class TestRestLugar {
	
	private final int OK = 201;
	private static final int ERROR = 500;
	
	public final String BASE_URL = "http://localhost:8080/TPE-ARQUITECTURAS/api";

	public HttpClient client = HttpClientBuilder.create().build();
	
	private void resetHttpClient() {
		this.client = HttpClientBuilder.create().build();
	}

	@Test
	public void testRESTInterface() throws ClientProtocolException, IOException {
		crearLugares();
		getLugar();
		listarLugares();
	}

	public void crearLugares() throws ClientProtocolException, IOException {
		System.out.println("\nLugarTest-> Se crean lugares");

		String url = BASE_URL + "/lugares";

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		
		jsonObject.put("nombre", "Pladema");
		jsonObject.put("ciudad", "Tandil");
		String jsonString = jsonObject.toString();
		
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		HttpResponse response = client.execute(post);

		assertEquals(this.OK, response.getStatusLine().getStatusCode());
		
		this.resetHttpClient();

		jsonObject = mapper.createObjectNode();
		jsonObject.put("nombre", "Conicet");
		jsonObject.put("ciudad", "Tandil");
		jsonString = jsonObject.toString();

		post = new HttpPost(url);
		post.setEntity(new StringEntity(jsonString, ContentType.APPLICATION_JSON));
		response = client.execute(post);

		assertEquals(this.OK, response.getStatusLine().getStatusCode());
		
		this.resetHttpClient();

		jsonObject = mapper.createObjectNode();
		jsonObject.put("nombre", "UBA");
		jsonObject.put("ciudad", "Buenos Aires");
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

	public void listarLugares() throws ClientProtocolException, IOException {
		System.out.println("\nLugarTest-> Se traen todos los lugares");

		String url = BASE_URL + "/lugares";

		HttpGet request = new HttpGet(url);

		HttpResponse response = client.execute(request);

		assertEquals(200, response.getStatusLine().getStatusCode());
		
		this.resetHttpClient();
	}

	public void getLugar() throws ClientProtocolException, IOException {
		System.out.println("\nLugarTest-> Se trae un lugar");

		String url = BASE_URL + "/lugares/1";

		HttpGet request = new HttpGet(url);

		HttpResponse response = client.execute(request);

		assertEquals(200, response.getStatusLine().getStatusCode());

		this.resetHttpClient();
	}

}