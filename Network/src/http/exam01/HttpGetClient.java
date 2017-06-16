package http.exam01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

public class HttpGetClient {
	//Field
//	private HttpClient httpClient;
	//Constructor

	//Method
public static void main(String[] args) throws IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			URIBuilder uriBuilder= new  URIBuilder("http://192.168.3.164:8080/IoTWebProgramming/http/exam01");
			uriBuilder.setParameter("thermistor", String.valueOf("25"));
			uriBuilder.setParameter("photoresistor", String.valueOf("200"));
			
			HttpGet httpGet = new HttpGet(uriBuilder.build());

//			다른 방법
//			HttpGet httpget = new HttpGet("http://192.168.3.164:8080/IoTWebProgramming/http/exam01?thermistor=25&photoresistor=200");
			CloseableHttpResponse response = httpClient.execute(httpGet);
			try {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					InputStream is = resEntity.getContent();
					try {
						InputStreamReader isr= new InputStreamReader(is);
						BufferedReader br= new BufferedReader(isr);
						String json= "";
						while(true){
							String data= br.readLine();
							if(data==null) break;
							json+=data;
						}
						JSONObject jSONObject= new JSONObject(json);
						String thermistor=jSONObject.getString("thermistor");
						String photoresistor=jSONObject.getString("photoresistor");
						System.out.println("thermistor:"+  thermistor);
						System.out.println("photoresistor:"+   photoresistor);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						is.close();
					}
				}
			} finally {
				response.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.close();
		}
	}
}

