package com.mycompany.myapp.websocket;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.Vector;

import javax.annotation.PostConstruct;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class PhotoresistorSensorHandler extends TextWebSocketHandler implements ApplicationListener<ApplicationEvent> {
	private static final Logger LOGGER = LoggerFactory.getLogger(PhotoresistorSensorHandler.class);
	private List<WebSocketSession> list = new Vector<>();
	private CoapClient coapClient;
	private CoapObserveRelation coapObserveRelation;
	
	@PostConstruct
	public void init() {
		coapClient = new CoapClient();
		coapClient.setURI("coap://192.168.3.48:5683/photoresistorsensor");
		coapObserveRelation = coapClient.observe(new CoapHandler() {
			
			//COap서버로무터 메시지가 오면 OnLoad가 실행
			@Override
			public void onLoad(CoapResponse response) {
				//temperature만 넘어오기 떄문에 time을 추가
				String json = response.getResponseText();
				//temperature를 읽어서 jsobObject로 불러온다.
				JSONObject jsonObject = new JSONObject(json);
				double doubleT=Double.parseDouble(jsonObject.getString("photoresistor"));
				double photoresistor=((int)(doubleT*10))/10.0;
				
				jsonObject= new JSONObject();
				jsonObject.put("time", getTimeUTC(new Date().getTime()));
				jsonObject.put("photoresistor", photoresistor);
				json = jsonObject.toString();
				try {
					for (WebSocketSession session : list) {
						session.sendMessage(new TextMessage(json));
					}
				} catch (Exception e) {
				}
				
			}
			
			@Override
			public void onError() {
				
			}
		});
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		LOGGER.info("");
		list.add(session);
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		LOGGER.info("");
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		LOGGER.info("");
		list.remove(session);
	}
	
	public long getTimeUTC(long localTime) {
		long utcTime = 0;
		TimeZone tz = TimeZone.getDefault();
		try {
			int offset = tz.getOffset(localTime);
			utcTime = localTime + offset;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return utcTime;
	}
	
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof ContextClosedEvent) {
			coapObserveRelation.proactiveCancel();
			coapClient.shutdown();
			
		}
	}
}
