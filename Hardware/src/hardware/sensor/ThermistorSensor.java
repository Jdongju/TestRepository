package hardware.sensor;

import com.pi4j.io.i2c.I2CFactory;
import hardware.converter.PCF8591;
import java.io.IOException;

public class ThermistorSensor {

	//Field
	private PCF8591 pcf8591;

	//Constructor
	public ThermistorSensor(PCF8591 pcf8591) throws Exception {
		this.pcf8591 = pcf8591;
	}

	public double getValue() throws Exception {
		int analogVal = pcf8591.analogRead();//0~255
		double value = 5 * (double) analogVal / 255;
		value= 10000*value/(5-value);
		value = 1 / ((Math.log(value / 10000) / 3950) + (1 / (273.15 + 25)));
		value = value - 273.15;
		return value; //섭씨 리턴
	}

	public static void main(String[] args) throws Exception {
		PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN0); //i2cAddress는 0x48로 고정. AIN0으로 온도센서와 연결
		ThermistorSensor thermistorSensor = new ThermistorSensor(pcf8591);
		while(true)
		{
			double value = thermistorSensor.getValue();
			System.out.println(value + "도");
			Thread.sleep(1000);
		}
	}

}
