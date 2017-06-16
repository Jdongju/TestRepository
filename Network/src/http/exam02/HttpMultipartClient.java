package http.exam02;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

public class HttpMultipartClient {
	public static void main(String[] args) throws IOException {
		String title="제목";
		StringBody titleBody= new StringBody(title, ContentType.create("text/plain", Charset.forName("UTF-8")));
		String content="내용";
		StringBody contentBody= new StringBody(content, ContentType.create("text/plain", Charset.forName("UTF-8")));
		File attach = new File("C:/Temp/사막.jpg");
		FileBody attachBody= new FileBody(attach);
		
		HttpPost httpPost = new HttpPost("http://192.168.3.164:8080/IoTWebProgramming/http/exam02");
		
		 MultipartEntityBuilder multipartEntityBuilder= MultipartEntityBuilder.create();
		 //문자파트
		multipartEntityBuilder.addPart("title", titleBody);
		multipartEntityBuilder.addPart("content", contentBody);
		//파일 파트
		multipartEntityBuilder.setCharset(Charset.forName("UTF-8")); //파일이름에 한글이 포함되어있을경우 UTF-8로한다.
		multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE); //브라우저가 파일을 보내는 방식과 동일하게
		multipartEntityBuilder.addPart("attach", attachBody);
		//멀티파트 인코딩된 본문 얻기
		HttpEntity reqEntity= multipartEntityBuilder.build();
		
		httpPost.setEntity(reqEntity);
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try{
		CloseableHttpResponse response = httpClient.execute(httpPost);
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
						String resTitle=jSONObject.getString("title");
						String resContent=jSONObject.getString("content");
						String originalfilename=jSONObject.getString("originalfilename");
						String savedfilename=jSONObject.getString("savedfilename");
						String filecontenttype=jSONObject.getString("filecontenttype");
						
						System.out.println("title: "+resTitle);
						System.out.println("content: "+resContent);
						System.out.println("originalfilename: "+originalfilename);
						System.out.println("savedfilename: "+ savedfilename);
						System.out.println("filecontenttype"+filecontenttype);
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
