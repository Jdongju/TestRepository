package com.raspoid.test;

import com.pi4j.io.i2c.I2CFactory;
import com.raspoid.MPU6050;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;



public class Test {

	
	//Field
	private String ipAddress="192.168.3.48";
	private CoapClient coapClient;
	private JSONObject jSONObject;
	private String json;
	
	//Method
	private void frontTire(int angle){
		jSONObject= new JSONObject();
		jSONObject.put("command","change");
		jSONObject.put("angle", String.valueOf(angle));
		json=jSONObject.toString();
		
		System.out.println(json);
		
		coapClient= new CoapClient();
		coapClient.setURI("coap://"+ipAddress+"/fronttire");
		CoapResponse response=coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		System.out.println(response);
		coapClient.shutdown();
	}
	
	private void backTire(String direction, int speed){
		jSONObject= new JSONObject();
		jSONObject.put("command", "change");
		jSONObject.put("direction", direction);
		jSONObject.put("speed", String.valueOf(speed));
		json= jSONObject.toString();
		
		coapClient= new CoapClient();
		coapClient.setURI("coap://"+ipAddress+"/backtire");
		coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		coapClient.shutdown();
	}


	public static void main(String[] args) throws I2CFactory.UnsupportedBusNumberException, InterruptedException {
		Test test=new Test();
		
		MPU6050 mpu6050= new MPU6050();
		
		mpu6050.startUpdatingThread();
		
		while (true){
			double[] filteredAngles = mpu6050.getFilteredAngles();
			if(filteredAngles[0]>130){
				test.frontTire(130);
				System.out.println("angle: "+filteredAngles[0]);
			}else{
				test.frontTire((int) filteredAngles[0]);
					System.out.println("angle: "+filteredAngles[0]);
			}
		
//			System.out.println("angle: "+filteredAngles[0]);
		}
		 
	}
}
