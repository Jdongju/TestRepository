package coap.exam02.client;


import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;

public class CoapResource02Client {

	//Field
	private CoapClient coapClient;

	//Constructor
	public CoapResource02Client() {
		coapClient = new CoapClient();

	}

	//Method
	public String get(int angle) {
//		coapClient.setURI("coap://localhost/resource01");
//		coapClient.setURI("coap://192.168.3.47/resource01"); //RPi(47)가 서버일떄 연결

//		String queryString="key1=value1&key2=value2";
		String queryString = "kind=ultrasonicsensor&angle=" + angle;
		coapClient.setURI("coap://192.168.3.48/resource02?" + queryString);
		CoapResponse response = coapClient.get(); //Get방식으로 요청
		if (response == null) {
			return get(angle);
		} else {
			if (response.getCode() == CoAP.ResponseCode.CONTENT) {
				return response.getResponseText();
			} else {
				return null;
			}
		}

	}

	public String post(int angle) {

//		String queryString = "kind=ultrasonicsensor&angle=" + angle;
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("kind", "ultrasonicsensor");
		jsonObject.put("angle", angle);
		String json= jsonObject.toString();
		System.out.println(json);
		coapClient.setURI("coap://192.168.3.48/resource02");
		CoapResponse response = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON); //Get방식으로 요청
		if (response == null) {
			return get(angle);
		} else {
			if (response.getCode() == CoAP.ResponseCode.CONTENT) {
				return response.getResponseText();
			} else {
				return null;
			}
		}
	}

	public void shutdown() {
		coapClient.shutdown();
	}

	public static void main(String[] args) throws InterruptedException {
		CoapResource02Client client = new CoapResource02Client();

		for (int i = 10; i <= 170; i += 10) {
			String text = client.post(i);
			System.out.println(i + " 각도 거리 :" + text); //응답내용 출력
		}
		client.get(90);
		client.shutdown();
	}

}
