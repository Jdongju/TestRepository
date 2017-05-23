package hardware.converter;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import java.io.IOException;

public class PCF8591 {

	public static final int AIN0 = 0x40;
	public static final int AIN1 = 0x41;
	public static final int AIN2 = 0x42;
	public static final int AIN3 = 0x43;

	private int i2cAddress;
	private int ainNo;
	private int analogVal;

	public PCF8591(int i2cAddress, int ainNo) {
		this.i2cAddress = i2cAddress;
		this.ainNo = ainNo;
	}

	public int analogRead() throws I2CFactory.UnsupportedBusNumberException, IOException {
		I2CBus i2cBus = I2CFactory.getInstance(I2CBus.BUS_1);
		I2CDevice i2cDevice = i2cBus.getDevice(i2cAddress); //read와 write가 기능이 다르기 때문에 필드에 선언하지 않고 메소드에서 선언하도록 해서 겹치지않게 했다.
		i2cDevice.read(ainNo);//dummy read
		analogVal=i2cDevice.read(ainNo);
		return analogVal;
	}
	
	public void analogWrite(byte value) throws I2CFactory.UnsupportedBusNumberException, IOException{
		I2CBus i2cBus = I2CFactory.getInstance(I2CBus.BUS_1);
		I2CDevice i2CDevice = i2cBus.getDevice(i2cAddress);
		i2CDevice.write(AIN0,value);
	}

	public static void main(String[] args) throws I2CFactory.UnsupportedBusNumberException, IOException, InterruptedException {
			PCF8591 pcf8591 = new PCF8591(0x48,AIN0); //AIN0은 가변저항값.
			while(true){
//				아날로그값읽기
				int value= pcf8591.analogRead();
				if(value>255){
					value=255;
				}
			
				System.out.println(value);
//			아날로그값 출력
				pcf8591.analogWrite((byte)value);
				Thread.sleep(200);
			}
	}

}
