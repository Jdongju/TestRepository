package coap.exam01.server;

import com.pi4j.io.i2c.I2CFactory;
import hardware.converter.PCF8591;
import hardware.sensor.ThermistorSensor;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class CoapResource01 extends CoapResource{

	//Field
	private ThermistorSensor thermistorSensor; //Hardware 프로젝트를 Network프로젝트의 Libraries에 Add Project로 Jar파일 추가
	//Constructor
	public CoapResource01() throws Exception  {
		super("resource01");
		PCF8591 pcf8591= new PCF8591(0x48, PCF8591.AIN1);
		thermistorSensor=new ThermistorSensor(pcf8591);
	}

	//Method

	@Override
	public void handleGET(CoapExchange exchange) {
		try {
			double value = thermistorSensor.getValue();
			exchange.respond("온도 : 섭씨"+ value+ "도");
		} catch (IOException ex) {
		} catch (I2CFactory.UnsupportedBusNumberException ex) {
			Logger.getLogger(CoapResource01.class.getName()).log(Level.SEVERE, null, ex);
		} catch (Exception ex) {
			Logger.getLogger(CoapResource01.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		
	}
	
	
}
