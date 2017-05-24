package hardware.sensor;

import com.pi4j.io.i2c.I2CFactory;
import hardware.converter.PCF8591;
import java.io.IOException;

public class PhotoresistorSensor {

	//Field
	private PCF8591 pcf8591;

	//Constructor
	public PhotoresistorSensor(PCF8591 pcf8591) throws Exception {
		this.pcf8591 = pcf8591;
	}

	public double getValue() throws IOException, I2CFactory.UnsupportedBusNumberException {
		int analogVal = pcf8591.analogRead();//0~255
		
		return analogVal; //섭씨 리턴
	}

	public static void main(String[] args) throws Exception {
		PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN0); //i2cAddress는 0x48로 고정. AIN0으로 온도센서와 연결
		PhotoresistorSensor thermistorSensor = new PhotoresistorSensor(pcf8591);
		while(true)
		{
			double value = thermistorSensor.getValue();
			System.out.println(value + "도");
			Thread.sleep(1000);
		}
	}

}
